package com.example.learncards.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

@Entity(tableName = "card")
public class Card {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long subject_fk;
    private String name;
    private String description;
    private String context;
    private float rating;

    @Relation(parentColumn = "id", entityColumn = "card_fk", entity = Question.class)
    private List<Question> questions;

    public Card(long i, String n, String d, String c, float r, List<Question> l){
        this.id = i;
        this.name = n;
        this.description = d;
        this.context = c;
        this.rating = r;
        this.questions = l;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubject_fk() {
        return subject_fk;
    }

    public void setSubject_fk(long subject_fk) {
        this.subject_fk = subject_fk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}