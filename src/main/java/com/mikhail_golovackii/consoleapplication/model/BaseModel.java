
package com.mikhail_golovackii.consoleapplication.model;

public abstract class BaseModel {
    
    abstract public long getId();

    abstract public void setId(long id);

    abstract public String toString();

    abstract public int hashCode();

    abstract public boolean equals(Object obj);

    public BaseModel() {
    }

}
