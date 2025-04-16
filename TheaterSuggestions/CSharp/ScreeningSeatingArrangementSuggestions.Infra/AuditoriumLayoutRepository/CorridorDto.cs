namespace ScreeningSeatingArrangementSuggestions.Infra.Adapter.AuditoriumLayoutRepository;

public class CorridorDto
{
    public int Number { get; set; }
    public IEnumerable<string> InvolvedRowNames { get; set; }
}