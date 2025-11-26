package org.octo.seatingplacessuggestions;

import org.junit.jupiter.api.Test;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.PricingCategory;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.Row;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlace;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlaceAvailability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

public class RowTest {
    @Test
    public void be_a_Value_Type() {
        var a1 = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a2 = new SeatingPlace("A", 2, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

        // Two different instances with same values should be equals
        Row rowFirstInstance = new Row("A", Arrays.asList(a1, a2));
        Row rowSecondInstance = new Row("A", Arrays.asList(a1, a2));
        assertThat(rowSecondInstance).isEqualTo(rowFirstInstance);
    }

    @Test
    public void
    Offer_seating_places_from_the_middle_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one()
    {
        var partySize = 2;

        var a1 = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a2 = new SeatingPlace("A", 2, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a3 = new SeatingPlace("A", 3, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a4 = new SeatingPlace("A", 4, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a5 = new SeatingPlace("A", 5, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a6 = new SeatingPlace("A", 6, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a7 = new SeatingPlace("A", 7, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a8 = new SeatingPlace("A", 8, PricingCategory.FIRST, SeatingPlaceAvailability.ALLOCATED);
        var a9 = new SeatingPlace("A", 9, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a10 = new SeatingPlace("A", 10, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

        var row = new Row("A", Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));

        var integers = MakeSeatingPlacesWithDistance(row).stream()
                .map(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow)
                .collect(toList());

        assertThat(integers)
                .containsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlaces = OfferSeatsNearerTheMiddleOfTheRow(row)
                .stream()
                .sorted(Comparator.comparing(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow))
                .map(SeatingPlaceWithDistance::SeatingPlace)
                .filter(SeatingPlace::isAvailable)
                .filter(seatingPlace -> seatingPlace.matchCategory(PricingCategory.IGNORED))
                .limit(partySize);

        assertThat(seatingPlaces)
                .containsExactly(a5, a6);
    }

    @Test
    public void
    Offer_seating_places_from_the_middle_of_the_row_when_the_row_size_is_odd_and_party_size_is_greater_than_one()
    {
        var partySize = 2;

        var a1 = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a2 = new SeatingPlace("A", 2, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a3 = new SeatingPlace("A", 3, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a4 = new SeatingPlace("A", 4, PricingCategory.FIRST, SeatingPlaceAvailability.ALLOCATED);
        var a5 = new SeatingPlace("A", 5, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a6 = new SeatingPlace("A", 6, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a7 = new SeatingPlace("A", 7, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a8 = new SeatingPlace("A", 8, PricingCategory.FIRST, SeatingPlaceAvailability.ALLOCATED);
        var a9 = new SeatingPlace("A", 9, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

        var row = new Row("A", Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9));

        var integers = MakeSeatingPlacesWithDistance(row).stream()
                .map(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow)
                .collect(toList());

        assertThat(integers)
                .containsExactly(4, 3, 2, 1, 0, 1, 2, 3, 4);

        var seatingPlaces = OfferSeatsNearerTheMiddleOfTheRow(row)
                .stream()
                .sorted(Comparator.comparing(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow))
                .map(SeatingPlaceWithDistance::SeatingPlace)
                .filter(SeatingPlace::isAvailable)
                .filter(seatingPlace -> seatingPlace.matchCategory(PricingCategory.IGNORED))
                .limit(partySize);

        assertThat(seatingPlaces)
                .containsExactly(a5, a6);
    }

    @Test
    public void Offer_seating_places_adjacent_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one()
    {
        var partySize = 2;

        var a1 = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a2 = new SeatingPlace("A", 2, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a3 = new SeatingPlace("A", 3, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a4 = new SeatingPlace("A", 4, PricingCategory.FIRST, SeatingPlaceAvailability.ALLOCATED);
        var a5 = new SeatingPlace("A", 5, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a6 = new SeatingPlace("A", 6, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a7 = new SeatingPlace("A", 7, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a8 = new SeatingPlace("A", 8, PricingCategory.FIRST, SeatingPlaceAvailability.AVAILABLE);
        var a9 = new SeatingPlace("A", 9, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        var a10 = new SeatingPlace("A", 10, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

        var row = new Row("A", Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));

        var integers = MakeSeatingPlacesWithDistance(row).stream()
                .map(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow)
                .collect(toList());

        assertThat(integers)
                .containsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlacesWithDistance = OfferSeatsNearerTheMiddleOfTheRow(row);

        var offerAdjacentSeatingPlaces = OfferAdjacentSeatingPlace(seatingPlacesWithDistance, partySize);
        assertThat(offerAdjacentSeatingPlaces)
                .containsExactly(a5, a6);
    }

    // Start with a prototype
    private List<SeatingPlace> OfferAdjacentSeatingPlace(List<SeatingPlaceWithDistance> seatingPlacesWithDistance, int partySize)
    {
        return new ArrayList<>();
    }

    // -----------------------------------------------
    // DeepModeling - Seats from the middle of the row
    // -----------------------------------------------
    private static List<SeatingPlaceWithDistance> OfferSeatsNearerTheMiddleOfTheRow(Row row)
    {
        return MakeSeatingPlacesWithDistance(row);
    }

    private static List<SeatingPlaceWithDistance> MakeSeatingPlacesWithDistance(Row row)
    {
        // The middle of the row
        var middleOfTheRow = row.seatingPlaces().size() / 2;
        // Distance from the middle of the row
        var seatingPlacesWithDistanceLeftPart = SeatingPlacesWithDistanceLeftPart(row, middleOfTheRow);
        var seatingPlacesWithDistanceRightPart = SeatingPlacesWithDistanceRightPart(row, middleOfTheRow);
        return Stream.concat(seatingPlacesWithDistanceLeftPart.stream(), seatingPlacesWithDistanceRightPart.stream()).toList();
    }

    private static List<SeatingPlaceWithDistance> SeatingPlacesWithDistanceRightPart(Row row, int middleOfTheRow)
    {
        // Right part   A6:0, A7:1, A8:2, A9:3, A10:4
        var seatingPlacesWithDistanceRightPart = row.seatingPlaces()
                .stream().skip(middleOfTheRow)
                .map(seatingPlace -> new SeatingPlaceWithDistance(
                        seatingPlace,
                        seatingPlace.number() - (middleOfTheRow + OffsetRightPart())));
        return seatingPlacesWithDistanceRightPart.toList();
    }

    private static List<SeatingPlaceWithDistance> SeatingPlacesWithDistanceLeftPart(Row row, int middleOfTheRow)
    {
        // Left part    A1:4, A2:3, A3:2, A4:1, A5:0,
        var seatingPlacesWithDistanceLeftPart = row.seatingPlaces()
                .stream().limit(middleOfTheRow)
                .map(seatingPlace -> new SeatingPlaceWithDistance(
                        seatingPlace, // 5 - (A1 + 1)
                        (middleOfTheRow + OffsetLeftPartForRowSizeOdd(row)) - seatingPlace.number()));
        return seatingPlacesWithDistanceLeftPart.toList();
    }

    private static int OffsetRightPart() {
        return 1;
    }

    private static int OffsetLeftPartForRowSizeOdd(Row row) {
        return row.seatingPlaces().size() % 2 == 0 ? 0 : 1;
    }
}
