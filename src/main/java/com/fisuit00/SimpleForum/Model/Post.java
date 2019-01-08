package com.fisuit00.SimpleForum.Model;

import io.micrometer.core.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private long created;

    @NonNull
    private String content;

    public long getId() {
        return id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created){ this.created = created; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
