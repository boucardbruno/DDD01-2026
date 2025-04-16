package org.octo.seatingplacessuggestions.infra.adapter.reservationsprovider;

public interface IProvideCurrentReservations {

    ReservedSeatsDto getReservedSeats(String showId);
}
