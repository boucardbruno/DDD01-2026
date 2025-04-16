namespace SeatingSuggestions.Infra.AuditoriumLayoutRepository;

public class SeatDto
{
    public SeatDto(string name, int category)
    {
        Name = name;
        Category = category;
    }

    public string Name { get; }
    public int Category { get; }
}