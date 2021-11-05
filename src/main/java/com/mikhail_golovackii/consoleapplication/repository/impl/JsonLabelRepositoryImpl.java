
package com.mikhail_golovackii.consoleapplication.repository.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.repository.LabelRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

public class JsonLabelRepositoryImpl implements LabelRepository{

    private final String FILE_PATH = "label.json";
    
    @Override
    public Label create(Label elem) {
        Gson gson = new Gson();
        LinkedList<Label> labels = getAll();
        
        if (labels == null){
            elem.setId(0);
        }
        else{
            elem.setId(labels.getLast().getId() + 1);
        }
        
        try(FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(elem, writer);
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return elem;
    }

    @Override
    public Label get(Long id) {
        Gson gson = new Gson();
        Label label = new Label();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List labels is empty. Create new list!");
            return null;
        }
        
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))){
            reader.setLenient(true);
            while (reader.peek() != JsonToken.END_DOCUMENT){
                label = gson.fromJson(reader, Label.class);
                if (label.getId() == id){
                    return label;
                }
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
        
        System.out.println("Label not found, id: " + id);

        return null;
    }

    @Override
    public LinkedList<Label> getAll() {
        LinkedList<Label> labels = new LinkedList<>();
        Label label = new Label();
        Gson gson = new Gson();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List posts is empty. Create new list!");
            return null;
        }
        
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))){
            reader.setLenient(true);

            while (reader.peek() != JsonToken.END_DOCUMENT){
                label = gson.fromJson(reader, Label.class);
                labels.add(label);
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
    
        if (labels.isEmpty()){
            System.out.println("List labels is empty");
            return null;
        }

        labels.sort(Comparator.comparing(Label::getId));
        return labels;
    }    

    @Override
    public Label update(Long id, Label elem) {
        LinkedList<Label> labels = getAll();
        Gson gson = new Gson();
        Label label = get(id);

        if (label == null){
            return null;
        }
        
        if (labels.isEmpty()){
            return null;
        }
        
        labels.remove(label);
        label.setName(elem.getName());
        labels.add(label);
        
        labels.sort(Comparator.comparing(Label::getId));
        
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            labels.stream().forEach(e -> gson.toJson(e, writer));
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return label;
    }

    @Override
    public void delete(Long id) {
        LinkedList<Label> labels = getAll();
        Label label = get(id);
        Gson gson = new Gson();
        
        if (label == null){
            return;
        }
        if (labels.isEmpty()){
            return;
        }

        labels.remove(label);
        
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            labels.stream().forEach(elem -> gson.toJson(elem, writer));
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
    }
}
