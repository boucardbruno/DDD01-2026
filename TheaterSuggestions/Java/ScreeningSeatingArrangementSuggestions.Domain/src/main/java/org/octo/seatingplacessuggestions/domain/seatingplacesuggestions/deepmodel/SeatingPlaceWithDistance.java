package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.deepmodel;

import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.SeatingPlace;

public record SeatingPlaceWithDistance(SeatingPlace SeatingPlace, int DistanceFromTheMiddleOfTheRow) {
}
