package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.port;

import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.PartyRequested;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.ShowID;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.SuggestionsAreMade;

public interface ISeatingArrangementRecommenderSuggestions {
    SuggestionsAreMade makeSuggestions(ShowID showId, PartyRequested partyRequested);
}
