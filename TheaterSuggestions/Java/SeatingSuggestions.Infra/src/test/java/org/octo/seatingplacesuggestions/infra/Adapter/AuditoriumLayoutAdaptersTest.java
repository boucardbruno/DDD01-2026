package org.octo.seatingplacesuggestions.infra.Adapter;

import org.junit.jupiter.api.Test;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository.AuditoriumLayoutRepositoryAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider.ReservationsProviderAdapter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditoriumLayoutAdaptersTest {

    @Test
    void should_allow_us_to_retrieve_reserved_seats_for_a_given_ShowId() throws IOException {
        var reservationsProviderAdapter = new ReservationsProviderAdapter();
        var reservedSeatsDto = reservationsProviderAdapter.getReservedSeats("1");
        assertEquals(19, reservedSeatsDto.reservedSeats().size(), "Expected 19 reserved seats");
    }

    @Test
    void should_allow_us_to_retrieve_AuditoriumLayout_for_a_given_ShowId() throws IOException {

        var auditoriumLayoutRepositoryAdapter = new AuditoriumLayoutRepositoryAdapter();
        var theaterDto = auditoriumLayoutRepositoryAdapter.findByShowId("2");

        // JUnit 5 Assertions
        assertEquals(6, theaterDto.rows().size(), "Expected 6 rows in the AuditoriumLayoutRepository");
        assertEquals(2, theaterDto.corridors().size(), "Expected 2 corridors in the AuditoriumLayoutRepository");
        var firstSeatOfFirstRow = theaterDto.rows().get("A").get(0);
        System.out.println(firstSeatOfFirstRow);
        assertEquals(2, firstSeatOfFirstRow.category(), "Expected category 2 for the first seat of the first row");
    }
}
