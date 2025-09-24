using System.Linq;
using ExternalDependencies.AuditoriumLayoutRepository;
using ExternalDependencies.ReservationsProvider;
using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests
{
    public class SeatingArrangementRecommenderShould
    {
        /*
         *  Business Rule - Only Suggest available seats
         */

        [Test]
        public void Suggest_one_seatingPlace_when_Auditorium_contains_one_available_seatingPlace()
        {
            // Ford Auditorium-1
            //       1   2   3   4   5   6   7   8   9  10
            //  A : (2) (2)  1  (1) (1) (1) (1) (1) (2) (2)
            //  B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
            const string showId = "1";
            const int partyRequested = 1;
                
            var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
            
            // Make this assertion real to the expected one with outcomes:
            var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
            var seatingPlaces = seatingArrangementRecommender.MakeSuggestions(showId, partyRequested);
            Check.That(seatingPlaces.Select(s => s.ToString())).ContainsExactly("A3");
        }

        [Test]
        public void Return_SuggestionNotAvailable_when_Auditorium_has_all_its_seatingPlaces_reserved()
        {
            // Madison Auditorium-5
            //      1   2   3   4   5   6   7   8   9  10
            // A : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
            // B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
            const string showId = "5";
            const int partyRequested = 1;

            var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
            
            // Make it this assertion real to the expected one with outcomes:
            var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
            var seatingPlaces  = seatingArrangementRecommender.MakeSuggestions(showId, partyRequested);
            
            Check.That(seatingPlaces).IsEmpty();
        }
    }
}