namespace SeatsSuggestions.Tests;

public class SeatingArrangementRecommender
{
    public SeatingArrangementRecommender(AuditoriumSeatingArrangements auditoriumSeatingArrangements)
    {
         
    }

    public SuggestionsAreMade MakeSuggestions(string showId, int partyRequested)
    {
        return new SuggestionsAreMade(showId, partyRequested);
    }
}