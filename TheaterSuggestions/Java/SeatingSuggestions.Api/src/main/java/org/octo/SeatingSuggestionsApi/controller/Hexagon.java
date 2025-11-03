package org.octo.SeatingSuggestionsApi.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.octo.SeatingPlaceSuggestions.Domain.DrivingPort.IProvideSeatingArrangementRecommenderSuggestions;
import org.octo.SeatingPlaceSuggestions.Domain.PartyRequested;
import org.octo.SeatingPlaceSuggestions.Domain.SeatingArrangementRecommender;
import org.octo.SeatingPlaceSuggestions.Domain.ShowID;
import org.octo.SeatingPlaceSuggestions.Domain.SuggestionsAreMade;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumSeatingArrangements.AuditoriumSeatingArrangementsAdapter;

public record Hexagon(AuditoriumSeatingArrangementsAdapter auditoriumSeatingArrangementsAdapter
) implements IProvideSeatingArrangementRecommenderSuggestions {

    private static String getSerialized(SuggestionsAreMade suggestionsMade) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(suggestionsMade);
    }

    @Override
    public String makeSuggestions(String id, int party) {
        // Infra => Domain
        ShowID showID = new ShowID(id);
        PartyRequested partyRequested = new PartyRequested(party);
        SuggestionsAreMade suggestionsAreMade = new SeatingArrangementRecommender(auditoriumSeatingArrangementsAdapter).makeSuggestions(showID, partyRequested);
        // Domain => Infra
        try {
            return getSerialized(suggestionsAreMade);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
