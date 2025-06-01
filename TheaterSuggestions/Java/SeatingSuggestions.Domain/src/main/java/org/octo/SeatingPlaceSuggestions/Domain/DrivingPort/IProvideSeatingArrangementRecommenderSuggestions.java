package org.octo.SeatingPlaceSuggestions.Domain.DrivingPort;

import org.octo.SeatingPlaceSuggestions.Domain.PartyRequested;
import org.octo.SeatingPlaceSuggestions.Domain.ShowID;
import org.octo.SeatingPlaceSuggestions.Domain.SuggestionsAreMade;

public interface IProvideSeatingArrangementRecommenderSuggestions {
    SuggestionsAreMade makeSuggestions(ShowID showId, PartyRequested partyRequested);
}
