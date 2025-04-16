namespace SeatingSuggestions.Infra.ReservationsProvider;

public interface IProvideCurrentReservations
{
    ReservedSeatsDto? GetReservedSeats(string showId);
}