package org.octo.SeatingSuggestionsApi.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.octo.SeatingPlaceSuggestions.Domain.PartyRequested;
import org.octo.SeatingPlaceSuggestions.Domain.ShowID;
import org.octo.SeatingPlaceSuggestions.Domain.SuggestionsAreAreNotAvailable;
import org.octo.SeatingPlaceSuggestions.Domain.SuggestionsAreMade;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideSeatingArrangementRecommenderSuggestions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/SeatingSuggestions")
public class SeatingArrangementController {

    private final IProvideSeatingArrangementRecommenderSuggestions hexagon;


    public SeatingArrangementController(IProvideSeatingArrangementRecommenderSuggestions provideSeatingArrangementRecommenderSuggestions) {
        this.hexagon = provideSeatingArrangementRecommenderSuggestions;
    }

    // GET api/SeatingSuggestions?showId=5&party=3
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> makeSuggestions(@RequestParam String showId, @RequestParam int party) throws JsonProcessingException {
        // Infra => Domain
        ShowID ID = new ShowID(showId);
        PartyRequested partyRequested = new PartyRequested(party);

        var suggestionsMade = hexagon.makeSuggestions(ID, partyRequested);

        // Domain => Infra
        if (suggestionsMade instanceof SuggestionsAreAreNotAvailable) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(getSerialized(suggestionsMade), HttpStatus.OK);
    }

    private static String getSerialized(SuggestionsAreMade suggestionsMade) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(suggestionsMade);
    }
}