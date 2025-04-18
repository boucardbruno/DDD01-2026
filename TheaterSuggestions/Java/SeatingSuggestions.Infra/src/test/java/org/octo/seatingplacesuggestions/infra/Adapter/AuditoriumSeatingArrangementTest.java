package org.octo.seatingplacesuggestions.infra.Adapter;

import org.junit.jupiter.api.Test;
import org.octo.SeatingPlaceSuggestions.Domain.SeatingPlace;
import org.octo.SeatingPlaceSuggestions.Domain.ShowID;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideAuditoriumSeatingArrangements;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository.AuditoriumLayoutRepositoryAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumSeatingArrangements.AuditoriumSeatingArrangementsAdapter;
import org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider.ReservationsProviderAdapter;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuditoriumSeatingArrangementTest {

    @Test
    public void be_a_Value_Type() throws IOException {
        IProvideAuditoriumSeatingArrangements auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangementsAdapter(new AuditoriumLayoutRepositoryAdapter(), new ReservationsProviderAdapter());

        ShowID showIdWithoutReservationYet = new ShowID("18");
        var auditoriumSeatingFirstInstance =
                auditoriumSeatingArrangements.findByShowId(showIdWithoutReservationYet);
        var auditoriumSeatingSecondInstance =
                auditoriumSeatingArrangements.findByShowId(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        assertThat(auditoriumSeatingSecondInstance)
                .isEqualTo(auditoriumSeatingFirstInstance);

        // Should not mutate existing instances
        SeatingPlace seatAllocated = auditoriumSeatingSecondInstance.getRows().values().iterator()
                .next().seatingPlaces().getFirst().allocate();
        assertThat(seatAllocated).isInstanceOf(SeatingPlace.class);
        assertThat(auditoriumSeatingSecondInstance)
                .isEqualTo(auditoriumSeatingFirstInstance);
    }
}
