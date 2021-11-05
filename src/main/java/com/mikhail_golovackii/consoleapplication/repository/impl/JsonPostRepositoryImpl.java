
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
            System.out.println("List posts is empty. Create new list!");
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
        
        System.out.println("Post not found, id: " + id);
        return null;
    }

    @Override
    public LinkedList<Post> getAll() {
        LinkedList<Post> posts = new LinkedList<>();
        Post post = new Post();
        Gson gson = new Gson();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List posts is empty. Create new list!");
            return null;
        }
        
        try (JsonReader reader = new JsonReader(new FileReader(FILE_PATH))){
            reader.setLenient(true);

            while (reader.peek() != JsonToken.END_DOCUMENT){
                post = gson.fromJson(reader, Post.class);
                posts.add(post);
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
    
        if (posts.isEmpty()){
            System.out.println("List posts is empty");
            return null;
        }

        posts.sort(Comparator.comparing(Post::getId));
        return posts;
    }

    @Override
    public Post addLabel(Long postId, Long labelId) {
        Gson gson = new Gson();
        Post post = get(postId);
        JsonLabelRepositoryImpl repository = new JsonLabelRepositoryImpl();
        Label label = repository.get(labelId);
        
        if (post == null){
            return null;
        }
        if (label == null){
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
        Label label = new Label();
        
        if (post == null){
            return null;
        }

        delete(postId);
        
        for (Long i : labelsId){
            label = repository.get(i);
            
            if (label == null){
                continue;
            }
            post.getLabels().add(label);
        }
        
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
            return null;
        }
        if (label == null){
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
            return null;
        }

        delete(postId);
        post.setLabels(new LinkedList<>());
        
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
        LinkedList<Post> posts = getAll();
        Gson gson = new Gson();
        Post post = get(id);

        if (post == null){
            return null;
        }
        
        if (posts.isEmpty()){
            return null;
        }
        
        posts.remove(post);
        
        post.setId(post.getId());
        post.setName(elem.getName());
        post.setContent(elem.getContent());
        post.setLabels(elem.getLabels());
        posts.add(post);
        
        posts.sort(Comparator.comparing(Post::getId));
        
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            posts.stream().forEach(e -> gson.toJson(e, writer));
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
        
        return post;
    }

    @Override
    public void delete(Long id) {
        LinkedList<Post> posts = getAll();
        Post post = get(id);
        Gson gson = new Gson();
        
        if (post == null){
            return;
        }
        if (posts.isEmpty()){
            return;
        }

        posts.remove(post);
        
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            posts.stream().forEach(elem -> gson.toJson(elem, writer));
        }
        catch(IOException ex){
            System.out.println("IOException");
            System.out.println(ex.getMessage());
        }
    } 
    
}
