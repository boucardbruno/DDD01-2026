import org.junit.jupiter.api.Test;
import org.octo.externaldependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import org.octo.externaldependencies.reservationsprovider.ReservationsProvider;
import org.octo.seatingplacesuggestions.AuditoriumSeatingArrangements;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuditoriumSeatingArrangementTest {
    @Test
    public void be_a_Value_Type() throws IOException {
        var auditoriumLayoutAdapter =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());
        var showIdWithoutReservationYet = "18";
        var auditoriumSeatingFirstInstance =
                auditoriumLayoutAdapter.findByShowId(showIdWithoutReservationYet);
        var auditoriumSeatingSecondInstance =
                auditoriumLayoutAdapter.findByShowId(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        assertThat(auditoriumSeatingSecondInstance).isEqualTo(auditoriumSeatingFirstInstance);

        // Two different instances with same values should be equals
        assertThat(auditoriumSeatingSecondInstance).isEqualTo(auditoriumSeatingFirstInstance);

        // Should not mutate existing instance
        auditoriumSeatingSecondInstance.getRows().values().iterator().next().getSeatingPlaces().iterator().next().allocate();
        assertThat(auditoriumSeatingSecondInstance).isEqualTo(auditoriumSeatingFirstInstance);
    }
}
