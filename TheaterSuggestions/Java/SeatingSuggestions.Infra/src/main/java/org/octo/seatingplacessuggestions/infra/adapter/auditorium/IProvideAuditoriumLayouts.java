package org.octo.seatingplacessuggestions.infra.adapter.auditorium;

public interface IProvideAuditoriumLayouts {
    AuditoriumDto findByShowId(String showId);
}
