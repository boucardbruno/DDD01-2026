namespace SeatsSuggestions.DeepModeling.AdjacentSeatingPlace;

public static class AdjacentSeatingPlaces
{
    public static IEnumerable<SeatingPlace> OfferAdjacentSeatingPlace(
        IEnumerable<SeatingPlaceWithDistance> seatingPlacesWithDistances, PricingCategory pricingCategory, int partySize)
    {
        var potentialAdjacentSeats = new List<SeatingPlaceWithDistance>();
        var groupsOfAdjacentSeats = new List<GroupOfAdjacentSeats>();
        var seatingPlacesWithDistancePrevious =
            new SeatingPlaceWithDistance(
                new SeatingPlace("Z", -1, PricingCategory.Ignored, SeatingPlaceAvailability.Allocated), -1);

        foreach (var seatingPlaceWithDistance in seatingPlacesWithDistances
                     .OrderBy(seatingPlaceWithDistance => seatingPlaceWithDistance.SeatingPlace.Number)
                     .Where(s => s.SeatingPlace.IsAvailable())
                     .Where(s => s.SeatingPlace.MatchCategory(pricingCategory))
                 )
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
internal class GroupOfAdjacentSeats(List<SeatingPlace> seatingPlaces, int sumOfDistance)
{
    public List<SeatingPlace> SeatingPlaces { get; } = seatingPlaces;
    public int SumOfDistance { get; } = sumOfDistance;
}