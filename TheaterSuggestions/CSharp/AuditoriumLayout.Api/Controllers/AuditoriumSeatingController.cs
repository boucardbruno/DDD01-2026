using ExternalDependencies.AuditoriumLayoutRepository;
using Microsoft.AspNetCore.Mvc;

namespace AuditoriumLayout.Api.Controllers;

[Route("api/data_for_auditoriumSeating/")]
[ApiController]
public class AuditoriumSeatingController(IProvideAuditoriumLayouts provideAuditoriumLayouts) : ControllerBase
{  

    // GET api/data_for_auditoriumSeating/5
    [HttpGet("{showId}")]
    public Task<ActionResult<AuditoriumDto>> Get(string showId)
    {
        return Task.FromResult<ActionResult<AuditoriumDto>>(provideAuditoriumLayouts.GetAuditoriumSeatingFor(showId));
    }
}