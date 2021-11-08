
package com.mikhail_golovackii.consoleapplication.controller;

import com.mikhail_golovackii.consoleapplication.model.Post;
import com.mikhail_golovackii.consoleapplication.repository.impl.JsonPostRepositoryImpl;
import java.util.LinkedList;
import java.util.List;

public class PostController {
    
    private final JsonPostRepositoryImpl repository = new JsonPostRepositoryImpl();
    
    public Post create(Post elem){
        return repository.create(elem);
    }
    
    public Post create(Post elem, Long labelId){
        return repository.create(elem, labelId);
    }
    
    public Post create(Post elem, List<Long> labelsId){
        return repository.create(elem, labelsId);
    }
    
    public Post get(Long id){
        return repository.get(id);
    }
    
    public LinkedList<Post> getAll(){
        return repository.getAll();
    }
    
    public Post addLabel(Long postId, Long labelId){
        return repository.addLabel(postId, labelId);
    }
    
    public Post addLabels(Long postId, List<Long> labelsID){
        return repository.addLabels(postId, labelsID);
    }
    
    public Post deleteLabel(Long postId, Long labelId){
        return repository.deleteLabel(postId, labelId);
    }
    
    public Post deleteLabels(Long postId){
        return repository.deleteLabels(postId);
    }
    
    public Post update(Long id, Post elem){
        return update(id, elem);
    }
    
    public Post delete(Long id){
        return repository.delete(id);
    }
    
}
