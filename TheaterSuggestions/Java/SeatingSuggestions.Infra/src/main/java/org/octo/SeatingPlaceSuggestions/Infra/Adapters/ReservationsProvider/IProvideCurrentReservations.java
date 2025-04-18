package org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider;

public interface IProvideCurrentReservations {

    ReservedSeatsDto getReservedSeats(String showId);
}
