using SeatsSuggestions.DrivenPort;
using SeatsSuggestions.DrivingPort;

namespace SeatsSuggestions;

public class SeatingArrangementRecommender(IAdaptAuditoriumSeating auditoriumSeatingArrangements)
    : ISeatingArrangementRecommenderSuggestions
{
    private const int NumberOfSuggestions = 3;

    public SuggestionsAreMade MakeSuggestions(ShowId showId, PartyRequested partyRequested)
    {
        var auditoriumSeating = auditoriumSeatingArrangements.FindByShowId(showId);

        var suggestionsMade = new SuggestionsAreMade(showId, partyRequested);

        foreach (var pricingCategory in Enum.GetValues<PricingCategory>())
            suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, pricingCategory));

        return suggestionsMade.MatchExpectations() ? 
            suggestionsMade : 
            new SuggestionsAreNotAvailable(showId, partyRequested);
    }

    private static IEnumerable<SuggestionIsMade> GiveMeSuggestionsFor(
        AuditoriumSeatingArrangement auditoriumSeatingArrangement,
        PartyRequested partyRequested,
        PricingCategory pricingCategory)
    {
        var foundedSuggestions = new List<SuggestionIsMade>();

        for (var i = 0; i < NumberOfSuggestions; i++)
        {
            var seatingOptionSuggested =
                auditoriumSeatingArrangement.SuggestSeatingOptionFor(partyRequested, pricingCategory);

            if (seatingOptionSuggested.MatchExpectation())
            {
                auditoriumSeatingArrangement = auditoriumSeatingArrangement.Allocate(seatingOptionSuggested.Seats);

                foundedSuggestions.Add(new SuggestionIsMade(seatingOptionSuggested));
            }
        }

        return foundedSuggestions;
    }
}