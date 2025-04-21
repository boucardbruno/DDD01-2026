using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SeatsSuggestions;
using SeatsSuggestions.Port;

namespace SeatingArrangement.Api.Controller;

[ApiController]
[Route("api/SeatingSuggestions")]
public class SeatingArrangementController(
    ISeatingArrangementRecommenderSuggestions seatingArrangementRecommenderSuggestions)
    : ControllerBase
{
    // GET api/SeatingSuggestions?showId=5&party=3
    [HttpGet]
    public IResult Get([FromQuery(Name = "showId")] string showId, [FromQuery(Name = "party")] int party)
    {
        // Infra => Domain
        var id = new ShowId(showId);
        var partyRequested = new PartyRequested(party);
        var suggestions = seatingArrangementRecommenderSuggestions.MakeSuggestions(id, partyRequested);
        // Domain => Infra
        return Results.Ok(JsonConvert.SerializeObject(suggestions, Formatting.Indented));
    }
}