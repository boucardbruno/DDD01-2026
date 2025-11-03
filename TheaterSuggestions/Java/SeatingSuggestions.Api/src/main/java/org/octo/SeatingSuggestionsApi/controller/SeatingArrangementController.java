package org.octo.SeatingSuggestionsApi.controller;

import org.octo.SeatingPlaceSuggestions.Domain.DrivingPort.IProvideSeatingArrangementRecommenderSuggestions;
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
    public ResponseEntity<String> makeSuggestions(@RequestParam String showId, @RequestParam int party) {
        return new ResponseEntity<>(hexagon.makeSuggestions(showId, party), HttpStatus.OK);
    }
}