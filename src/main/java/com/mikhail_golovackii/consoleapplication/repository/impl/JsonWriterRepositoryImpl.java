
package com.mikhail_golovackii.consoleapplication.repository.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.mikhail_golovackii.consoleapplication.model.Post;
import com.mikhail_golovackii.consoleapplication.model.Writer;
import com.mikhail_golovackii.consoleapplication.repository.WriterRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class JsonWriterRepositoryImpl implements WriterRepository{

    private final String FILE_PATH = "writer.json";
    
    @Override
    public Writer create(Writer elem) {
        Gson gson = new Gson();
        LinkedList<Writer> writers = getAll();
        
        if (writers == null){
            elem.setId(0);
        }
        else{
            elem.setId(writers.getLast().getId() + 1);
        }
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(elem, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return elem;
    }
    
    @Override
    public Writer create(Writer elem, Long postId) {
        Gson gson = new Gson();
        LinkedList<Writer> writers = getAll();
        JsonPostRepositoryImpl repository = new JsonPostRepositoryImpl();
        Post post = repository.get(postId);
        
        if (post == null){
            return null;
        }
        
        if (writers == null){
            elem.setId(0);
        }
        else{
            elem.setId(writers.getLast().getId() + 1);
        }
        elem.getPosts().add(post);
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(elem, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return elem;
    }

    @Override
    public Writer create(Writer elem, List<Long> postsId) {
        Gson gson = new Gson();
        LinkedList<Writer> writers = getAll();
        JsonPostRepositoryImpl repository = new JsonPostRepositoryImpl();
        Post post = new Post();
        
        if (writers == null){
            elem.setId(0);
        }
        else{
            elem.setId(writers.getLast().getId() + 1);
        }
        
        for (Long i : postsId){
            post = repository.get(i);
            
            if (post == null){
                continue;
            }
            elem.getPosts().add(post);
        }
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(elem, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return elem;
    }
    
    @Override
    public Writer get(Long id) {
        Gson gson = new Gson();
        Writer writer = new Writer();
        File file = new File(FILE_PATH);
        
        if(file.length() == 0){
            System.out.println("List writers is empty. Create new list!");
            return null;
        }
        
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))){
            reader.setLenient(true);
            while (reader.peek() != JsonToken.END_DOCUMENT){
                writer = gson.fromJson(reader, Writer.class);
                if (writer.getId() == id){
                    return writer;
                }
            }
        } 
        catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.out.println(ex.getMessage());
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        System.out.println("Writer not found, id: " + id);
        return null;
    }

    @Override
    public LinkedList<Writer> getAll() {
        LinkedList<Writer> writers = new LinkedList<>();
        Writer writer = new Writer();
        Gson gson = new Gson();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List writers is empty. Create new list!");
            return null;
        }
        
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))){
            reader.setLenient(true);

            while (reader.peek() != JsonToken.END_DOCUMENT){
                writer = gson.fromJson(reader, Writer.class);
                writers.add(writer);
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
    
        if (writers.isEmpty()){
            System.out.println("List writers is empty");
            return null;
        }

        writers.sort(Comparator.comparing(Writer::getId));
        return writers;
    }

    @Override
    public Writer addPost(Long writerId, Long postId) {
        Gson gson = new Gson();
        Writer writerModel = get(writerId);
        JsonPostRepositoryImpl repository = new JsonPostRepositoryImpl();
        Post post = repository.get(postId);
        
        if (writerModel == null){
            return null;
        }
        if (post == null){
            return null;
        }
        
        delete(writerId);
        writerModel.getPosts().add(post);
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(writerModel, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return writerModel;
    }

    @Override
    public Writer addPosts(Long writerId, List<Long> postsId) {
        Gson gson = new Gson();
        Writer writerModel = get(writerId);
        JsonPostRepositoryImpl repository = new JsonPostRepositoryImpl();
        Post post = new Post();
        
        if (writerModel == null){
            return null;
        }

        delete(writerId);
        
        for (Long i : postsId){
            post = repository.get(i);
            
            if (post == null){
                continue;
            }
            writerModel.getPosts().add(post);
        }
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(writerModel, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return writerModel;
    }

    @Override
    public Writer deletePost(Long writerId, Long postId) {
        Gson gson = new Gson();
        Writer writerModel = get(writerId);
        JsonPostRepositoryImpl repository = new JsonPostRepositoryImpl();
        Post post = repository.get(postId);
        
        if (writerModel == null){
            return null;
        }
        if (post == null){
            return null;
        }
        
        delete(writerId);
        writerModel.getPosts().remove(post);
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(writerModel, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return writerModel;
    }

    @Override
    public Writer deletePosts(Long writerId) {
        Gson gson = new Gson();
        Writer writerModel = get(writerId);
        
        if (writerModel == null){
            return null;
        }

        delete(writerId);
        writerModel.setPosts(new LinkedList<>());
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(writerModel, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return writerModel;
    }

    @Override
    public Writer update(Long id, Writer elem) {
        LinkedList<Writer> writers = getAll();
        Gson gson = new Gson();
        Writer writerModel = get(id);

        if (writerModel == null){
            return null;
        }
        
        if (writers.isEmpty()){
            return null;
        }
        
        writers.remove(writerModel);
        
        writerModel.setName(elem.getName());
        writerModel.setPosts(writerModel.getPosts());
        writers.add(writerModel);
        
        writers.sort(Comparator.comparing(Writer::getId));
        
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            writers.stream().forEach(e -> gson.toJson(e, writer));
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return writerModel;
    }

    @Override
    public Writer delete(Long id) {
        LinkedList<Writer> writers = getAll();
        Writer writerModel = get(id);
        Gson gson = new Gson();
        
        if (writerModel == null){
            return null;
        }
        if (writers.isEmpty()){
            return null;
        }

        writers.remove(writerModel);
        
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            writers.stream().forEach(elem -> gson.toJson(elem, writer));
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        return writerModel;
    }
    
}
