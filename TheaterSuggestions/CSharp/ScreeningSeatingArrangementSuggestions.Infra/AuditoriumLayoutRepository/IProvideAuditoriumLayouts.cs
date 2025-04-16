namespace SeatingSuggestions.Infra.AuditoriumLayoutRepository;

public interface IProvideAuditoriumLayouts
{
    AuditoriumDto GetAuditoriumSeatingFor(string showId);
}