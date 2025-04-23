using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SeatsSuggestions;
using SeatsSuggestions.Port;

namespace SeatingArrangement.Api.Controller;

[ApiController]
[Route("api/SeatingSuggestions")]
public class SeatingArrangementController(
    ISeatingArrangementRecommenderSuggestions hexagon)
    : ControllerBase
{
    // GET api/SeatingSuggestions?showId=5&party=3
    [HttpGet]
    public IResult Get([FromQuery(Name = "showId")] string showId, [FromQuery(Name = "party")] int party)
    {
        // Infra => Domain
        var id = new ShowId(showId);
        var partyRequested = new PartyRequested(party);
        
        var suggestionsMade = hexagon.MakeSuggestions(id, partyRequested);

        // Domain => Infra
        return Results.Ok(JsonConvert.SerializeObject(suggestionsMade, Formatting.Indented));
    }
}