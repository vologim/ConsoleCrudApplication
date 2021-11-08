
package com.mikhail_golovackii.consoleapplication.controller;

import com.mikhail_golovackii.consoleapplication.model.Writer;
import com.mikhail_golovackii.consoleapplication.repository.impl.JsonWriterRepositoryImpl;
import java.util.LinkedList;
import java.util.List;

public class WriterController {
    
    private final JsonWriterRepositoryImpl repository = new JsonWriterRepositoryImpl();
    
    public Writer create(Writer elem){
        return repository.create(elem);
    }
    
    public Writer create(Writer elem, Long postId){
        return repository.create(elem, postId);
    }
    
    public Writer create(Writer elem, List<Long> postsId){
        return repository.create(elem, postsId);
    }
    
    public Writer get(Long id){
        return repository.get(id);
    }
    
    public LinkedList<Writer> getAll(){
        return repository.getAll();
    }
    
    public Writer addPost(Long writerId, Long postId){
        return repository.addPost(writerId, postId);
    }
    
    public Writer addPosts(Long writerId, List<Long> postsId){
        return repository.addPosts(writerId, postsId);
    }
    
    public Writer deletePost(Long writerId, Long postId){
        return repository.deletePost(writerId, postId);
    }
    
    public Writer deletePosts(Long writerId){
        return repository.deletePosts(writerId);
    }
    
    public Writer update(Long id, Writer elem){
        return repository.update(id, elem);
    }
    
    public Writer delete(Long id){
        return repository.delete(id);
    }

}
