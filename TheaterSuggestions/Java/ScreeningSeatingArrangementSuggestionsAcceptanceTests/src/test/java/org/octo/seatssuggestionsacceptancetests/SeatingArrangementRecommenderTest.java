package org.octo.seatssuggestionsacceptancetests;

import org.octo.externaldependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import org.octo.externaldependencies.reservationsprovider.ReservationsProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.assertj.core.api.Assertions.assertThat;

class SeatingArrangementRecommenderTest {/*
     *  Business Rule - Only Suggest available seats
     */
    @Test
    void suggest_one_seatingPlace_when_Auditorium_contains_one_available_seatingPlace() throws IOException {
        // Ford Auditorium-1
        //       1   2   3   4   5   6   7   8   9  10
        //  A : (2) (2)  1  (1) (1) (1) (1) (1) (2) (2)
        //  B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
        final String showId = "1";
        final int partyRequested = 1;

        var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());

        // Make this assertion real to the expected one with outcomes:
        var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
        var suggestionsAreMade = seatingArrangementRecommender.makeSuggestion(showId, partyRequested);

        assertThat(suggestionsAreMade.seatNames(PricingCategory.FIRST)).containsExactly("A3");
    }

    @Test
    void return_SuggestionNotAvailable_when_Auditorium_has_all_its_seatingPlaces_reserved() throws IOException {
        // Madison Auditorium-5
        //      1   2   3   4   5   6   7   8   9  10
        // A : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
        // B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
        final String showId = "5";
        final int partyRequested = 1;

        var auditoriumSeatingArrangements =
                new AuditoriumSeatingArrangements(new AuditoriumLayoutRepository(), new ReservationsProvider());

        // Make this assertion real to the expected one with outcomes: SuggestionNotAvailable
        var seatingArrangementRecommender = new SeatingArrangementRecommender(auditoriumSeatingArrangements);
        var suggestionsAreMade = seatingArrangementRecommender.makeSuggestion(showId, partyRequested);

        assertThat(suggestionsAreMade).isInstanceOf(SuggestionsAreAreNotAvailable.class);
    }
}



