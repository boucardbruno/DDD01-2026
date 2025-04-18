package org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditoriumseating;

import org.octo.SeatingPlaceSuggestions.Domain.*;
import org.octo.SeatingPlaceSuggestions.Domain.port.IProvideAuditoriumSeatingArrangements;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.AuditoriumDto;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.AuditoriumLayoutRepository;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.IProvideAuditoriumLayouts;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.auditorium.SeatDto;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.IProvideCurrentReservations;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.ReservationsProvider;
import org.octo.SeatingPlaceSuggestions.Infra.Adapter.reservationsprovider.ReservedSeatsDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuditoriumSeatingArrangements implements IProvideAuditoriumSeatingArrangements {

    private final IProvideCurrentReservations reservedSeatsRepository;
    private final IProvideAuditoriumLayouts auditoriumLayoutRepository;


    public AuditoriumSeatingArrangements(IProvideAuditoriumLayouts auditoriumLayoutRepository, IProvideCurrentReservations reservationsProvider) {
        this.auditoriumLayoutRepository = auditoriumLayoutRepository;
        this.reservedSeatsRepository = reservationsProvider;
    }

    private static PricingCategory convertCategory(int seatDtoCategory) {
        return PricingCategory.valueOf(seatDtoCategory);
    }

    private static int extractNumber(String name) {
        return Integer.parseUnsignedInt(name.substring(1));
    }

    @Override
    public AuditoriumSeatingArrangement findByShowId(ShowID showId) {
        return adapt(auditoriumLayoutRepository.findByShowId(showId.id()),
                reservedSeatsRepository.getReservedSeats(showId.id()));

    }

    private AuditoriumSeatingArrangement adapt(AuditoriumDto auditoriumDto, ReservedSeatsDto reservedSeatsDto) {

        var rows = new HashMap<String, Row>();

        for (Map.Entry<String, List<SeatDto>> rowDto : auditoriumDto.rows().entrySet()) {
            List<SeatingPlace> seats = new ArrayList<>();

            rowDto.getValue().forEach(seatDto -> {
                var rowName = rowDto.getKey();
                var number = extractNumber(seatDto.name());
                var pricingCategory = convertCategory(seatDto.category());

                var isReserved = reservedSeatsDto.reservedSeats().contains(seatDto.name());

                seats.add(new SeatingPlace(rowName, number, pricingCategory, isReserved ? SeatingPlaceAvailability.RESERVED : SeatingPlaceAvailability.AVAILABLE));
            });

            rows.put(rowDto.getKey(), new Row(rowDto.getKey(), seats));
        }

        return new AuditoriumSeatingArrangement(rows);
    }

}
