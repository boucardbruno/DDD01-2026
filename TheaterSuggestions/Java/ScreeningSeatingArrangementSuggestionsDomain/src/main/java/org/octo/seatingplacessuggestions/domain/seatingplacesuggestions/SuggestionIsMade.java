package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions;

import java.util.List;

public class SuggestionIsMade {

    private final List<SeatingPlace> suggestedSeats;
    private final PartyRequested partyRequested;
    private final PricingCategory pricingCategory;

    public SuggestionIsMade(SeatingOptionIsSuggested seatingOptionIsSuggested) {
        this.suggestedSeats = seatingOptionIsSuggested.seats();
        this.partyRequested = seatingOptionIsSuggested.partyRequested();
        this.pricingCategory = seatingOptionIsSuggested.pricingCategory();
    }

    public List<String> seatNames() {
        return suggestedSeats.stream().map(SeatingPlace::toString).toList();
    }

    public boolean matchExpectation() {
        return suggestedSeats.size() == partyRequested.partySize();
    }

    public PricingCategory pricingCategory() {
        return pricingCategory;
    }
}
