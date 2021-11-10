
package com.mikhail_golovackii.consoleapplication.repository.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.mikhail_golovackii.consoleapplication.model.BaseModel;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseClass<T extends BaseModel> {
    
    private static final Gson gson = new Gson();
    private T value;

    public void writerListInBase(List<T> list, String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            list.stream().forEach(e -> gson.toJson(e, writer));
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
    }
    
    public void writeInBase(T obj, String filePath){
        try(FileWriter writer = new FileWriter(filePath, true)){
            gson.toJson(obj, writer);
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
    }
        
    public LinkedList<T> getAllFromBases(String filePath, T obj){
        LinkedList<T> list = new LinkedList<>();
        
        try (JsonReader reader = new JsonReader(new FileReader(filePath))){
            reader.setLenient(true);

            while (reader.peek() != JsonToken.END_DOCUMENT){
                list.add(gson.fromJson(reader, obj.getClass()));
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
            System.out.println(ex.getMessage());
        }
        catch (IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    public T getFromBases(Long id, String filePath, T obj){
        return getAllFromBases(filePath, obj).stream()
                                        .filter(elem -> ((BaseModel) elem).getId() == id)
                                        .findFirst().orElse(null);
    }
    
    public Long generatedId(LinkedList<T> list){
        
        if (list == null){
            return 0l;
        }
        else{
            return ((BaseModel) list.getLast()).getId() + 1;
        }
    }
}
