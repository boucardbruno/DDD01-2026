package org.octo.seatingplacessuggestions.domain.domain.seatingplacesuggestions.deepmodel;

import org.octo.seatingplacessuggestions.domain.domain.seatingplacesuggestions.SeatingPlace;

public record SeatingPlaceWithDistance(SeatingPlace SeatingPlace, int DistanceFromTheMiddleOfTheRow) {
}
