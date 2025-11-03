namespace SeatsSuggestions.DrivingPort;

public interface ISeatingArrangementRecommenderSuggestions
{
    string MakeSuggestions(string id, int party);
}