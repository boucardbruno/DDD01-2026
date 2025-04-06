
namespace ScreeningSeatingArrangementSuggestions.Infra.Adapter.ReservationsProvider;

public interface IProvideCurrentReservations
{
    ReservedSeatsDto GetReservedSeats(string showId);
}