package org.octo.seatingplacessuggestions.seatingplacesuggestions;

import org.octo.seatingplacessuggestions.seatingplacesuggestions.deepmodel.SeatingPlaceWithDistance;

import java.util.ArrayList;
import java.util.List;

import static org.octo.seatingplacessuggestions.seatingplacesuggestions.deepmodel.adjacentseatingplace.AdjacentSeatingPlaces.offerAdjacentSeatingPlace;
import static org.octo.seatingplacessuggestions.seatingplacesuggestions.deepmodel.middleoftherow.TheMiddleOfTheRow.offerSeatsNearerTheMiddleOfTheRow;

public record Row(String name, List<SeatingPlace> seatingPlaces) {

    public SeatingOptionIsSuggested suggestSeatingOption(int partyRequested, PricingCategory pricingCategory) {

        var seatAllocation = new SeatingOptionIsSuggested(partyRequested, pricingCategory);

        for (var seat : offerAdjacentSeatsNearerTheMiddleOfRow(partyRequested, pricingCategory)) {
            if (seat.isAvailable() && seat.matchCategory(pricingCategory)) {
                seatAllocation.addSeat(seat);

                if (seatAllocation.matchExpectation())
                    return seatAllocation;

            }
        }
        return new SeatingOptionIsNotAvailable(partyRequested, pricingCategory);
    }

    public Row addSeatingPlace(SeatingPlace seatingPlace) {
        List<SeatingPlace> newSeatingPlaces = new ArrayList<>( seatingPlaces );
        newSeatingPlaces.add(seatingPlace);
        return new Row(name, newSeatingPlaces);
    }

    public Row allocate(SeatingPlace seatingPlaceSuggested) {
        ArrayList<SeatingPlace> newSeatingPlaces = new ArrayList<>();
        seatingPlaces.forEach(seatingPlace -> {
            if (seatingPlaceSuggested.isSameLocation(seatingPlace))
                newSeatingPlaces.add(seatingPlaceSuggested.allocate());
            else
                newSeatingPlaces.add(seatingPlace);
        });
        return new Row(name, newSeatingPlaces);
    }

    private List<SeatingPlace> offerAdjacentSeatsNearerTheMiddleOfRow(int partyRequested, PricingCategory pricingCategory)
    {
        var seatingPlacesWithDistance = offerSeatsNearerTheMiddleOfTheRow(this);

        if (partyRequested > 1) {
            return offerAdjacentSeatingPlace(seatingPlacesWithDistance, pricingCategory, partyRequested);
        }

        return seatingPlacesWithDistance.stream()
                .map(SeatingPlaceWithDistance::SeatingPlace)
                .filter(s -> s.matchCategory(pricingCategory))
                .filter(SeatingPlace::isAvailable)
                .limit(partyRequested)
                .toList();
    }
}
