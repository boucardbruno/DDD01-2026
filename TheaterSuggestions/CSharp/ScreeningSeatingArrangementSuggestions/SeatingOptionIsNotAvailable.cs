namespace SeatsSuggestions;

internal class SeatingOptionIsNotAvailable(PartyRequested partyRequested, PricingCategory pricingCategory)
    : SeatingOptionIsSuggested(partyRequested,
        pricingCategory);