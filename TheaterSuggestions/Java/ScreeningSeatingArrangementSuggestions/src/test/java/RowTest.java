import org.junit.jupiter.api.Test;
import org.octo.seatingplacesuggestions.PricingCategory;
import org.octo.seatingplacesuggestions.Row;
import org.octo.seatingplacesuggestions.SeatingPlace;
import org.octo.seatingplacesuggestions.SeatingPlaceAvailability;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RowTest {
    @Test
    public void Be_a_Value_Type() {
        SeatingPlace firstInstance = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        SeatingPlace secondInstance = new SeatingPlace("A", 1, PricingCategory.SECOND, SeatingPlaceAvailability.AVAILABLE);
        Row row1 = new Row("A", List.of(firstInstance, secondInstance));
        Row row2 = new Row("A", List.of(firstInstance, secondInstance));
        // Two different instances with same values should be equals
        assertThat(row1).isEqualTo(row2);
    }
}
