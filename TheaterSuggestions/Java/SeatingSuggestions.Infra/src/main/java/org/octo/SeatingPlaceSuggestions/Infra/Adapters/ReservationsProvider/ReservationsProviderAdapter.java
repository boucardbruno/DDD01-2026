package org.octo.SeatingPlaceSuggestions.Infra.Adapters.ReservationsProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Paths.get;
import static org.octo.SeatingPlaceSuggestions.Infra.Adapters.StubDirectory.getStubDirectory;

public class ReservationsProviderAdapter implements IProvideCurrentReservations {

    private final Map<String, ReservedSeatsDto> repository = new HashMap<>();

    public ReservationsProviderAdapter() throws IOException {

        var jsonDirectory = getStubDirectory();

        try (var directoryStream = Files.newDirectoryStream(get(jsonDirectory))) {

            for (Path path : directoryStream) {
                if (path.toString().contains("_booked_seats.json")) {
                    var fileName = path.getFileName().toString();
                    var mapper = new ObjectMapper();
                    repository.put(fileName.split("-")[0], mapper.readValue(path.toFile(), ReservedSeatsDto.class));
                }

            }
        }
    }

    @Override
    public ReservedSeatsDto getReservedSeats(String showId) {
        if (repository.containsKey(showId)) {
            return repository.get(showId);
        }
        return new ReservedSeatsDto(Collections.emptyList());
    }
}