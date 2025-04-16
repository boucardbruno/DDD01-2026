package org.octo.seatingplacessuggestions.infra.adapter.auditorium;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CorridorDto(@JsonProperty("Number") int number,
                          @JsonProperty("InvolvedRowNames") Iterable<String> involvedRowNames) {

}
