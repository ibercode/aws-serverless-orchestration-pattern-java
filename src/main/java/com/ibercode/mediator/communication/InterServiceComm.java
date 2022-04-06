package com.ibercode.mediator.communication;

import com.google.gson.Gson;
import com.ibercode.mediator.model.CinemaResponse;
import com.ibercode.mediator.model.MovieResponse;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.util.Arrays;
import java.util.List;

public class InterServiceComm {

    private final LambdaClient awsLambda = LambdaClient.builder()
            .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
            .build();
    private final Gson gson = new Gson();

    public CinemaResponse getCinema(String cinemaId, String CINEMA_MS){

        SdkBytes payload = SdkBytes.fromUtf8String(gson.toJson(cinemaId)) ;
        InvokeRequest request = InvokeRequest.builder()
                .functionName(CINEMA_MS)
                .payload(payload)
                .build();

        InvokeResponse response = awsLambda.invoke(request);
        String value = response.payload().asUtf8String() ;

        return gson.fromJson(value, CinemaResponse.class);
    }

    public List<MovieResponse> getMoviesForCinema(List<String> movieIds, String MOVIE_MS){

        SdkBytes payload = SdkBytes.fromUtf8String(gson.toJson(movieIds)) ;
        InvokeRequest request = InvokeRequest.builder()
                .functionName(MOVIE_MS)
                .payload(payload)
                .build();

        InvokeResponse response = awsLambda.invoke(request);
        String value = response.payload().asUtf8String() ;

        MovieResponse[] movies = gson.fromJson(value, MovieResponse[].class);

        return Arrays.asList(movies);
    }
}
