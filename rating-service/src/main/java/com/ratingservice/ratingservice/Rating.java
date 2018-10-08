package com.ratingservice.ratingservice;

public class Rating {

    private Long id;
    private Long bookId;
    private int stars;
    private int port;

    public Rating(Long id, Long bookId, int stars, int port) {
        this.id = id;
        this.bookId = bookId;
        this.stars = stars;
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
