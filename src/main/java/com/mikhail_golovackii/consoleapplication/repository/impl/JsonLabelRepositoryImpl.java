
package com.mikhail_golovackii.consoleapplication.repository.impl;

import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.repository.LabelRepository;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class JsonLabelRepositoryImpl extends BaseClass<Label> implements LabelRepository{

    private final String FILE_PATH = "label.json";
    
    @Override
    public Label create(Label elem) {
        LinkedList<Label> labels = getAll();
        
        elem.setId(generatedId(labels));
        writeInBase(elem, FILE_PATH);
        
        return elem;
    }

    @Override
    public Label get(Long id) {
        Label label = new Label();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List labels is empty. Create new list!");
            return null;
        }

        if ((label = getObjFromBase(id, FILE_PATH, label)) != null){
            return label;
        }
        
        System.out.println("Label not found, id: " + id);
        
        return null;
    }

    @Override
    public LinkedList<Label> getAll() {
        LinkedList<Label> labels = new LinkedList<>();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List posts is empty. Create new list!");
            return null;
        }
        
        labels = getAllObjFromBase(FILE_PATH, new Label());

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

        if (labels.isEmpty()){
            return null;
        }

        Label label = get(id);
        
        if (labels.removeIf(i -> i.getId() == id)){
            
            label.setName(elem.getName());
            labels.add(label);
        
            writeListInBase(labels, FILE_PATH);
            
            return label;
        }
        
        return null;
    }

    @Override
    public Label delete(Long id) {
        LinkedList<Label> labels = getAll();
        
        if (labels.isEmpty()){
            return null;
        }
        
        Label label = get(id);

        if (labels.removeIf(i -> i.getId() == id)){
            writeListInBase(labels, FILE_PATH);
            return label;
        }
        
        return null;
    }
 
    @Override
    public Long generatedId(LinkedList<Label> list) {
        return super.generatedId(list);
    }

    @Override
    public Label getObjFromBase(Long id, String filePath, Label obj) {
        return super.getObjFromBase(id, filePath, obj);
    }

    @Override
    public LinkedList<Label> getAllObjFromBase(String filePath, Label obj) {
        return super.getAllObjFromBase(filePath, obj);
    }

    @Override
    public void writeInBase(Label obj, String filePath) {
        super.writeInBase(obj, filePath);
    }

    @Override
    public void writeListInBase(List<Label> list, String filePath) {
        super.writeListInBase(list, filePath);
    }

}
