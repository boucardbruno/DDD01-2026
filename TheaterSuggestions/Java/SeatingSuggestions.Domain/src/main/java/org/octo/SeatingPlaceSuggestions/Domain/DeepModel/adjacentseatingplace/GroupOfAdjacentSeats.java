package org.octo.SeatingPlaceSuggestions.Domain.DeepModel.adjacentseatingplace;

import org.octo.SeatingPlaceSuggestions.Domain.SeatingPlace;

import java.util.List;

public record GroupOfAdjacentSeats(List<SeatingPlace> SeatingPlaces, int SumOfDistance) {
}
