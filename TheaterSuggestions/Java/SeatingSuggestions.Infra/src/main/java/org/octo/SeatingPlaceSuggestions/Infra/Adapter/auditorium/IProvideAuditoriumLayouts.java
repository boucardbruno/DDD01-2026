package org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium;

public interface IProvideAuditoriumLayouts {
    AuditoriumDto findByShowId(String showId);
}
