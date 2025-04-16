namespace SeatsSuggestions;

public interface ISeatingArrangementRecommenderSuggestions
{
    SuggestionsAreMade MakeSuggestions(ShowId showId, PartyRequested partyRequested);
}