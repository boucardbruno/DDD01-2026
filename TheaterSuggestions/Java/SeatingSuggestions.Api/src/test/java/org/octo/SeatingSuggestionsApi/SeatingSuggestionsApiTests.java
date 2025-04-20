package org.octo.SeatingSuggestionsApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.octo.SeatingPlaceSuggestions.Domain.SeatingArrangementRecommender;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository.AuditoriumLayoutRepositoryAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumSeatingArrangements.AuditoriumSeatingArrangementsAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider.ReservationsProviderAdapter;
import org.octo.SeatingSuggestionsApi.controller.SeatingArrangementController;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SeatingSuggestionsApiTests {

    SeatingArrangementController controller;

    @BeforeEach
    void setUp() throws IOException {
        var auditoriumLayoutRepository = new AuditoriumLayoutRepositoryAdapter();
        var reservationsProvider = new ReservationsProviderAdapter();

        controller = new SeatingArrangementController(new SeatingArrangementRecommender(
                new AuditoriumSeatingArrangementsAdapter(auditoriumLayoutRepository, reservationsProvider)));
    }

    @Test
    void should_suggest_seating_arrangement() throws IOException {
        ResponseEntity<String> suggestionsMade = controller.makeSuggestions("18",2);
        assertEquals(200, suggestionsMade.getStatusCode().value());
    }
}
