package org.octo.SeatingPlaceSuggestions.Infra.Adapters.AuditoriumLayoutRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.octo.SeatingPlaceSuggestions.Infra.Adapters.StubDirectory.getStubDirectory;

public class AuditoriumLayoutRepositoryAdapter implements IProvideAuditoriumLayouts {

    private final Map<String, AuditoriumDto> repository = new HashMap<>();

    public AuditoriumLayoutRepositoryAdapter() throws IOException {
        var jsonDirectory = getStubDirectory();
        try (var directoryStream = Files.newDirectoryStream(Paths.get(jsonDirectory))) {

            for (Path path : directoryStream) {
                if (path.toString().contains("_theater.json")) {
                    var fileName = path.getFileName().toString();
                    var mapper = new ObjectMapper();
                    repository.put(fileName.split("-")[0], mapper.readValue(path.toFile(), AuditoriumDto.class));
                }
            }
        }
    }

    @Override
    public AuditoriumDto findByShowId(String showId) {
        if (repository.containsKey(showId)) {
            return repository.get(showId);
        }

        return new AuditoriumDto(Collections.emptyMap(), Collections.emptyList());
    }
}
