package org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.port;
import org.octo.seatingplacessuggestions.domain.seatingplacesuggestions.*;

public interface IAuditoriumSeatingArrangements {
    AuditoriumSeatingArrangement findByShowId(ShowID showId);
}
