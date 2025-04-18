package org.octo.SeatingSuggestionsApi.service;

import org.octo.SeatingPlaceSuggestions.Domain.SeatingArrangementRecommender;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideSeatingArrangementRecommenderSuggestions;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository.AuditoriumLayoutRepositoryAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository.IProvideAuditoriumLayouts;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumSeatingArrangements.AuditoriumSeatingArrangementsAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider.IProvideCurrentReservations;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider.ReservationsProviderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceSeatingSuggestions {

    @Bean
    public IProvideSeatingArrangementRecommenderSuggestions getSeatingArrangementRecommenderSuggestions() throws IOException {

        IProvideAuditoriumLayouts auditoriumLayoutRepository = new AuditoriumLayoutRepositoryAdapter();
        IProvideCurrentReservations reservationsProvider = new ReservationsProviderAdapter();

        return new SeatingArrangementRecommender(
                        new AuditoriumSeatingArrangementsAdapter(auditoriumLayoutRepository, reservationsProvider));
    }
}
