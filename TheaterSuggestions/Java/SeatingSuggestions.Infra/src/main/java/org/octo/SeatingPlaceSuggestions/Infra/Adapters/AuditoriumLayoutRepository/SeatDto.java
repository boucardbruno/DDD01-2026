package org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SeatDto(@JsonProperty("Name") String name, @JsonProperty("Category") int category) {
}
