package org.octo.seatingplacesuggestions.infra;

import org.junit.jupiter.api.Test;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.SeatingPlace;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.ShowID;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.port.IAuditoriumSeatingArrangements;
import org.octo.seatingplacessuggestions.infra.adapter.auditorium.AuditoriumLayoutRepository;
import org.octo.seatingplacessuggestions.infra.adapter.auditoriumseating.AuditoriumSeatingArrangements;
import org.octo.seatingplacessuggestions.infra.adapter.reservationsprovider.ReservationsProvider;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuditoriumSeatingArrangementTest {

    @Test
    public void be_a_Value_Type() throws IOException {
        IAuditoriumSeatingArrangements auditoriumSeatingArrangements =
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
                .next().seatingPlaces().iterator().next().allocate();
        assertThat(seatAllocated).isInstanceOf(SeatingPlace.class);
        assertThat(auditoriumSeatingSecondInstance)
                .isEqualTo(auditoriumSeatingFirstInstance);
    }
}
