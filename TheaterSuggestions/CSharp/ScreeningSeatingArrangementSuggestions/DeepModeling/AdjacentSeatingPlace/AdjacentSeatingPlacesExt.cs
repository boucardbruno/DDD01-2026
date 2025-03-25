using SeatsSuggestions.DeepModeling.MiddleOfTheRow;

namespace SeatsSuggestions.DeepModeling.AdjacentSeatingPlace;

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