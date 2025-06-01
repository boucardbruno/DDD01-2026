namespace SeatsSuggestions.DrivingPort;

public interface ISeatingArrangementRecommenderSuggestions
{
    SuggestionsAreMade MakeSuggestions(ShowId showId, PartyRequested partyRequested);
}