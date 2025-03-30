using ExternalDependencies.AuditoriumLayoutRepository;
using ExternalDependencies.ReservationsProvider;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SeatsSuggestions;

namespace SeatingArrangement.Api.Controllers;

[ApiController]
[Route("[controller]")]
public class SeatingArrangementController(
    IProvideAuditoriumLayouts auditoriumSeatingRepository,
    IProvideCurrentReservations seatReservationsProvider)
    : ControllerBase
{
    // GET api/SeatsSuggestions?showId=5&party=3
    [HttpGet]
    public Task<string> Get([FromQuery(Name = "showId")] string showId, [FromQuery(Name = "party")] int party)
    {
        var seatingArrangementRecommender =
            new SeatingArrangementRecommender(new AuditoriumSeatingArrangements(auditoriumSeatingRepository, seatReservationsProvider));
        var suggestions = seatingArrangementRecommender.MakeSuggestions(new ShowId(showId), new PartyRequested(party));

        return Task.FromResult(JsonConvert.SerializeObject(suggestions, Formatting.Indented));
    }
}