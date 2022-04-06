package com.ibercode.mediator.model;

import java.util.List;

public class MediatorResponse {
    private String cinemaName;
    private List<MovieResponse> movies;

    public MediatorResponse() {
    }

    public MediatorResponse(String cinemaName, List<MovieResponse> movies) {
        this.cinemaName = cinemaName;
        this.movies = movies;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public List<MovieResponse> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieResponse> movies) {
        this.movies = movies;
    }
}
