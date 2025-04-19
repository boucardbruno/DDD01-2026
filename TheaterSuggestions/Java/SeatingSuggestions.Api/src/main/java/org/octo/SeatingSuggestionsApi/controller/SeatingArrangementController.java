package org.octo.SeatingSuggestionsApi.controller;

import org.octo.SeatingPlaceSuggestions.Domain.PartyRequested;
import org.octo.SeatingPlaceSuggestions.Domain.ShowID;
import org.octo.SeatingPlaceSuggestions.Domain.SuggestionsAreMade;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideSeatingArrangementRecommenderSuggestions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/SeatingSuggestions")
public class SeatingArrangementController {

    private final IProvideSeatingArrangementRecommenderSuggestions provideSeatingArrangementRecommenderSuggestions;

    public SeatingArrangementController(IProvideSeatingArrangementRecommenderSuggestions provideSeatingArrangementRecommenderSuggestions) {
        this.provideSeatingArrangementRecommenderSuggestions = provideSeatingArrangementRecommenderSuggestions;
    }

    // GET SeatsSuggestions?showId=5&party=3
    @GetMapping(produces = "application/json")
    public ResponseEntity<SuggestionsAreMade> makeSuggestions(@RequestParam String showId, @RequestParam int party) {
        var suggestionsMade = provideSeatingArrangementRecommenderSuggestions
                .makeSuggestions(new ShowID(showId),new PartyRequested(party));
        return ResponseEntity.ok(suggestionsMade);
    }
}