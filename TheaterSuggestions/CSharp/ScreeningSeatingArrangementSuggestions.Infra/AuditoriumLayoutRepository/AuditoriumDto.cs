namespace SeatingSuggestions.Infra.AuditoriumLayoutRepository;

public class AuditoriumDto
{
    public Dictionary<string, IReadOnlyList<SeatDto>>? Rows { get; } = new();
    public IEnumerable<CorridorDto>? Corridors { get; } = new List<CorridorDto>();
}