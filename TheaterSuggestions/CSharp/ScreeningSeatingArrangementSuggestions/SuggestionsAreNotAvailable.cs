namespace SeatsSuggestions;

/// <summary>
///     Occurs when a Suggestion that does not meet expectation is made.
/// </summary>
public class SuggestionsAreNotAvailable(ShowId showId, PartyRequested partyRequested)
    : SuggestionsAreMade(showId, partyRequested);