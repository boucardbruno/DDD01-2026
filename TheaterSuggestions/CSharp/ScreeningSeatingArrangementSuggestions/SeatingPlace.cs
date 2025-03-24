namespace SeatsSuggestions;

public class SeatingPlace(
    string rowName,
    int number,
    PricingCategory pricingCategory,
    SeatingPlaceAvailability seatingPlaceAvailability)
{
    public string RowName { get; } = rowName;
    public int Number { get; } = number;
    public PricingCategory PricingCategory { get; } = pricingCategory;
    private SeatingPlaceAvailability SeatingPlaceAvailability { get; set; } = seatingPlaceAvailability;

    public bool IsAvailable()
    {
        return SeatingPlaceAvailability == SeatingPlaceAvailability.Available;
    }

    public override string ToString()
    {
        return $"{RowName}{Number}";
    }

    public bool MatchCategory(PricingCategory pricingCategory)
    {
        if (pricingCategory == PricingCategory.Ignored)
        {
            return true;
        }
        
        return PricingCategory == pricingCategory;
    }

    public void Allocate()
    {
        if (SeatingPlaceAvailability == SeatingPlaceAvailability.Available) SeatingPlaceAvailability = SeatingPlaceAvailability.Allocated;
    }
}