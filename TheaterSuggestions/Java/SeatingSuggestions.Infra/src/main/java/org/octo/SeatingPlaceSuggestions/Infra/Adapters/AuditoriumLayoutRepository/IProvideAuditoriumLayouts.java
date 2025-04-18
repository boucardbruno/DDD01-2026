package org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository;

public interface IProvideAuditoriumLayouts {
    AuditoriumDto findByShowId(String showId);
}
