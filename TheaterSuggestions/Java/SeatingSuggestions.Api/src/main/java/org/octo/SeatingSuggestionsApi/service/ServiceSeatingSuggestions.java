package org.octo.SeatingSuggestionsApi.service;

import org.octo.SeatingPlaceSuggestions.Domain.SeatingArrangementRecommender;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideSeatingArrangementRecommenderSuggestions;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.AuditoriumLayoutRepository;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.IProvideAuditoriumLayouts;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditoriumseating.AuditoriumSeatingArrangements;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.IProvideCurrentReservations;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.ReservationsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceSeatingSuggestions {

    @Bean
    public IProvideSeatingArrangementRecommenderSuggestions getSeatingArrangementRecommendreSuggestions() throws IOException {

        IProvideAuditoriumLayouts auditoriumLayoutRepository = new AuditoriumLayoutRepository();
        IProvideCurrentReservations reservationsProvider = new ReservationsProvider();

        return new SeatingArrangementRecommender(
                        new AuditoriumSeatingArrangements(auditoriumLayoutRepository, reservationsProvider));
    }
}
