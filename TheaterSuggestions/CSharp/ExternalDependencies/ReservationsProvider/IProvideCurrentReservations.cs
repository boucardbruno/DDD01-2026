namespace ExternalDependencies.ReservationsProvider;

public interface IProvideCurrentReservations
{
    ReservedSeatsDto GetReservedSeats(string showId);
}