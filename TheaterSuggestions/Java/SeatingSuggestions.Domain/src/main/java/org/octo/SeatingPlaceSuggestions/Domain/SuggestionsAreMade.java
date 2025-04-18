package org.octo.SeatingPlaceSuggestions.Domain;

import java.util.*;

public class SuggestionsAreMade {
    private final ShowID showId;
    private final PartyRequested partyRequested;

    private final Map<PricingCategory, List<SuggestionIsMade>> forCategory = new HashMap<>();

    public SuggestionsAreMade(ShowID showId, PartyRequested partyRequested) {
        this.showId = showId;
        this.partyRequested = partyRequested;

        instantiateAnEmptyListForEveryPricingCategory();
    }

    public List<String> seatNames(PricingCategory pricingCategory) {
        return forCategory.get(pricingCategory).stream()
                .map(SuggestionIsMade::seatNames).flatMap(Collection::stream).toList();
    }

    private void instantiateAnEmptyListForEveryPricingCategory() {
        for (PricingCategory pricingCategory : PricingCategory.values()) {
            forCategory.put(pricingCategory, new ArrayList<>());
        }
    }

    public void add(Iterable<SuggestionIsMade> suggestions) {
        suggestions.forEach(suggestionIsMade -> forCategory.get(suggestionIsMade.pricingCategory()).add(suggestionIsMade));
    }

    public boolean matchExpectations() {
        return forCategory.values().stream().flatMap(List::stream).anyMatch(SuggestionIsMade::matchExpectation);
    }

    public ShowID showId() {
        return showId;
    }

    public PartyRequested partyRequested() {
        return partyRequested;
    }
}
