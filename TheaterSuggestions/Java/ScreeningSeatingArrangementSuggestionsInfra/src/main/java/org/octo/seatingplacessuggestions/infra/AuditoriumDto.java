package org.octo.seatingplacessuggestions.infra;



import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuditoriumDto(@JsonProperty("Rows") Map<String, List<SeatDto>> rows, @JsonProperty("Corridors") List<CorridorDto> corridors) {
}
