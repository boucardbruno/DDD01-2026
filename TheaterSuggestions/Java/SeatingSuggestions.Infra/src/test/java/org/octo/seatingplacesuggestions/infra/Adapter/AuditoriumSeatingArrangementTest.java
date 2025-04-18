package org.octo.seatingplacesuggestions.infra.Adapter;

import org.junit.jupiter.api.Test;
import org.octo.SeatingPlaceSuggestions.Domain.SeatingPlace;
import org.octo.SeatingPlaceSuggestions.Domain.ShowID;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideAuditoriumSeatingArrangements;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.AuditoriumLayoutRepository;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditoriumseating.AuditoriumSeatingArrangements;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.ReservationsProvider;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuditoriumSeatingArrangementTest {

    @Test
    public void be_a_Value_Type() throws IOException {
        IProvideAuditoriumSeatingArrangements auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());

        ShowID showIdWithoutReservationYet = new ShowID("18");
        var auditoriumSeatingFirstInstance =
                auditoriumSeatingArrangements.findByShowId(showIdWithoutReservationYet);
        var auditoriumSeatingSecondInstance =
                auditoriumSeatingArrangements.findByShowId(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        assertThat(auditoriumSeatingSecondInstance)
                .isEqualTo(auditoriumSeatingFirstInstance);

        // Should not mutate existing instance
        SeatingPlace seatAllocated = auditoriumSeatingSecondInstance.getRows().values().iterator()
                .next().seatingPlaces().getFirst().allocate();
        assertThat(seatAllocated).isInstanceOf(SeatingPlace.class);
        assertThat(auditoriumSeatingSecondInstance)
                .isEqualTo(auditoriumSeatingFirstInstance);
    }
}
