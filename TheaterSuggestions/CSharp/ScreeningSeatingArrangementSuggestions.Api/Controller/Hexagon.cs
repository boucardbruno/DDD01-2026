using Newtonsoft.Json;
using SeatingSuggestions.Infra.AuditoriumSeatingAdapter;
using SeatsSuggestions.DrivingPort;

namespace SeatsSuggestions.Tests.AcceptanceTests;

public class Hexagon(AuditoriumSeatingArrangementAdapter auditoriumSeatingArrangements) : ISeatingArrangementRecommenderSuggestions
{
    private readonly SeatingArrangementRecommender _seatingArrangementRecommender = new(auditoriumSeatingArrangements);

    public string MakeSuggestions(string id, int party)
    {
        // Infra => Domain
        var showId = new ShowId(id);
        var partyRequested = new PartyRequested(party);
        
        var suggestionsAreMade = _seatingArrangementRecommender.MakeSuggestions(showId, partyRequested);
        
        // Domain => Infra
        return JsonConvert.SerializeObject(suggestionsAreMade, Formatting.Indented);
    }
}