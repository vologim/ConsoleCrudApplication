
package com.mikhail_golovackii.consoleapplication.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    
    T create(T elem);
    
    T get(ID id);
    
    List<T> getAll();
    
    T update(ID id, T elem);
    
    void delete(ID id);
}
