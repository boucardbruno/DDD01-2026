package org.octo.SeatingSuggestionsApi;

import org.junit.jupiter.api.Test;
import org.octo.SeatingPlaceSuggestions.Domain.SeatingArrangementRecommender;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository.AuditoriumLayoutRepositoryAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumSeatingArrangements.AuditoriumSeatingArrangementsAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider.ReservationsProviderAdapter;
import org.octo.SeatingSuggestionsApi.controller.SeatingSuggestionsController;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() throws IOException {

        SeatingSuggestionsController seatingSuggestionsController = new SeatingSuggestionsController(new SeatingArrangementRecommender(
                new AuditoriumSeatingArrangementsAdapter(new AuditoriumLayoutRepositoryAdapter(), new ReservationsProviderAdapter())));

        var suggestionsMade = seatingSuggestionsController.makeSuggestions("18",2);

        assertEquals(200, suggestionsMade.getStatusCode().value());
    }
}
