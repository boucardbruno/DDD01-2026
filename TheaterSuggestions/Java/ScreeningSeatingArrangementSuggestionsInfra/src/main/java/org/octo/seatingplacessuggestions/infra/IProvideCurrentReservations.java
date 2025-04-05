package org.octo.seatingplacessuggestions.infra;

public interface IProvideCurrentReservations {

    ReservedSeatsDto getReservedSeats(String showId);
}
