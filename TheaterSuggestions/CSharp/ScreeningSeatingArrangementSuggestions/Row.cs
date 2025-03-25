using SeatsSuggestions.DeepModeling.AdjacentSeatingPlace;
using SeatsSuggestions.DeepModeling.MiddleOfTheRow;
using Value;

namespace SeatsSuggestions;

public class Row(string name, List<SeatingPlace> seatingPlaces) : ValueType<Row>
{
    private string Name { get; } = name;
    public List<SeatingPlace> SeatingPlaces { get; } = seatingPlaces;

    public SeatingOptionIsSuggested SuggestSeatingOption(int partyRequested, PricingCategory pricingCategory)
    { 
        var seatAllocation = new SeatingOptionIsSuggested(partyRequested, pricingCategory);
        
        foreach (var seat in OfferAdjacentSeatsNearerTheMiddleOfRow(partyRequested, pricingCategory))
        {
            if (seat.IsAvailable() && seat.MatchCategory(pricingCategory))
            {
                seatAllocation.AddSeat(seat);
                if (seatAllocation.MatchExpectation())
                {
                    return seatAllocation;
                }
            }
        }
        return new SeatingOptionIsNotAvailable(partyRequested, pricingCategory);
    }

    public Row AddSeatingPlace(SeatingPlace seatingPlace)
    {
        return new Row(Name, [..SeatingPlaces, seatingPlace]);
    }

    protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
    {
        return [Name, new ListByValue<SeatingPlace>(SeatingPlaces)];
    }

    public Row Allocate(SeatingPlace seatingPlacesSuggested)
    {
        var seatingPlaces = SeatingPlaces.Select(seatingPlace => seatingPlacesSuggested.IsSameLocation(seatingPlace)
                ? seatingPlacesSuggested.Allocate()
                : seatingPlace)
            .ToList();
        
        return new Row(Name, seatingPlaces);
    }
    
    private IEnumerable<SeatingPlace> OfferAdjacentSeatsNearerTheMiddleOfRow(int partyRequested, PricingCategory pricingCategory)
    {
        var offerSeatsNearerTheMiddleOfTheRow = TheMiddleOfTheRow.OfferSeatsNearerTheMiddleOfTheRow(this);

        if (partyRequested > 1)
        {
            var offerAdjacentSeatsNearerTheMiddleOfRow = AdjacentSeatingPlaces
                .OfferAdjacentSeatingPlace(offerSeatsNearerTheMiddleOfTheRow, pricingCategory, partyRequested);
            
            return offerAdjacentSeatsNearerTheMiddleOfRow;
        }

        return offerSeatsNearerTheMiddleOfTheRow
            .OrderBy(s => s.DistanceFromTheMiddleOfTheRow)
            .Select(s => s.SeatingPlace)
            .Where(s => s.MatchCategory(pricingCategory))
            .Where(s => s.IsAvailable())
            .Take(partyRequested)
            .ToList();
    }
}