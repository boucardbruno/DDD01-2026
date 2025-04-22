package org.octo.seatssuggestionsacceptancetests;

public class SeatingArrangementRecommender {
    public SeatingArrangementRecommender(AuditoriumSeatingArrangements auditoriumSeatingArrangements) {
    }

    public SuggestionsAreMade makeSuggestion(String showId, int partyRequested) {
        return new SuggestionsAreMade(showId, partyRequested);
    }
}
