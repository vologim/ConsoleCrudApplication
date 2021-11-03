
package com.mikhail_golovackii.consoleapplication.model;

import com.mikhail_golovackii.consoleapplication.model.Post;
import java.util.List;

public class Writer {

    private long id;
    private String name;
    private List<Post> posts;

    public Writer() {
    }

    public Writer(String name, List<Post> posts) {
        this.name = name;
        this.posts = posts;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
        
}
