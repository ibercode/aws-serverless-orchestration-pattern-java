package com.ibercode.mediator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.ibercode.mediator.communication.InterServiceComm;
import com.ibercode.mediator.model.CinemaResponse;
import com.ibercode.mediator.model.MediatorResponse;
import com.ibercode.mediator.model.MovieResponse;

import java.util.List;

public class MediatorService implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final Gson gson = new Gson();
    private final InterServiceComm interServiceComm = new InterServiceComm();
    private final String CINEMA_MS = System.getenv("CINEMA_MS");
    private final String MOVIE_MS = System.getenv("MOVIE_MS");

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        APIGatewayProxyResponseEvent apiResponse = new APIGatewayProxyResponseEvent();

        //get Cinema from CinemaCatalog Service
        CinemaResponse cinema = interServiceComm.getCinema(event.getPathParameters().get("cinemaId"), CINEMA_MS);

        // get Cinema movies from Movies Service
        List<MovieResponse> movies = interServiceComm.getMoviesForCinema(cinema.getMovies(), MOVIE_MS);

        // set response
        MediatorResponse mediatorResponse = new MediatorResponse(cinema.getName(), movies);

        apiResponse.setBody(gson.toJson(mediatorResponse));
        return apiResponse;
    }
}
