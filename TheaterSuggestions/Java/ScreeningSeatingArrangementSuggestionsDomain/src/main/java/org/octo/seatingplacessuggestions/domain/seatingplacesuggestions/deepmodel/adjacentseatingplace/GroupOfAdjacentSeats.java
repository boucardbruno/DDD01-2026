package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.deepmodel.adjacentseatingplace;

import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.SeatingPlace;

import java.util.List;

public record GroupOfAdjacentSeats(List<SeatingPlace> SeatingPlaces, int SumOfDistance)
{
}
