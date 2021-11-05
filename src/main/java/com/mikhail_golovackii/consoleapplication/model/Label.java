
package com.mikhail_golovackii.consoleapplication.model;

import java.util.Objects;

public class Label {
    
    private long id;
    private String name;

    public Label() {
    }
        
    public Label(String name) {       
        this.name = name;
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

    @Override
    public String toString() {
        return "Label{" + "id=" + id + ", name=" + name + '}';
    }
}
