package org.octo.seatingplacessuggestions.infra.adapter.auditorium;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SeatDto(@JsonProperty("Name") String name, @JsonProperty("Category") int category) {
}
