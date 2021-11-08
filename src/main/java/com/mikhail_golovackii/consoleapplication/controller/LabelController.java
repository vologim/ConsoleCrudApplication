
package com.mikhail_golovackii.consoleapplication.controller;

import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.repository.impl.JsonLabelRepositoryImpl;
import java.util.LinkedList;

public class LabelController {
    
    private final JsonLabelRepositoryImpl repository = new JsonLabelRepositoryImpl();
    
    public Label create(Label elem){
        return repository.create(elem);
    }
    
    public Label get(Long id){
        return repository.get(id);
    }
    
    public LinkedList<Label> getAll(){
        return repository.getAll();
    }
    
    public Label update(Long id, Label elem){
        return repository.update(id, elem);
    }
    
    public Label delete(Long id){
        return repository.delete(id);
    }
    
}
