package org.kie.kogito.flexible.example.model;

import java.time.ZonedDateTime;

public class Questionnaire {

    private ZonedDateTime date = ZonedDateTime.now();
    private int evaluation;
    private String comment;

    public Questionnaire() {
    }

    public Questionnaire(Questionnaire q) {
        this.comment = q.comment;
        this.date = q.date;
        this.evaluation = q.evaluation;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public Questionnaire setEvaluation(int evaluation) {
        this.evaluation = evaluation;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Questionnaire setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String toString() {
        return "Questionnaire{" + "date=" + date + ", evaluation=" + evaluation + ", comment='" + comment + '\'' + '}';
    }
}
