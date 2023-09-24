package org.kie.kogito.flexible.example.model;

import java.time.ZonedDateTime;

public class Comment {

    private String author;
    private ZonedDateTime date = ZonedDateTime.now();
    private String text;

    public Comment() {
    }

    public Comment(Comment c) {
        this.author = c.author;
        this.date = c.date;
        this.text = c.text;
    }

    public String getAuthor() {
        return author;
    }

    public Comment setAuthor(String author) {
        this.author = author;
        return this;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Comment setDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" + "author='" + author + '\'' + ", date=" + date + ", text='" + text + '\'' + '}';
    }
}
