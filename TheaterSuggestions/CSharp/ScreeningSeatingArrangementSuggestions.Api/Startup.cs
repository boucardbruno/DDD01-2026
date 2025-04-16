using SeatingSuggestions.Infra.AuditoriumLayoutRepository;
using SeatingSuggestions.Infra.AuditoriumSeatingAdapter;
using SeatingSuggestions.Infra.ReservationsProvider;
using SeatsSuggestions;
using SeatsSuggestions.Port;

namespace SeatingArrangement.Api;

public class Startup
{
    // This method gets called by the runtime. Use this method to add services to the container.
    public void ConfigureServices(IServiceCollection services)
    {
        services.AddControllers();

        var auditoriumLayoutRepository = new AuditoriumLayoutRepository();
        var reservationsProvider = new ReservationsProvider();
        var seatingArrangementRecommender = new SeatingArrangementRecommender(
            new AuditoriumSeatingAdapter(auditoriumLayoutRepository, reservationsProvider));

        services.AddSingleton<ISeatingArrangementRecommenderSuggestions>(seatingArrangementRecommender);
    }

    // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
    public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
    {
        if (env.IsDevelopment()) app.UseDeveloperExceptionPage();

        app.UseRouting();

        app.UseAuthorization();

        app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
    }
}