package org.octo.SeatingPlaceSuggestions.Domain.DrivenPort;

import org.octo.SeatingPlaceSuggestions.Domain.AuditoriumSeatingArrangement;
import org.octo.SeatingPlaceSuggestions.Domain.ShowID;

public interface IProvideAuditoriumSeatingArrangements {
    AuditoriumSeatingArrangement findByShowId(ShowID showId);
}
