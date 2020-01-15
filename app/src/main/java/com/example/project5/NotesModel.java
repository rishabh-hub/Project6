package com.example.project5;

public class NotesModel {
    private String title;
    private String notes;
    private int id;

    public NotesModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotesModel(String title, String notes, int id) {
        this.title = title;
        this.notes = notes;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
