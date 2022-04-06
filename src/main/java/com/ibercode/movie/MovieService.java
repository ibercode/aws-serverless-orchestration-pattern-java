package com.ibercode.movie;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ibercode.movie.model.Movie;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.List;

public class MovieService implements RequestHandler<List<String>, List<Movie>> {

    private final String MOVIES_TABLE = System.getenv("MOVIES_TABLE");
    private final DynamoDbClient ddbClient = DynamoDbClient.builder()
            .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
            .build();
    private final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(ddbClient)
            .build();

    @Override
    public List<Movie> handleRequest(List<String> movieIds, Context context) {

        List<Movie> movies = new ArrayList<>();

        DynamoDbTable<Movie> mappedTable = enhancedClient.table(MOVIES_TABLE, TableSchema.fromBean(Movie.class));

        movieIds.forEach(id -> movies.add(mappedTable.getItem(Key.builder().partitionValue(id).build())));

        return movies;
    }
}
