
package com.mikhail_golovackii.consoleapplication.repository.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.model.Post;
import com.mikhail_golovackii.consoleapplication.repository.PostRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class JsonPostRepositoryImpl implements PostRepository{

    private final String FILE_PATH = "post.json";
    
    @Override
    public Post create(Post elem) {
        Gson gson = new Gson();
        LinkedList<Post> posts = getAll();
        
        if (posts == null){
            elem.setId(0);
        }
        else{
            elem.setId(posts.getLast().getId() + 1);
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
    public Post create(Post elem, Long labelId) {
        Gson gson = new Gson();
        LinkedList<Post> posts = getAll();
        JsonLabelRepositoryImpl repository = new JsonLabelRepositoryImpl();
        Label label = repository.get(labelId);
        
        if (label == null){
            return null;
        }
        
        if (posts == null){
            elem.setId(0);
        }
        else{
            elem.setId(posts.getLast().getId() + 1);
        }
        elem.getLabels().add(label);
        
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
    public Post create(Post elem, List<Long> labelsId) {
        Gson gson = new Gson();
        LinkedList<Post> posts = getAll();
        JsonLabelRepositoryImpl repository = new JsonLabelRepositoryImpl();
        Label label = new Label();
        
        if (posts == null){
            elem.setId(0);
        }
        else{
            elem.setId(posts.getLast().getId() + 1);
        }
        
        for (Long i : labelsId){
            label = repository.get(i);
            
            if (label == null){
                System.out.println("Item not found, id: " + i);
                continue;
            }
            elem.getLabels().add(label);
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
    public Post get(Long id) {
        Gson gson = new Gson();
        Post post = new Post();
        File file = new File(FILE_PATH);
        
        if(file.length() == 0){
            return null;
        }
        
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))){
            reader.setLenient(true);
            while (reader.peek() != JsonToken.END_DOCUMENT){
                post = gson.fromJson(reader, Post.class);
                if (post.getId() == id){
                    return post;
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
        
        return post;
    }

    @Override
    public LinkedList<Post> getAll() {
        LinkedList<Post> labels = new LinkedList<>();
        Post label = new Post();
        Gson gson = new Gson();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            return null;
        }
        
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))){
            reader.setLenient(true);

            while (reader.peek() != JsonToken.END_DOCUMENT){
                label = gson.fromJson(reader, Post.class);
                labels.add(label);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
            System.out.println(ex.getMessage());
        }
        catch (IOException ex){
            System.out.println("IOException---");
            System.out.println(ex.getMessage());
        }
    
        if (labels.isEmpty()){
            return null;
        }

        labels.sort(Comparator.comparing(Post::getId));
        return labels;
    }

    @Override
    public Post addLabel(Long postId, Long labelId) {
        Gson gson = new Gson();
        Post post = get(postId);
        JsonLabelRepositoryImpl repository = new JsonLabelRepositoryImpl();
        Label label = repository.get(labelId);
        
        if (post == null){
            System.out.println("Post not found, id: " + postId);
            return null;
        }
        if (label == null){
            System.out.println("Label not found, id: " + labelId);
            return null;
        }
        
        delete(postId);
        post.getLabels().add(label);
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(post, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return post;
    }

    @Override
    public Post addLabels(Long postId, List<Long> labelsId) {
        Gson gson = new Gson();
        Post post = get(postId);
        JsonLabelRepositoryImpl repository = new JsonLabelRepositoryImpl();
        List<Label> labels = repository.getAll();
        
        if (post == null){
            System.out.println("Post not found, id: " + postId);
            return null;
        }

        delete(postId);
        post.setLabels(labels);
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(post, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return post;
    }
    
    @Override
    public Post deleteLabel(Long postId, Long labelId) {
        Gson gson = new Gson();
        Post post = get(postId);
        JsonLabelRepositoryImpl repository = new JsonLabelRepositoryImpl();
        Label label = repository.get(labelId);
        
        if (post == null){
            System.out.println("Post not found, id: " + postId);
            return null;
        }
        if (label == null){
            System.out.println("Label not found, id: " + labelId);
            return null;
        }
        
        delete(postId);
        post.getLabels().remove(label);
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(post, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return post;
    }

    @Override
    public Post deleteLabels(Long postId) {
        Gson gson = new Gson();
        Post post = get(postId);
        
        if (post == null){
            System.out.println("Post not found, id: " + postId);
            return null;
        }

        delete(postId);
        post.setLabels(null);
        
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            gson.toJson(post, writer);
        } 
        catch (IOException ex) {
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return post;
    }
    
    @Override
    public Post update(Long id, Post elem) {
        LinkedList<Post> labels = getAll();
        Gson gson = new Gson();
        Post label = get(id);

        if (label == null){
            return null;
        }
        
        if (labels.isEmpty()){
            return null;
        }
        
        labels.remove(label);
        label.setName(elem.getName());
        labels.add(label);
        
        labels.sort(Comparator.comparing(Post::getId));
        
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
        LinkedList<Post> labels = getAll();
        Post label = get(id);
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
