namespace SeatsSuggestions;

public interface ISeatingArrangementRecommenderSuggestion
{
    SuggestionsAreMade MakeSuggestions(ShowId showId, PartyRequested partyRequested);
}