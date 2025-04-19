using Microsoft.Extensions.DependencyInjection;
using SeatingSuggestions.Infra.AuditoriumLayoutRepository;
using SeatingSuggestions.Infra.AuditoriumSeatingAdapter;
using SeatingSuggestions.Infra.ReservationsProvider;
using SeatsSuggestions;
using SeatsSuggestions.Port;

namespace SeatingArrangement.Api;

public class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);
        
        builder.Services.AddControllers();
        builder.Services.AddOpenApi();
        builder.Services.AddSwaggerGen();
     
        var auditoriumLayoutRepository = new AuditoriumLayoutRepositoryAdapter();
        var reservationsProvider = new ReservationsProviderAdapter();
        var seatingArrangementRecommender = new SeatingArrangementRecommender(
            new AuditoriumSeatingAdapter(auditoriumLayoutRepository, reservationsProvider));
        builder.Services.AddSingleton<ISeatingArrangementRecommenderSuggestions>(seatingArrangementRecommender);

        var app = builder.Build();

        if (app.Environment.IsDevelopment())
        {
            app.UseSwagger();
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
}