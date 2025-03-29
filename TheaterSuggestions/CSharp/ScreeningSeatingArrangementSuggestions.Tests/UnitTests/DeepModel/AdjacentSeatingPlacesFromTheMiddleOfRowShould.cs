using System.Linq;
using NFluent;
using NUnit.Framework;
using SeatsSuggestions.DeepModeling.AdjacentSeatingPlace;
using SeatsSuggestions.DeepModeling.MiddleOfTheRow;

namespace SeatsSuggestions.Tests.UnitTests.DeepModel;

public class AdjacentSeatingPlacesFromTheMiddleOfRowShould
{
     [Test]
    public void
        Offer_seating_places_from_the_middle_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one()
    {
        var partySize = 2;

        var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.Reserved);
        var a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.Reserved);
        var a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a10 = new SeatingPlace("A", 10, PricingCategory.Second, SeatingPlaceAvailability.Available);

        var row = new Row("A", [a1, a2, a3, a4, a5, a6, a7, a8, a9, a10]);
        Check.That(
                TheMiddleOfTheRow.MakeSeatingPlacesWithDistance(row).Select(seatingPlaceWithDistance =>
                    seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow))
            .ContainsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlaces = TheMiddleOfTheRow.OfferSeatsNearerTheMiddleOfTheRow(row)
            .OrderBy(seatingPlaceWithDistance => seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow)
            .Select(seatingPlaceWithDistance => seatingPlaceWithDistance.SeatingPlace)
            .Where(seatingPlace => seatingPlace.IsAvailable())
            .Where(seatingPlace => seatingPlace.MatchCategory(PricingCategory.Ignored))
            .Take(partySize);

        Check.That(seatingPlaces)
            .ContainsExactly(a5, a6);
    }
    
    [Test]
    public void
        Offer_seating_places_from_the_middle_of_the_row_when_the_row_size_is_odd_and_party_size_is_greater_than_one()
    {
        var partySize = 2;

        var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.Reserved);
        var a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.Reserved);
        var a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.Available);

        var row = new Row("A", [a1, a2, a3, a4, a5, a6, a7, a8, a9]);
        Check.That(
                TheMiddleOfTheRow.MakeSeatingPlacesWithDistance(row).Select(seatingPlaceWithDistance =>
                    seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow))
            .ContainsExactly(4, 3, 2, 1, 0, 1, 2, 3, 4);

        var seatingPlaces = TheMiddleOfTheRow.OfferSeatsNearerTheMiddleOfTheRow(row)
            .OrderBy(seatingPlaceWithDistance => seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow)
            .Select(seatingPlaceWithDistance => seatingPlaceWithDistance.SeatingPlace)
            .Where(seatingPlace => seatingPlace.IsAvailable())
            .Where(seatingPlace => seatingPlace.MatchCategory(PricingCategory.Ignored))
            .Take(partySize);

        Check.That(seatingPlaces)
            .ContainsExactly(a5, a6);
    }
    
    [Test]
    public void Offer_seating_places_adjacent_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one()
    {
        PartyRequested partySize = new PartyRequested(2);
        var pricingCategory = PricingCategory.Ignored;

        var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.Reserved);
        var a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a10 = new SeatingPlace("A", 10, PricingCategory.Second, SeatingPlaceAvailability.Available);

        var row = new Row("A", [a1, a2, a3, a4, a5, a6, a7, a8, a9, a10]);
        Check.That(
                TheMiddleOfTheRow.MakeSeatingPlacesWithDistance(row).Select(seatingPlaceWithDistance =>
                    seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow))
            .ContainsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlacesWithDistance = TheMiddleOfTheRow.OfferSeatsNearerTheMiddleOfTheRow(row);

        var offerAdjacentSeatingPlaces =
            AdjacentSeatingPlaces.OfferAdjacentSeatingPlace(seatingPlacesWithDistance, pricingCategory, partySize);
        
        Check.That(offerAdjacentSeatingPlaces)
            .ContainsExactly(a5, a6);
    }
}