namespace SeatsSuggestions.Port;

public interface ISeatingArrangementRecommenderSuggestions
{
    SuggestionsAreMade MakeSuggestions(ShowId showId, PartyRequested partyRequested);
}