using System.Linq;
using NFluent;
using NUnit.Framework;
using ScreeningSeatingArrangementSuggestions.Infra;
using ScreeningSeatingArrangementSuggestions.Infra.Adapter;
using ScreeningSeatingArrangementSuggestions.Infra.Adapter.AuditoriumLayoutRepository;
using ScreeningSeatingArrangementSuggestions.Infra.Adapter.AuditoriumSeatingAdaptater;
using ScreeningSeatingArrangementSuggestions.Infra.Adapter.ReservationsProvider;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class AuditoriumSeatingArrangementsShould
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
        auditoriumSeatingSecondInstance.Rows.Values.First().SeatingPlaces.First().Allocate();
        Check.That(auditoriumSeatingSecondInstance).IsEqualTo(auditoriumSeatingFirstInstance);
    }
}