namespace SeatsSuggestions;

public class SeatingOptionIsSuggested(PartyRequested partyRequested, PricingCategory priceCategory)
{
    public PricingCategory PricingCategory { get; } = priceCategory;
    public List<SeatingPlace> Seats { get; } = new();
    public PartyRequested PartyRequested { get; } = partyRequested;

    public void AddSeat(SeatingPlace seatingPlace)
    {
        Seats.Add(seatingPlace);
    }

    public bool MatchExpectation()
    {
        return Seats.Count == PartyRequested.Party;
    }
}