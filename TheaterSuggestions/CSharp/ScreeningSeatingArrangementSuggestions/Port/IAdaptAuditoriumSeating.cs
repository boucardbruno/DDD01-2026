namespace SeatsSuggestions.Port;

public interface IAdaptAuditoriumSeating
{
    AuditoriumSeatingArrangement FindByShowId(ShowId showId);
}