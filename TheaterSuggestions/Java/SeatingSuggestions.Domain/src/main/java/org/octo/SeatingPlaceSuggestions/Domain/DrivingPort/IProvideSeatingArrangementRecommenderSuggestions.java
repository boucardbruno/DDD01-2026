package org.octo.SeatingPlaceSuggestions.Domain.DrivingPort;

public interface IProvideSeatingArrangementRecommenderSuggestions {
    String makeSuggestions(String id, int party);
}
