using SeatingSuggestions.Infra.AuditoriumLayoutRepository;
using SeatingSuggestions.Infra.AuditoriumSeatingAdapter;
using SeatingSuggestions.Infra.ReservationsProvider;
using SeatsSuggestions;
using SeatsSuggestions.DrivingPort;
using SeatsSuggestions.Tests.AcceptanceTests;

namespace SeatingArrangement.Api;

public static class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);
        
        builder.Services.AddControllers();
        builder.Services.AddOpenApi();

        builder.Services.AddSingleton(BuildSeatingArrangementRecommender());

        var app = builder.Build();

        if (app.Environment.IsDevelopment())
        {
            app.MapOpenApi();
            app.UseSwaggerUI(options =>
            {
                options.SwaggerEndpoint("/openapi/v1.json", "version 1");

            });
            app.UseDeveloperExceptionPage();
        }

        app.UseAuthorization();
        app.MapControllers();

        app.Run();
    }

    private static ISeatingArrangementRecommenderSuggestions BuildSeatingArrangementRecommender() =>
        new Hexagon(
            new AuditoriumSeatingArrangementAdapter(
                new AuditoriumLayoutRepositoryAdapter(), 
                new ReservationsProviderAdapter()));
}