using Microsoft.AspNetCore.Http;
using NFluent;
using NUnit.Framework;
using SeatingArrangement.Api.Controller;
using SeatingSuggestions.Infra.AuditoriumLayoutRepository;
using SeatingSuggestions.Infra.AuditoriumSeatingAdapter;
using SeatingSuggestions.Infra.ReservationsProvider;

namespace SeatsSuggestions.Tests.ControllerTests
{
    internal class SeatingArrangementControllerShould
    {
        private SeatingArrangementController _controller;

        [SetUp]
        public void Setup()
        {
             var auditoriumLayoutRepository = new AuditoriumLayoutRepositoryAdapter(); 
             var reservationsProvider = new ReservationsProviderAdapter();

             var seatingArrangementRecommender = new SeatingArrangementRecommender(
                 new AuditoriumSeatingArrangementAdapter(auditoriumLayoutRepository, reservationsProvider));

             _controller = new SeatingArrangementController(seatingArrangementRecommender);
        }

        [Test]
        public void Suggest_seating_arrangement()
        {
            var result = _controller.Get("18",2);
            Check.That((result as IStatusCodeHttpResult)?.StatusCode).IsEqualTo(200);
        }
    }
}
