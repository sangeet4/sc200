package com.stackroute.moviecruiserapp.service;

import com.stackroute.moviecruiserapp.domain.Movie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("impl2")
public class MovieServiceImpl2 implements MovieService {

    private List<Movie> emptyObj;
    private Optional<Movie> emptyObj1;
    private Movie emptyObj2;

    @Override
    public List<Movie> getAllMovies() {
        return emptyObj;
    }

    @Override
    public Optional<Movie> getMovieById(int id) {
        return emptyObj1;
    }

    @Override
    public Movie getMovieByTitle(String title) {
        return emptyObj2;
    }

    @Override
    public List<Movie> deleteMovie(int id) {
        return emptyObj;
    }

    @Override
    public Movie updateMovie(int id,String comments) {
        return emptyObj2;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movie;
    }
}
