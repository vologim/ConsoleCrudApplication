
package com.mikhail_golovackii.consoleapplication.model;

import com.mikhail_golovackii.consoleapplication.model.Label;
import java.util.List;

public class Post {

    private long id;
    private String name;
    private String content;
    private List<Label> labels;

    public Post() {
    }

    public Post(String name, String content, List<Label> labels) {
        this.name = name;
        this.content = content;
        this.labels = labels;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

}
