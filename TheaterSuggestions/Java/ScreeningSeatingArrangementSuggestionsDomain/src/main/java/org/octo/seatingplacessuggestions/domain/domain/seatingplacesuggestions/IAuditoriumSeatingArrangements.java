package org.octo.seatingplacessuggestions.domain.domain.seatingplacesuggestions;

public interface IAuditoriumSeatingArrangements {
    AuditoriumSeatingArrangement findByShowId(ShowID showId);
}
