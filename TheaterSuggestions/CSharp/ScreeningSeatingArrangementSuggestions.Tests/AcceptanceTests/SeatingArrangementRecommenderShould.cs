using ExternalDependencies.AuditoriumLayoutRepository;
using ExternalDependencies.ReservationsProvider;
using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests.AcceptanceTests
{
    [TestFixture]
    public class SeatingArrangementRecommenderShould
    {
        [Test]
        public void Suggest_one_seatingPlace_when_Auditorium_contains_one_available_seatingPlace()
        {
            // Ford Auditorium-1
            //
            //       1   2   3   4   5   6   7   8   9  10
            //  A : (2) (2)  1  (1) (1) (1) (1) (1) (2) (2)
            //  B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
           ShowId showId = new ShowId("1");
           PartyRequested partyRequested = new PartyRequested(1);

            var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
            var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
            var suggestionsAreMade = seatingArrangementRecommender.MakeSuggestions(showId, partyRequested);

            Check.That(suggestionsAreMade.SeatNames(PricingCategory.First)).ContainsExactly("A3");
        }
        
        [Test]
        public void Return_SuggestionNotAvailable_when_Auditorium_has_all_its_seatingPlaces_reserved()
        {
            // Madison Auditorium-5
            //
            //      1   2   3   4   5   6   7   8   9  10
            // A : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
            // B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
            var showId = new ShowId("5");
            PartyRequested partyRequested = new PartyRequested(1);

            var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
            var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
            var suggestionsAreMade = seatingArrangementRecommender.MakeSuggestions(showId, partyRequested);

            Check.That(suggestionsAreMade.PartyRequested).IsEqualTo(partyRequested);
            Check.That(suggestionsAreMade.ShowId).IsEqualTo(showId);

            Check.That(suggestionsAreMade).IsInstanceOf<SuggestionsAreNotAvailable>();
        }
        
        [Test]
        public void Suggest_two_seatingPlaces_when_Auditorium_contains_all_available_seatingPlaces()
        {
            // Lincoln Auditorium-17
            //     1   2   3   4   5   6   7   8   9  10
            //  A: 2   2   1   1   1   1   1   1   2   2
            //  B: 2   2   1   1   1   1   1   1   2   2
            ShowId showId = new ShowId("17");
            PartyRequested partyRequested = new PartyRequested(2);
            
            var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
            var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
            var suggestionsAreMade = seatingArrangementRecommender.MakeSuggestions(showId, partyRequested);


            var seatNames = suggestionsAreMade.SeatNames(PricingCategory.Second);
            Check.That(seatNames).ContainsExactly("A1", "A2", "A9", "A10", "B1", "B2");
        }

        [Test]
        public void Suggest_three_availabilities_per_PricingCategory()
        {
            // New Amsterdam-18
            //
            //     1   2   3   4   5   6   7   8   9  10
            //  A: 2   2   1   1   1   1   1   1   2   2
            //  B: 2   2   1   1   1   1   1   1   2   2
            //  C: 2   2   2   2   2   2   2   2   2   2
            //  D: 2   2   2   2   2   2   2   2   2   2
            //  E: 3   3   3   3   3   3   3   3   3   3
            //  F: 3   3   3   3   3   3   3   3   3   3
            ShowId showId = new ShowId("18");
            PartyRequested partyRequested = new PartyRequested(1);
            
            var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
            var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
            var suggestionsAreMade = seatingArrangementRecommender.MakeSuggestions(showId, partyRequested);

            Check.That(suggestionsAreMade.SeatNames(PricingCategory.First)).ContainsExactly("A5","A6","A4");
            Check.That(suggestionsAreMade.SeatNames(PricingCategory.Second)).ContainsExactly("A2", "A9", "A1");
            Check.That(suggestionsAreMade.SeatNames(PricingCategory.Third)).ContainsExactly("E5", "E6", "E4");
            Check.That(suggestionsAreMade.SeatNames(PricingCategory.Ignored)).ContainsExactly("A5", "A6", "A4");
        }
    }
}