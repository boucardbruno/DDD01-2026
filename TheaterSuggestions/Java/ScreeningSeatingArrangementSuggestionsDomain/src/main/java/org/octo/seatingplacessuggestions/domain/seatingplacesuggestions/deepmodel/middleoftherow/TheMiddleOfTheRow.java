package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.deepmodel.middleoftherow;

import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.Row;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.deepmodel.SeatingPlaceWithDistance;

import java.util.List;
import java.util.stream.Stream;

public class TheMiddleOfTheRow {

    public static List<SeatingPlaceWithDistance> offerSeatsNearerTheMiddleOfTheRow(Row row)
    {
        return makeSeatingPlacesWithDistance(row);
    }

    public static List<SeatingPlaceWithDistance> makeSeatingPlacesWithDistance(Row row)
    {
        // The middle of the row
        var middleOfTheRow = row.seatingPlaces().size() / 2;
        // Distance from the middle of the row
        var seatingPlacesWithDistanceLeftPart = seatingPlacesWithDistanceLeftPart(row, middleOfTheRow);
        var seatingPlacesWithDistanceRightPart = seatingPlacesWithDistanceRightPart(row, middleOfTheRow);
        return Stream.concat(seatingPlacesWithDistanceLeftPart.stream(), seatingPlacesWithDistanceRightPart.stream()).toList();
    }

    private static List<SeatingPlaceWithDistance> seatingPlacesWithDistanceRightPart(Row row, int middleOfTheRow)
    {
        // Right part   A6:0, A7:1, A8:2, A9:3, A10:4
        var seatingPlacesWithDistanceRightPart = row.seatingPlaces()
                .stream().skip(middleOfTheRow)
                .map(seatingPlace -> new SeatingPlaceWithDistance(
                        seatingPlace,
                        seatingPlace.number() - (middleOfTheRow + offsetRightPart())));
        return seatingPlacesWithDistanceRightPart.toList();
    }

    private static List<SeatingPlaceWithDistance> seatingPlacesWithDistanceLeftPart(Row row, int middleOfTheRow)
    {
        // Left part    A1:4, A2:3, A3:2, A4:1, A5:0,
        var seatingPlacesWithDistanceLeftPart = row.seatingPlaces()
                .stream().limit(middleOfTheRow)
                .map(seatingPlace -> new SeatingPlaceWithDistance(
                        seatingPlace, // 5 - (A1 + 1)
                        (middleOfTheRow + offsetLeftPartForRowSizeOdd(row)) - seatingPlace.number()));

        return seatingPlacesWithDistanceLeftPart.toList();
    }

    private static int offsetRightPart() {
        return 1;
    }

    private static int offsetLeftPartForRowSizeOdd(Row row) {
        return row.seatingPlaces().size() % 2 == 0 ? 0 : 1;
    }
}
