
package com.mikhail_golovackii.consoleapplication.repository.impl;

import com.mikhail_golovackii.consoleapplication.model.Post;
import com.mikhail_golovackii.consoleapplication.model.Writer;
import com.mikhail_golovackii.consoleapplication.repository.WriterRepository;
import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class JsonWriterRepositoryImpl extends BaseClass<Writer> implements WriterRepository{

    private final String FILE_PATH = "writer.json";
    private final JsonPostRepositoryImpl repository = new JsonPostRepositoryImpl();
    
    @Override
    public Writer create(Writer elem) {
        LinkedList<Writer> writers = getAll();

        elem.setId(generatedId(writers));
        
        writeInBase(elem, FILE_PATH);
        
        return elem;
    }
    
    @Override
    public Writer create(Writer elem, Long postId) {
        LinkedList<Writer> writers = getAll();
        Post post = repository.get(postId);
        
        if (post == null){
            return null;
        }
        
        elem.setId(generatedId(writers));
        elem.getPosts().add(post);
        
        writeInBase(elem, FILE_PATH);
        
        return elem;
    }

    @Override
    public Writer create(Writer elem, List<Long> postsId) {
        LinkedList<Writer> writers = getAll();
        Post post = new Post();
        
        elem.setId(generatedId(writers));

        for (Long i : postsId){
            post = repository.get(i);
            
            if (post == null){
                continue;
            }
            elem.getPosts().add(post);
        }
        
        writeInBase(elem, FILE_PATH);
        
        return elem;
    }
    
    @Override
    public Writer get(Long id) {
        Writer writer = new Writer();
        File file = new File(FILE_PATH);
        
        if (file.length() == 0){
            System.out.println("List writers is empty. Create new list!");
            return null;
        }
        
        if ((writer = getObjFromBase(id, FILE_PATH, writer)) != null){
            return writer;
        }
        
        System.out.println("Writer not found, id: " + id);
        return null;
    }

    @Override
    public LinkedList<Writer> getAll() {
        LinkedList<Writer> writers = new LinkedList<>();
        File emptyFile = new File(FILE_PATH);
        
        if (emptyFile.length() == 0){
            System.out.println("List writers is empty. Create new list!");
            return null;
        }
        
        writers = getAllObjFromBase(FILE_PATH, new Writer());
    
        if (writers.isEmpty()){
            System.out.println("List writers is empty");
            return null;
        }

        writers.sort(Comparator.comparing(Writer::getId));
        return writers;
    }

    @Override
    public Writer addPost(Long writerId, Long postId) {
        Writer writerModel = get(writerId);;
        Post post = repository.get(postId);
        
        if (writerModel == null){
            return null;
        }
        if (post == null){
            return null;
        }
        
        delete(writerId);
        writerModel.getPosts().add(post);
        
        writeInBase(writerModel, FILE_PATH);
        
        return writerModel;
    }

    @Override
    public Writer addPosts(Long writerId, List<Long> postsId) {
        Writer writerModel = get(writerId);
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
        
        writeInBase(writerModel, FILE_PATH);
        
        return writerModel;
    }

    @Override
    public Writer deletePost(Long writerId, Long postId) {
        Writer writerModel = get(writerId);
        Post post = repository.get(postId);
        
        if (writerModel == null){
            return null;
        }
        if (post == null){
            return null;
        }
        
        delete(writerId);
        writerModel.getPosts().remove(post);
        
        writeInBase(writerModel, FILE_PATH);
        
        return writerModel;
    }

    @Override
    public Writer deletePosts(Long writerId) {
        Writer writerModel = get(writerId);
        
        if (writerModel == null){
            return null;
        }

        delete(writerId);
        writerModel.setPosts(new LinkedList<>());
        
        writeInBase(writerModel, FILE_PATH);
        
        return writerModel;
    }

    @Override
    public Writer update(Long id, Writer elem) {
        LinkedList<Writer> writers = getAll();
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
        
        writeListInBase(writers, FILE_PATH);
        
        return writerModel;
    }

    @Override
    public Writer delete(Long id) {
        LinkedList<Writer> writers = getAll();
        Writer writerModel = get(id);
        
        if (writerModel == null){
            return null;
        }
        if (writers.isEmpty()){
            return null;
        }

        writers.remove(writerModel);
        
        writeListInBase(writers, FILE_PATH);
        
        return writerModel;
    }

    @Override
    public Long generatedId(LinkedList<Writer> list) {
        return super.generatedId(list);
    }

    @Override
    public Writer getObjFromBase(Long id, String filePath, Writer obj) {
        return super.getObjFromBase(id, filePath, obj);
    }

    @Override
    public LinkedList<Writer> getAllObjFromBase(String filePath, Writer obj) {
        return super.getAllObjFromBase(filePath, obj);
    }

    @Override
    public void writeInBase(Writer obj, String filePath) {
        super.writeInBase(obj, filePath);
    }

    @Override
    public void writeListInBase(List<Writer> list, String filePath) {
        super.writeListInBase(list, filePath);
    }
}
