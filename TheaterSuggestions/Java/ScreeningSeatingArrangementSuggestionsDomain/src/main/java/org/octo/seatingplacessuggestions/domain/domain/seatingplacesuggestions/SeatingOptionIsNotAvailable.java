package org.octo.seatingplacessuggestions.domain.domain.seatingplacesuggestions;

public class SeatingOptionIsNotAvailable extends SeatingOptionIsSuggested {
    public SeatingOptionIsNotAvailable(PartyRequested partyRequested, PricingCategory pricingCategory) {
        super(partyRequested, pricingCategory);
    }
}
