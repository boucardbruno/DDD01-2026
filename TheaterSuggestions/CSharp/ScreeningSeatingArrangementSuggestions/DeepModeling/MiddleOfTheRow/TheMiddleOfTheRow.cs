namespace SeatsSuggestions.DeepModeling.MiddleOfTheRow;

public static class TheMiddleOfTheRow
{
    public static IEnumerable<SeatingPlaceWithDistance> OfferSeatsNearerTheMiddleOfTheRow(Row row)
    {
        return MakeSeatingPlacesWithDistance(row);
    }

    public static IEnumerable<SeatingPlaceWithDistance> MakeSeatingPlacesWithDistance(Row row)
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
}