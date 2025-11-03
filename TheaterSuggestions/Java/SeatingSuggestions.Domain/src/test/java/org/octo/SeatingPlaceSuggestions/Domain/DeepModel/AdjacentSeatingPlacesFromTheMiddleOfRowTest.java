package org.octo.SeatingPlaceSuggestions.Domain.DeepModel;

import org.junit.jupiter.api.Test;
import org.octo.SeatingPlaceSuggestions.Domain.DeepModel.middleoftherow.TheMiddleOfTheRow;
import org.octo.SeatingPlaceSuggestions.Domain.PricingCategory;
import org.octo.SeatingPlaceSuggestions.Domain.Row;
import org.octo.SeatingPlaceSuggestions.Domain.SeatingPlace;
import org.octo.SeatingPlaceSuggestions.Domain.SeatingPlaceAvailability;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.octo.SeatingPlaceSuggestions.Domain.DeepModel.adjacentseatingplace.AdjacentSeatingPlaces.offerAdjacentSeatingPlace;
import static org.octo.SeatingPlaceSuggestions.Domain.DeepModel.middleoftherow.TheMiddleOfTheRow.offerSeatsNearerTheMiddleOfTheRow;

public class AdjacentSeatingPlacesFromTheMiddleOfRowTest {
    @Test
    public void
    offer_seating_places_from_the_middle_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one() {
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

        var integers = TheMiddleOfTheRow.makeSeatingPlacesWithDistance(row).stream()
                .map(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow)
                .collect(toList());

        assertThat(integers)
                .containsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlaces = offerSeatsNearerTheMiddleOfTheRow(row)
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
    offer_seating_places_from_the_middle_of_the_row_when_the_row_size_is_odd_and_party_size_is_greater_than_one() {
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

        var integers = TheMiddleOfTheRow.makeSeatingPlacesWithDistance(row).stream()
                .map(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow)
                .collect(toList());

        assertThat(integers)
                .containsExactly(4, 3, 2, 1, 0, 1, 2, 3, 4);

        var seatingPlaces = offerSeatsNearerTheMiddleOfTheRow(row)
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
    public void offer_seating_places_adjacent_of_the_row_when_the_row_size_is_even_and_party_size_is_greater_than_one() {
        var partySize = 2;
        var pricingCategory = PricingCategory.IGNORED;

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

        var integers = TheMiddleOfTheRow.makeSeatingPlacesWithDistance(row).stream()
                .map(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow)
                .collect(toList());

        assertThat(integers)
                .containsExactly(4, 3, 2, 1, 0, 0, 1, 2, 3, 4);

        var seatingPlacesWithDistance = offerSeatsNearerTheMiddleOfTheRow(row);


        var offerAdjacentSeatingPlaces = offerAdjacentSeatingPlace(seatingPlacesWithDistance, pricingCategory, partySize);
        assertThat(offerAdjacentSeatingPlaces)
                .containsExactly(a5, a6);
    }
}
