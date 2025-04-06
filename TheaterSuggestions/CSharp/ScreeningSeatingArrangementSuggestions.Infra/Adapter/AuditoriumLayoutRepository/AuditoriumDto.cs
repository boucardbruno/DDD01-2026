using ExternalDependencies.AuditoriumLayoutRepository;

namespace ScreeningSeatingArrangementSuggestions.Infra.Adapter.AuditoriumLayoutRepository
{
    public class AuditoriumDto
    {
        public Dictionary<string, IReadOnlyList<SeatDto>> Rows { get; set; }
        public IEnumerable<CorridorDto> Corridors { get; set; }
    }
}