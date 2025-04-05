package org.octo.seatingplacessuggestions.infra;

public interface IProvideAuditoriumLayouts {
    AuditoriumDto findByShowId(String showId);
}
