package org.octo.SeatingPlaceSuggestions.Domain;

public class SeatingOptionIsNotAvailable extends SeatingOptionIsSuggested {
    public SeatingOptionIsNotAvailable(PartyRequested partyRequested, PricingCategory pricingCategory) {
        super(partyRequested, pricingCategory);
    }
}
