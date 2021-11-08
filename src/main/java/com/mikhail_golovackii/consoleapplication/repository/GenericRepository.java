
package com.mikhail_golovackii.consoleapplication.repository;

import java.util.LinkedList;

public interface GenericRepository<T, ID> {
    
    T create(T elem);
    
    T get(ID id);
    
    LinkedList<T> getAll();
    
    T update(ID id, T elem);
    
    T delete(ID id);
}
