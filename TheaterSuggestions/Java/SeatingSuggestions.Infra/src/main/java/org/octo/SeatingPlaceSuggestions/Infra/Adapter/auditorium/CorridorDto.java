package org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CorridorDto(@JsonProperty("Number") int number,
                          @JsonProperty("InvolvedRowNames") Iterable<String> involvedRowNames) {

}
