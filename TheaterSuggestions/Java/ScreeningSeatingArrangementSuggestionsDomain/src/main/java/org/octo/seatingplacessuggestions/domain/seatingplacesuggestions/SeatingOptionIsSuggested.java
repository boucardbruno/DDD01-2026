package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions;

import java.util.ArrayList;
import java.util.List;

public class SeatingOptionIsSuggested {

    private final PricingCategory pricingCategory;
    private final List<SeatingPlace> seats = new ArrayList<>();
    private final PartyRequested partyRequested;

    public SeatingOptionIsSuggested(PartyRequested partyRequested, PricingCategory pricingCategory) {
        this.pricingCategory = pricingCategory;
        this.partyRequested = partyRequested;
    }

    public void addSeat(SeatingPlace seat) {
        seats.add(seat);
    }

    public boolean matchExpectation() {
        return seats.size() == partyRequested.partySize();
    }

    public List<SeatingPlace> seats() {
        return seats;
    }

    public PricingCategory pricingCategory() { return pricingCategory; }

    public PartyRequested partyRequested() { return partyRequested; }
}
