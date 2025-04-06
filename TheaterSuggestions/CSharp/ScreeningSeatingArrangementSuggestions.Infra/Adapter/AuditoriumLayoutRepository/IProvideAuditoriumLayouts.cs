
namespace ScreeningSeatingArrangementSuggestions.Infra.Adapter.AuditoriumLayoutRepository;

public interface IProvideAuditoriumLayouts
{
    AuditoriumDto GetAuditoriumSeatingFor(string showId);
}