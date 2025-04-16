using SeatingSuggestions.Infra.AuditoriumLayoutRepository;
using SeatingSuggestions.Infra.ReservationsProvider;
using SeatsSuggestions;
using SeatsSuggestions.Port;

namespace SeatingSuggestions.Infra.AuditoriumSeatingAdapter;

public class AuditoriumSeatingAdapter(
    IProvideAuditoriumLayouts auditoriumLayoutRepository,
    IProvideCurrentReservations reservationsProvider) : IAdaptAuditoriumSeating
{
    public AuditoriumSeatingArrangement FindByShowId(ShowId showId)
    {
        return Adapt(auditoriumLayoutRepository.GetAuditoriumSeatingFor(showId.Identifier),
            reservationsProvider.GetReservedSeats(showId.Identifier));
    }

    private static AuditoriumSeatingArrangement Adapt(AuditoriumDto auditoriumDto, ReservedSeatsDto? reservedSeatsDto)
    {
        var rows = new Dictionary<string, Row>();

        foreach (var rowDto in auditoriumDto.Rows!)
        {
            foreach (var seatDto in rowDto.Value)
            {
                var rowName = ExtractRowName(seatDto.Name);
                var number = ExtractNumber(seatDto.Name);
                var pricingCategory = ConvertCategory(seatDto.Category);

                var isReserved = reservedSeatsDto!.ReservedSeats.Contains(seatDto.Name);

                if (!rows.ContainsKey(rowName)) rows[rowName] = new Row(rowName, []);

                rows[rowName].SeatingPlaces.Add(new SeatingPlace(rowName, number, pricingCategory,
                    isReserved ? SeatingPlaceAvailability.Reserved : SeatingPlaceAvailability.Available));
            }
        }

        return new AuditoriumSeatingArrangement(rows);
    }

    private static PricingCategory ConvertCategory(int seatDtoCategory)
    {
        return (PricingCategory)seatDtoCategory;
    }

    private static int ExtractNumber(string name)
    {
        return int.Parse(name[1..]);
    }

    private static string ExtractRowName(string name)
    {
        return name[0].ToString();
    }
}