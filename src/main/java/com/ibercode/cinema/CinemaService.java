package com.ibercode.cinema;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ibercode.cinema.model.Cinema;
import com.ibercode.cinema.utils.DDBUtils;

public class CinemaService implements RequestHandler<String, Cinema> {

    private final DDBUtils ddbUtils = new DDBUtils();
    private final String tableName = System.getenv("CINEMAS_TABLE");

    @Override
    public Cinema handleRequest(String cinemaId, Context context) {
        return ddbUtils.getCinemaById(cinemaId, tableName);
    }
}
