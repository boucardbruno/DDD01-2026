package org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ReservedSeatsDto(@JsonProperty("ReservedSeats") List<String> reservedSeats) {
}
