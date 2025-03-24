package org.octo.seatingplacessuggestions;

import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlace;

import java.util.List;

public record GroupOfAdjacentSeats(List<SeatingPlace> SeatingPlaces, int SumOfDistance)
{
}
