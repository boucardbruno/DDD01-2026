package org.octo.SeatingArrangementApi.controllers;

import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.PartyRequested;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.ShowID;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.SuggestionsAreMade;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.port.ISeatingArrangementRecommenderSuggestions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/SeatingArrangementRecommenderSuggestions")
public class SeatingArrangementRecommenderSuggestions {

    private final ISeatingArrangementRecommenderSuggestions seatingArrangementRecommenderSuggestions;

    public SeatingArrangementRecommenderSuggestions(ISeatingArrangementRecommenderSuggestions seatingArrangementRecommenderSuggestions) {
        this.seatingArrangementRecommenderSuggestions = seatingArrangementRecommenderSuggestions;
    }

    // GET api/SeatingArrangementRecommenderSuggestion?showId=5&party=3
    @GetMapping(produces = "application/json")
    public ResponseEntity<SuggestionsAreMade> makeSuggestions(@RequestParam String showId, @RequestParam int party) {
        var suggestionsMade = seatingArrangementRecommenderSuggestions.makeSuggestions(new ShowID(showId),new PartyRequested(party));
        return ResponseEntity.ok(suggestionsMade);
    }
}