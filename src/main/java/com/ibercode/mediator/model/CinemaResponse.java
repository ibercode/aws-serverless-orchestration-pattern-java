package com.ibercode.mediator.model;

import java.util.List;

public class CinemaResponse {

    private String cinemaId;
    private String name;
    private List<String> movies;

    public CinemaResponse() {
    }

    public CinemaResponse(String cinemaId, String name, List<String> movies) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.movies = movies;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMovies() {

        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaId='" + cinemaId + '\'' +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
