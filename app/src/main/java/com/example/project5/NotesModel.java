package com.example.project5;

public class NotesModel {
    private String title;
    private String notes;

    public NotesModel(String title, String notes) {
        this.title = title;
        this.notes = notes;
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
