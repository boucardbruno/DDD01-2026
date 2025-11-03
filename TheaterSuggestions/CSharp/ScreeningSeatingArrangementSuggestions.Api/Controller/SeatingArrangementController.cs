using Microsoft.AspNetCore.Mvc;
using SeatsSuggestions.DrivingPort;

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
        return Results.Ok(hexagon.MakeSuggestions(showId, party));
    }
}