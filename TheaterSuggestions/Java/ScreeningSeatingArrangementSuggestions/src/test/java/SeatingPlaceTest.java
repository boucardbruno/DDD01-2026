import org.junit.jupiter.api.Test;
import org.octo.seatingplacesuggestions.PricingCategory;
import org.octo.seatingplacesuggestions.SeatingPlace;
import org.octo.seatingplacesuggestions.SeatingPlaceAvailability;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SeatingPlaceTest {

    @Test
    public void Be_a_Value_Type() {
        SeatingPlace firstInstance = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        SeatingPlace secondInstance = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);

        // Two different instances with same values should be equals
        assertThat(secondInstance).isEqualTo(firstInstance);

        // Should not mutate existing instance
        secondInstance.allocate();
        assertThat(secondInstance).isEqualTo(firstInstance);
    }
}
