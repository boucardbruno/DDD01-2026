using System.Collections.Generic;

namespace SeatsSuggestions.Tests;

public class SeatingOptionIsSuggested(int partyRequested, PricingCategory priceCategory)
{
    public PricingCategory PricingCategory { get; } = priceCategory;
    public List<SeatingPlace> Seats { get; } = new();
    public int PartyRequested { get; } = partyRequested;

    public void AddSeat(SeatingPlace seatingPlace)
    {
        Seats.Add(seatingPlace);
    }

    public bool MatchExpectation()
    {
        return Seats.Count == PartyRequested;
    }
}