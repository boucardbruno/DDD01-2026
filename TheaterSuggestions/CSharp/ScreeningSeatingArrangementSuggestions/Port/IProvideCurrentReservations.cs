using ExternalDependencies.ReservationsProvider;

namespace SeatsSuggestions.Port;

public interface IProvideCurrentReservations
{
    ReservedSeatsDto GetReservedSeats(string showId);
}