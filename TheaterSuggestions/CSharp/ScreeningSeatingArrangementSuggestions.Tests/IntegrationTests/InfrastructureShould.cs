using NFluent;
using NUnit.Framework;
using SeatingSuggestions.Infra.AuditoriumLayoutRepository;
using SeatingSuggestions.Infra.ReservationsProvider;

namespace SeatsSuggestions.Tests.IntegrationTests;

[TestFixture]
public class InfrastructureShould
{
    [Test]
    public void Allow_us_to_retrieve_reserved_seats_for_a_given_ShowId()
    {
        var seatsRepository = new ReservationsProviderAdapter();
        var reservedSeatsDto = seatsRepository.GetReservedSeats("1");

        Check.That(reservedSeatsDto!.ReservedSeats).HasSize(19);
    }

    [Test]
    public void Allow_us_to_retrieve_AuditoriumLayout_for_a_given_ShowId()
    {
        var eventRepository = new AuditoriumLayoutRepositoryAdapter();
        var theaterDto = eventRepository.GetAuditoriumSeatingFor("2");

        Check.That(theaterDto.Rows).HasSize(6);
        Check.That(theaterDto.Corridors).HasSize(2);
        var firstSeatOfFirstRow = theaterDto.Rows!["A"][0];
        Check.That(firstSeatOfFirstRow.Category).IsEqualTo(2);
    }
}