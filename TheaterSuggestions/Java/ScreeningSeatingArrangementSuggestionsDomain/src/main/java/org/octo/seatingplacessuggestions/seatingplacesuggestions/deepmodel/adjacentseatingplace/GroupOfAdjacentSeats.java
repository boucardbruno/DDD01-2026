package org.octo.seatingplacessuggestions.seatingplacesuggestions.deepmodel.adjacentseatingplace;

import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlace;

import java.util.List;

public record GroupOfAdjacentSeats(List<SeatingPlace> SeatingPlaces, int SumOfDistance)
{
}
