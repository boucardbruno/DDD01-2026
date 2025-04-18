package org.octo.SeatingPlaceSuggestions.Domain;

import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideAuditoriumSeatingArrangements;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideSeatingArrangementRecommenderSuggestions;

import java.util.ArrayList;
import java.util.List;

public class SeatingArrangementRecommender implements IProvideSeatingArrangementRecommenderSuggestions {
    private static final int NUMBER_OF_SUGGESTIONS = 3;
    private final IProvideAuditoriumSeatingArrangements auditoriumSeatingArrangements;

    public SeatingArrangementRecommender(IProvideAuditoriumSeatingArrangements auditoriumSeatingArrangements) {
        this.auditoriumSeatingArrangements = auditoriumSeatingArrangements;
    }

    private static List<SuggestionIsMade> giveMeSuggestionsFor(
            AuditoriumSeatingArrangement auditoriumSeatingArrangement, PartyRequested partyRequested, PricingCategory pricingCategory) {
        var foundedSuggestions = new ArrayList<SuggestionIsMade>();

        for (int i = 0; i < NUMBER_OF_SUGGESTIONS; i++) {
            var seatingOptionSuggested = auditoriumSeatingArrangement.suggestSeatingOptionFor(partyRequested, pricingCategory);

            if (seatingOptionSuggested.matchExpectation()) {
                auditoriumSeatingArrangement = auditoriumSeatingArrangement.allocate(seatingOptionSuggested.seats());

                foundedSuggestions.add(new SuggestionIsMade(seatingOptionSuggested));
            }
        }

        return foundedSuggestions;
    }

    @Override
    public SuggestionsAreMade makeSuggestions(ShowID showId, PartyRequested partyRequested) {

        var auditoriumSeating = auditoriumSeatingArrangements.findByShowId(showId);

        var suggestionsMade = new SuggestionsAreMade(showId, partyRequested);

        for (var pricingCategory : PricingCategory.values()) {
            suggestionsMade.add(giveMeSuggestionsFor(auditoriumSeating, partyRequested, pricingCategory));
        }

        if (suggestionsMade.matchExpectations()) return suggestionsMade;

        return new SuggestionsAreAreNotAvailable(showId, partyRequested);
    }
}