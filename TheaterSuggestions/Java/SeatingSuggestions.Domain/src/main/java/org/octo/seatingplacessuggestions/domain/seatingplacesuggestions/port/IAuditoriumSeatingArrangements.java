package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.port;

import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.AuditoriumSeatingArrangement;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.ShowID;

public interface IAuditoriumSeatingArrangements {
    AuditoriumSeatingArrangement findByShowId(ShowID showId);
}
