using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SeatsSuggestions;
using SeatsSuggestions.Port;

namespace SeatingArrangement.Api.Controllers;

[ApiController]
[Route("[controller]")]
public class SeatingArrangementController(
    ISeatingArrangementRecommenderSuggestions seatingArrangementRecommenderSuggestions)
    : ControllerBase
{
    // GET api/SeatsSuggestions?showId=5&party=3
    [HttpGet]
    public Task<string> Get([FromQuery(Name = "showId")] string showId, [FromQuery(Name = "party")] int party)
    {
        // Infra => Domain
        var id = new ShowId(showId);
        var partyRequested = new PartyRequested(party);
        var suggestions = seatingArrangementRecommenderSuggestions.MakeSuggestions(id, partyRequested);
        // Domain => Infra
        return Task.FromResult(JsonConvert.SerializeObject(suggestions, Formatting.Indented));
    }
}