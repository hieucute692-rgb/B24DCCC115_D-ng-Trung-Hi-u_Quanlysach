package com.example.quanlysach.model;

public class Book {
    private String name;
    private String author;
    private String category;
    private String status;
    private float rating;
    private String notes;

    public Book(String name, String author, String category, String status, float rating, String notes) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.status = status;
        this.rating = rating;
        this.notes = notes == null ? "" : notes;
    }

    public String getName() { return name; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public String getStatus() { return status; }
    public float getRating() { return rating; }
    public String getNotes() { return notes; }

    public void setStatus(String status) { this.status = status; }
    public void setRating(float rating) { this.rating = rating; }
    public void setNotes(String notes) { this.notes = notes == null ? "" : notes; }
}
