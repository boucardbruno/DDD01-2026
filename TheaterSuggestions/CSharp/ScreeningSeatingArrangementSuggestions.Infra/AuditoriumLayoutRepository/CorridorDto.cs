namespace SeatingSuggestions.Infra.AuditoriumLayoutRepository;

public class CorridorDto
{
    public int Number { get; set; }
    public IEnumerable<string>? InvolvedRowNames { get; set; } = new List<string>();
}