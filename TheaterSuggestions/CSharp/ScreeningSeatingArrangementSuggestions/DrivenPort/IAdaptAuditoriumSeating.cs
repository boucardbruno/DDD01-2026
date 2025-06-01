namespace SeatsSuggestions.DrivenPort;

public interface IAdaptAuditoriumSeating
{
    AuditoriumSeatingArrangement FindByShowId(ShowId showId);
}