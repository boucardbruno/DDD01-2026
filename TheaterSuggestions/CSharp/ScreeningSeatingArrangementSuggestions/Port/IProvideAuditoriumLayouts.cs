namespace ExternalDependencies.AuditoriumLayoutRepository;

public interface IProvideAuditoriumLayouts
{
    AuditoriumDto GetAuditoriumSeatingFor(string showId);
}