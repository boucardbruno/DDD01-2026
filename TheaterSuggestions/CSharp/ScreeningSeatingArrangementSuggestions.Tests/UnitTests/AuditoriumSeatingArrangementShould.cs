using System.Linq;
using NFluent;
using NUnit.Framework;
using SeatingSuggestions.Infra.AuditoriumLayoutRepository;
using SeatingSuggestions.Infra.AuditoriumSeatingAdapter;
using SeatingSuggestions.Infra.ReservationsProvider;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class AuditoriumSeatingArrangementShould
{
    [Test]
    public void Be_a_Value_Type()
    {
        var auditoriumLayoutAdapter =
            new AuditoriumSeatingAdapter(new AuditoriumLayoutRepository(), new ReservationsProvider());
        var showIdWithoutReservationYet = new ShowId("18");
        var auditoriumSeatingFirstInstance =
            auditoriumLayoutAdapter.FindByShowId(showIdWithoutReservationYet);
        var auditoriumSeatingSecondInstance =
            auditoriumLayoutAdapter.FindByShowId(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        Check.That(auditoriumSeatingSecondInstance).IsEqualTo(auditoriumSeatingFirstInstance);

        // Should not mutate existing instance 
        var seatingPlace = auditoriumSeatingSecondInstance.Rows.Values.First().SeatingPlaces.First().Allocate();
        Check.That(seatingPlace).IsInstanceOf<SeatingPlace>();
        Check.That(auditoriumSeatingSecondInstance).IsEqualTo(auditoriumSeatingFirstInstance);
    }
}