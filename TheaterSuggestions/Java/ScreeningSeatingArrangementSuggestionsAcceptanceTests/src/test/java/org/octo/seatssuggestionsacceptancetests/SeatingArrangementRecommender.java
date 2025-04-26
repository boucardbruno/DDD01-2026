package org.octo.seatssuggestionsacceptancetests;

public class SeatingArrangementRecommender {
    public SeatingArrangementRecommender(AuditoriumSeatingArrangements auditoriumSeatingArrangements) {
        // TODO: Implement the constructor logic to initialize the recommender with the auditorium seating arrangements.
    }

    public SuggestionsAreMade makeSuggestion(String showId, int partyRequested) {
        // TODO: Implement the logic to suggest seating arrangements based on the showId and partyRequested.
        return new SuggestionsAreMade(showId, partyRequested);
    }
}
