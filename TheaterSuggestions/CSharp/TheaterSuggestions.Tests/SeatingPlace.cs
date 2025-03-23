namespace SeatsSuggestions.Tests;

public class SeatingPlace
{
    public SeatingPlace(string rowName, int number, PricingCategory pricingCategory, SeatingPlaceAvailability seatingPlaceAvailability)
    {
        RowName = rowName;
        Number = number;
        PricingCategory = pricingCategory;
        SeatingPlaceAvailability = seatingPlaceAvailability;
    }

    public string RowName { get; }
    public int Number { get; }
    public PricingCategory PricingCategory { get; }
    private SeatingPlaceAvailability SeatingPlaceAvailability { get; set; }

    public bool IsAvailable => SeatingPlaceAvailability == SeatingPlaceAvailability.Available;

    public override string ToString()
    {
        return $"{RowName}{Number}";
    }

    public void UpdateCategory(SeatingPlaceAvailability seatingPlaceAvailability)
    {
        SeatingPlaceAvailability = seatingPlaceAvailability;
    }
}