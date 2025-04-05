package org.octo.seatingplacessuggestions.seatingplacesuggestions.deepmodel.adjacentseatingplace;

import org.octo.seatingplacessuggestions.seatingplacesuggestions.PricingCategory;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlace;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.SeatingPlaceAvailability;
import org.octo.seatingplacessuggestions.seatingplacesuggestions.deepmodel.SeatingPlaceWithDistance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdjacentSeatingPlaces {
    public static List<SeatingPlace> offerAdjacentSeatingPlace(List<SeatingPlaceWithDistance> seatingPlacesWithDistance, PricingCategory pricingCategory, int partySize)
    {
        var potentialAdjacentSeats = new ArrayList<SeatingPlaceWithDistance>();
        var groupsOfAdjacentSeats = new ArrayList<GroupOfAdjacentSeats>();
        var seatingPlacesWithDistancePrevious =
                new SeatingPlaceWithDistance(
                        new SeatingPlace("Z", -1, PricingCategory.IGNORED, SeatingPlaceAvailability.ALLOCATED), -1);

        List<SeatingPlaceWithDistance> seatingPlaceWithDistances = seatingPlacesWithDistance.stream()
                .filter(s -> s.SeatingPlace().matchCategory(pricingCategory))
                .filter(s -> s.SeatingPlace().isAvailable())
                .sorted(Comparator.comparing(s -> s.SeatingPlace().number())).toList();

        for(var seatingPlaceWithDistance : seatingPlaceWithDistances)
        {
            if (areAdjacentSeats(seatingPlacesWithDistancePrevious, seatingPlaceWithDistance))
            {
                if (potentialAdjacentSeats.isEmpty())
                {
                    potentialAdjacentSeats.add(seatingPlacesWithDistancePrevious);
                }

                potentialAdjacentSeats.add(seatingPlaceWithDistance);
            }
            else
            {
                potentialAdjacentSeats.clear();
            }

            seatingPlacesWithDistancePrevious = seatingPlaceWithDistance;

            if (partySize == potentialAdjacentSeats.size())
            {
                seatingPlacesWithDistancePrevious = addGroupOfPlaceWithDistances(groupsOfAdjacentSeats, potentialAdjacentSeats);
            }
        }

        return !groupsOfAdjacentSeats.isEmpty()
                ? selectTheBestGroupOfSeatingPlaces(groupsOfAdjacentSeats)
                : new ArrayList<>();

    }


    public static SeatingPlaceWithDistance addGroupOfPlaceWithDistances(
            List<GroupOfAdjacentSeats> groupsOfAdjacentSeats, List<SeatingPlaceWithDistance> potentialAdjacentSeats)
    {
        var sumOfDistance = (Integer)potentialAdjacentSeats.stream().mapToInt(SeatingPlaceWithDistance::DistanceFromTheMiddleOfTheRow).sum();
        var seatingPlaces = potentialAdjacentSeats.stream().map(SeatingPlaceWithDistance::SeatingPlace);
        groupsOfAdjacentSeats.add(new GroupOfAdjacentSeats(seatingPlaces.toList(), sumOfDistance));
        potentialAdjacentSeats.clear();
        return new SeatingPlaceWithDistance(
                new SeatingPlace("Z", -1, PricingCategory.IGNORED, SeatingPlaceAvailability.ALLOCATED), -1);
    }

    public static Boolean areAdjacentSeats(SeatingPlaceWithDistance seatingPlacesWithDistancePrevious,
                                           SeatingPlaceWithDistance seatingPlaceWithDistance)
    {
        return seatingPlacesWithDistancePrevious.SeatingPlace().number() + 1 ==
                seatingPlaceWithDistance.SeatingPlace().number();
    }

    private static List<SeatingPlace> selectTheBestGroupOfSeatingPlaces(
            List<GroupOfAdjacentSeats> groupsOfAdjacentSeats)
    {
        var bestOfGroupOfAdjacentSeats = groupsOfAdjacentSeats.stream()
                .sorted(Comparator.comparing(GroupOfAdjacentSeats::SumOfDistance))
                .toList();

        return bestOfGroupOfAdjacentSeats.get(0).SeatingPlaces();
    }
}
