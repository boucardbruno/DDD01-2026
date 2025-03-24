using System.Collections.Generic;
using System.Linq;
using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class RowShould
{
    [Test]
    public void Be_a_Value_Type()
    {
        var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);

        // Two different instances with same values should be equals
        var rowFirstInstance = new Row("A", [a1, a2]);
        var rowSecondInstance = new Row("A", [a1, a2]);
        Check.That(rowSecondInstance).IsEqualTo(rowFirstInstance);

        // Should not mutate existing instance 
        var a3 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var rowWithNewSeatingPlace = rowSecondInstance.AddSeatingPlace(a3);
        Check.That(rowSecondInstance).IsEqualTo(rowFirstInstance);
        Check.That(rowWithNewSeatingPlace).IsNotEqualTo(rowFirstInstance);
    }

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
                MakeSeatingPlacesWithDistance(row).Select(seatingPlaceWithDistance =>
                    seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow))
            .ContainsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlaces = OfferSeatsNearerTheMiddleOfTheRow(row)
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
        var partySize = 2;

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
                MakeSeatingPlacesWithDistance(row).Select(seatingPlaceWithDistance =>
                    seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow))
            .ContainsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlacesWithDistance = OfferSeatsNearerTheMiddleOfTheRow(row);

        var offerAdjacentSeatingPlaces = OfferAdjacentSeatingPlace(seatingPlacesWithDistance, partySize);
        Check.That(offerAdjacentSeatingPlaces)
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
                MakeSeatingPlacesWithDistance(row).Select(seatingPlaceWithDistance =>
                    seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow))
            .ContainsExactly(4, 3, 2, 1, 0, 1, 2, 3, 4);

        var seatingPlaces = OfferSeatsNearerTheMiddleOfTheRow(row)
            .OrderBy(seatingPlaceWithDistance => seatingPlaceWithDistance.DistanceFromTheMiddleOfTheRow)
            .Select(seatingPlaceWithDistance => seatingPlaceWithDistance.SeatingPlace)
            .Where(seatingPlace => seatingPlace.IsAvailable())
            .Where(seatingPlace => seatingPlace.MatchCategory(PricingCategory.Ignored))
            .Take(partySize);

        Check.That(seatingPlaces)
            .ContainsExactly(a5, a6);
    }

    // -----------------------------------------------
    // DeepModeling - Seats from the middle of the row
    // -----------------------------------------------
    private static IEnumerable<SeatingPlaceWithDistance> OfferSeatsNearerTheMiddleOfTheRow(Row row)
    {
        return MakeSeatingPlacesWithDistance(row);
    }

    private static IEnumerable<SeatingPlaceWithDistance> MakeSeatingPlacesWithDistance(Row row)
    {
        // The middle of the row
        var middleOfTheRow = row.SeatingPlaces.Count / 2;
        // Distance from the middle of the row
        var seatingPlacesWithDistanceLeftPart = SeatingPlacesWithDistanceLeftPart(row, middleOfTheRow);
        var seatingPlacesWithDistanceRightPart = SeatingPlacesWithDistanceRightPart(row, middleOfTheRow);
        return seatingPlacesWithDistanceLeftPart.Concat(seatingPlacesWithDistanceRightPart);
    }

    private static IEnumerable<SeatingPlaceWithDistance> SeatingPlacesWithDistanceRightPart(Row row, int middleOfTheRow)
    {
        // Right part   A6:0, A7:1, A8:2, A9:3, A10:4 
        var seatingPlacesWithDistanceRightPart = row.SeatingPlaces
            .Skip(middleOfTheRow)
            .Select(seatingPlace => new SeatingPlaceWithDistance(
                seatingPlace,
                seatingPlace.Number - (middleOfTheRow + OffsetRightPart())));
        return seatingPlacesWithDistanceRightPart;
    }
    
    private static IEnumerable<SeatingPlaceWithDistance> SeatingPlacesWithDistanceLeftPart(Row row, int middleOfTheRow)
    {
        // Left part    A1:4, A2:3, A3:2, A4:1, A5:0,
        var seatingPlacesWithDistanceLeftPart = row.SeatingPlaces
            .Take(middleOfTheRow)
            .Select(seatingPlace => new SeatingPlaceWithDistance(
                seatingPlace, // 5 - (A1 + 1)
                (middleOfTheRow + OffsetLeftPartForRowSizeOdd(row)) - seatingPlace.Number));
        return seatingPlacesWithDistanceLeftPart;
    }

    private static int OffsetRightPart()
    {
        return 1;
    }

    private static int OffsetLeftPartForRowSizeOdd(Row row)
    {
        return row.SeatingPlaces.Count % 2 == 0 ? 0 : 1;
    }
    
    // ------------------------------
    // DeepModeling - Adjacent seats
    // ------------------------------
    private IEnumerable<SeatingPlace> OfferAdjacentSeatingPlace(
        IEnumerable<SeatingPlaceWithDistance> seatingPlacesWithDistances, int partySize)
    {
        var potentialAdjacentSeats = new List<SeatingPlaceWithDistance>();
        var groupsOfAdjacentSeats = new List<GroupOfAdjacentSeats>();
        var seatingPlacesWithDistancePrevious =
            new SeatingPlaceWithDistance(
                new SeatingPlace("Z", -1, PricingCategory.Ignored, SeatingPlaceAvailability.Allocated), -1);

        foreach (var seatingPlaceWithDistance in seatingPlacesWithDistances
                     .OrderBy(seatingPlaceWithDistance => seatingPlaceWithDistance.SeatingPlace.Number)
                     .Where(s => s.SeatingPlace.IsAvailable()))
        {
            if (seatingPlacesWithDistancePrevious.AreAdjacentSeats(seatingPlaceWithDistance))
            {
                if (!potentialAdjacentSeats.Any())
                {
                    potentialAdjacentSeats.Add(seatingPlacesWithDistancePrevious);
                }

                potentialAdjacentSeats.Add(seatingPlaceWithDistance);
            }
            else
            {
                potentialAdjacentSeats.Clear();
            }

            seatingPlacesWithDistancePrevious = seatingPlaceWithDistance;

            if (partySize.Matches(potentialAdjacentSeats))
            {
                seatingPlacesWithDistancePrevious =
                    groupsOfAdjacentSeats.AddGroupOfPlaceWithDistances(potentialAdjacentSeats);
            }
        }

        return groupsOfAdjacentSeats.Any()
            ? SelectTheBestGroupOfSeatingPlaces(groupsOfAdjacentSeats)
            : new List<SeatingPlace>();
    }

    private static IEnumerable<SeatingPlace> SelectTheBestGroupOfSeatingPlaces(
        List<GroupOfAdjacentSeats> groupsOfAdjacentSeats)
    {
        var bestOfGroupOfAdjacentSeats = groupsOfAdjacentSeats.OrderBy(s => s.SumOfDistance).First();
        return bestOfGroupOfAdjacentSeats.SeatingPlaces.OrderBy(seatingPlace => seatingPlace.Number);
    }
}
internal record SeatingPlaceWithDistance(SeatingPlace SeatingPlace, int DistanceFromTheMiddleOfTheRow);

internal class GroupOfAdjacentSeats(List<SeatingPlace> seatingPlaces, int sumOfDistance)
{
    public List<SeatingPlace> SeatingPlaces { get; } = seatingPlaces;
    public int SumOfDistance { get; } = sumOfDistance;
}

static class AdjacentSeatingPlacesExt
{
    public static SeatingPlaceWithDistance AddGroupOfPlaceWithDistances(
        this List<GroupOfAdjacentSeats> groupsOfAdjacentSeats, List<SeatingPlaceWithDistance> potentialAdjacentSeats)
    {
        var sumOfDistance = potentialAdjacentSeats.Sum(s => s.DistanceFromTheMiddleOfTheRow);
        var seatingPlaces = potentialAdjacentSeats.Select(s => s.SeatingPlace);
        groupsOfAdjacentSeats.Add(new GroupOfAdjacentSeats(seatingPlaces.ToList(), sumOfDistance));
        potentialAdjacentSeats.Clear();
        return new SeatingPlaceWithDistance(
            new SeatingPlace("Z", -1, PricingCategory.Ignored, SeatingPlaceAvailability.Allocated), -1);
    }

    public static bool AreAdjacentSeats(this SeatingPlaceWithDistance seatingPlacesWithDistancePrevious,
        SeatingPlaceWithDistance seatingPlaceWithDistance)
    {
        return seatingPlacesWithDistancePrevious.SeatingPlace.Number + 1 ==
               seatingPlaceWithDistance.SeatingPlace.Number;
    }

    public static bool Matches(this int partySize, List<SeatingPlaceWithDistance> potentialAdjacentSeats)
    {
        return potentialAdjacentSeats.Count == partySize;
    }
}