using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class RowShould
{
    [Test]
    public void Be_a_Value_Type()
    {
        var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);

        // Two different instances with same values should be equals
        var rowFirstInstance = new Row("A", [a1, a2]);
        var rowSecondInstance = new Row("A", [a1, a2]);
        Check.That(rowSecondInstance).IsEqualTo(rowFirstInstance);

        // Should not mutate existing instance 
        var a3 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var rowWithNewSeatingPlace = rowSecondInstance.AddSeatingPlace(a3);
        Check.That(rowSecondInstance).IsEqualTo(rowFirstInstance);
        Check.That(rowWithNewSeatingPlace).IsNotEqualTo(rowFirstInstance);
    }

   
}
   