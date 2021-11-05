
package com.mikhail_golovackii.consoleapplication.repository;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public interface GenericRepository<T, ID> {
    
    T create(T elem);
    
    T get(ID id);
    
    LinkedList<T> getAll();
    
    T update(ID id, T elem);
    
    void delete(ID id);
    
    public default void writeFile(String path, T elem){
        Gson gson = new Gson();

        try(FileWriter writer = new FileWriter(path, true)){
            gson.toJson(elem, writer);
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
    }
}
