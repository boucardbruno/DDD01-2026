using ExternalDependencies.ReservationsProvider;
using Microsoft.AspNetCore.Mvc;

namespace SeatReservations.Api.Controllers;

[Route("api/data_for_reservation_seats/")]
[ApiController]
public class ReservationSeatsController(IProvideCurrentReservations provideCurrentReservations) : ControllerBase
{
    // GET api/data_for_reservation_seats/5
    [HttpGet("{showId}")]
    public Task<ReservedSeatsDto> Get(string showId)
    {
        return Task.FromResult(provideCurrentReservations.GetReservedSeats(showId));
    }
}