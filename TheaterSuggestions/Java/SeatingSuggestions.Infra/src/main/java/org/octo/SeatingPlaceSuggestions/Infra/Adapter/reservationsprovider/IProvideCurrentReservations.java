package org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider;

public interface IProvideCurrentReservations {

    ReservedSeatsDto getReservedSeats(String showId);
}
