package org.octo.SeatingSuggestionsApi;

import org.octo.SeatingPlaceSuggestions.Domain.SeatingArrangementRecommender;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.AuditoriumLayoutRepository;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.IProvideAuditoriumLayouts;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditoriumseating.AuditoriumSeatingArrangements;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.IProvideCurrentReservations;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.ReservationsProvider;
import org.octo.SeatingSuggestionsApi.controller.SeatingSuggestionsController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class config {

    @Bean
    public SeatingSuggestionsController seatingSuggestionsController() throws IOException {

        IProvideAuditoriumLayouts auditoriumLayoutRepository = new AuditoriumLayoutRepository();
        IProvideCurrentReservations reservationsProvider = new ReservationsProvider();

        return new SeatingSuggestionsController(
                new SeatingArrangementRecommender(
                        new AuditoriumSeatingArrangements(auditoriumLayoutRepository, reservationsProvider)));
    }
}


