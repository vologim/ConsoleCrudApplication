
package com.mikhail_golovackii.consoleapplication.repository.impl;

import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.model.Post;
import com.mikhail_golovackii.consoleapplication.repository.PostRepository;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class JsonPostRepositoryImpl extends BaseClass<Post> implements PostRepository{

    private final String FILE_PATH = "post.json";
    private final JsonLabelRepositoryImpl lableRepository = new JsonLabelRepositoryImpl();
    
    @Override
    public Post create(Post elem) {
        LinkedList<Post> posts = getAll();
        
        elem.setId(generatedId(posts));
        writeInBase(elem, FILE_PATH);
        
        return elem;
    }
    
    @Override
    public Post create(Post elem, Long labelId) {
        LinkedList<Post> posts = getAll();
        Label label = lableRepository.get(labelId);
        
        if (label == null){
            return null;
        }

        elem.setId(generatedId(posts));
        elem.getLabels().add(label);
        
        writeInBase(elem, FILE_PATH);

        return elem;
    }

    @Override
    public Post create(Post elem, List<Long> labelsId) {
        LinkedList<Post> posts = getAll();
        Label label;
        
        elem.setId(generatedId(posts));

        for (Long i : labelsId){
            label = lableRepository.get(i);
            
            if (label == null){
                System.out.println("Item not found, id: " + i);
                continue;
            }
            elem.getLabels().add(label);
        }
        
        writeInBase(elem, FILE_PATH);
        
        return elem;
    }
    
    @Override
    public Post get(Long id) {
        Post post = new Post();
        File file = new File(FILE_PATH);
        
        if(file.length() == 0){
            System.out.println("List posts is empty. Create new list!");
            return null;
        }

        if ((post = getObjFromBase(id, FILE_PATH, post)) != null){
            return post;
        }
        
        System.out.println("Post not found, id: " + id);
        return null;
    }

    @Override
    public LinkedList<Post> getAll() {
        LinkedList<Post> posts;
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List posts is empty. Create new list!");
            return null;
        }
            
        posts = getAllObjFromBase(FILE_PATH, new Post());
        
        if (posts.isEmpty()){
            System.out.println("List posts is empty");
            return null;
        }

        posts.sort(Comparator.comparing(Post::getId));
        return posts;
    }

    @Override
    public Post addLabel(Long postId, Long labelId) {
        Post post = get(postId);
        Label label = lableRepository.get(labelId);
        
        if (post == null){
            return null;
        }
        if (label == null){
            return null;
        }
        
        delete(postId);
        post.getLabels().add(label);
        
        writeInBase(post, FILE_PATH);
        
        return post;
    }

    @Override
    public Post addLabels(Long postId, List<Long> labelsId) {
        Post post = get(postId);
        Label label;
        
        if (post == null){
            return null;
        }

        delete(postId);

        for (Long i : labelsId){
            label = lableRepository.get(i);
            
            if (label == null){
                continue;
            }
            post.getLabels().add(label);
        }
        
        writeInBase(post, FILE_PATH);
        
        return post;
    }
    
    @Override
    public Post deleteLabel(Long postId, Long labelId) {
        Post post = get(postId);
        Label label = lableRepository.get(labelId);
        
        if (post == null){
            return null;
        }
        if (label == null){
            return null;
        }
        
        delete(postId);
        post.getLabels().remove(label);
        
        writeInBase(post, FILE_PATH);
        
        return post;
    }

    @Override
    public Post deleteLabels(Long postId) {
        Post post = get(postId);
        
        if (post == null){
            return null;
        }

        delete(postId);
        post.setLabels(new LinkedList<>());
        
        writeInBase(post, FILE_PATH);
        
        return post;
    }
    
    @Override
    public Post update(Long id, Post elem) {
        LinkedList<Post> posts = getAll();
        
        if (posts.isEmpty()){
            return null;
        }
        
        Post post = get(id);

        if (posts.removeIf(i -> i.getId() == id)){
            
            post.setName(elem.getName());
            post.setContent(elem.getContent());
            post.setLabels(elem.getLabels());
            posts.add(post);
            
            writeListInBase(posts, FILE_PATH);
            return post;
        }

        return null;
    }

    @Override
    public Post delete(Long id) {
        LinkedList<Post> posts = getAll();

        if (posts.isEmpty()){
            return null;
        }
        
        Post post = get(id);
        
        if (posts.removeIf(i -> i.getId() == id)){
            
            writeListInBase(posts, FILE_PATH);
            return post;
        }
        
        return null;
    } 

    @Override
    public Long generatedId(LinkedList<Post> list) {
        return super.generatedId(list);
    }

    @Override
    public Post getObjFromBase(Long id, String filePath, Post obj) {
        return super.getObjFromBase(id, filePath, obj);
    }

    @Override
    public LinkedList<Post> getAllObjFromBase(String filePath, Post obj) {
        return super.getAllObjFromBase(filePath, obj);
    }

    @Override
    public void writeInBase(Post obj, String filePath) {
        super.writeInBase(obj, filePath);
    }

    @Override
    public void writeListInBase(List<Post> list, String filePath) {
        super.writeListInBase(list, filePath);
    }
    
    
    
}
