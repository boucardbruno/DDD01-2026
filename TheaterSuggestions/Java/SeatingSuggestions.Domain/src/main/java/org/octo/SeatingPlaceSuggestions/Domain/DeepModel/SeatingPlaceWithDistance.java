package org.octo.SeatingPlaceSuggestions.Domain.DeepModel;

import org.octo.SeatingPlaceSuggestions.Domain.SeatingPlace;

public record SeatingPlaceWithDistance(SeatingPlace SeatingPlace, int DistanceFromTheMiddleOfTheRow) {
}
