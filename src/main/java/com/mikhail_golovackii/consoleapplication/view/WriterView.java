
package com.mikhail_golovackii.consoleapplication.view;

import com.mikhail_golovackii.consoleapplication.controller.PostController;
import com.mikhail_golovackii.consoleapplication.controller.WriterController;
import com.mikhail_golovackii.consoleapplication.model.Post;
import com.mikhail_golovackii.consoleapplication.model.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    
    private final WriterController controller = new WriterController();
    private final PostController postController = new PostController();
    private Scanner scanner = new Scanner(System.in);
    
    public void createWriter(){
        System.out.println("Enter name post:");
        String name = scanner.nextLine();

        System.out.println("Add post in writer?\n"
                + "Enter number:\n"
                + "1: no\n"
                + "2: add post\n"
                + "3: add posts\n");
        
        if (scanner.hasNextInt()){
            int answer = scanner.nextInt();
            long id;
            Writer writer = new Writer(name);
            Post post;
            
            switch (answer){
                
                case 1:
                    controller.create(writer);
                    System.out.println("Writer created.");
                    break;
                    
                case 2:
                    System.out.println("Enter id post:");

                    if (scanner.hasNextLong()){
                        id = scanner.nextLong();
                    }
                    else {
                        System.out.println("Please, enter a number.");
                        System.out.println("Writer is not created.");
                        return;
                    }
                    
                    post = postController.get(id);
                    if (post == null){
                        System.out.println("Post is not created.");
                        return;
                    }
                    
                    controller.create(writer, id);
                    System.out.println("Writer created.");
                    break;
                    
                case 3:
                    List<Long> idPosts = new ArrayList<>();
                    System.out.println("Enter 'exit' to stop.");
                    System.out.println("Enter list id posts:");
                    String check = scanner.nextLine();
                    
                    while (true){
                        if (check.toLowerCase().equals("exit")){
                            break;
                        }

                        if (scanner.hasNextLong()){
                            id = scanner.nextLong();
                            if (postController.get(id) != null){
                                idPosts.add(id); 
                            }
                        }
                        else {
                            System.out.println("Please, enter a number.");
                            System.out.println();
                        }
                    }
                    controller.create(writer, idPosts);
                    System.out.println("Writer created.");
                    break;
                default:
                    System.out.println("Please, enter a number 1, 2 or 3.");
                    System.out.println("Writer is not created.");
                    break;
                        
            }
        }
        else {
            System.out.println("Please, enter a number.");
            System.out.println();
        }
        
    }
    
    public void showWriter(){
        Writer writer; 
        System.out.println("Enter id writer:"); 
        
        if (scanner.hasNextLong()){
            writer = controller.get(scanner.nextLong());       
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (writer == null){
            return;
        }
        
        System.out.println("Show list posts?"
                + "yes or no:");
        String check = scanner.nextLine();
        
        if (check.toLowerCase().equals("yes")){
            System.out.println("Writer id: " + writer.getId() + 
                               "\nWriter name: " + writer.getName());
            
            if (!writer.getPosts().isEmpty()){
                System.out.println("Writer posts:");
                writer.getPosts().forEach(elem -> System.out.println("Post id: " + elem.getId() +
                                                                     "\nPost name: " + elem.getName() +
                                                                     "\nPost content: " + elem.getContent() + 
                                                                     "\nLabels:\n" +
                                                                     elem.getLabels().toString()));
            }
            else {
                System.out.println("Writer posts: empty.");
            }
        }
        if (check.toLowerCase().equals("no")){
            System.out.println("Writer id: " + writer.getId() + 
                               "\nWriter name: " + writer.getName());
        }
    }
    
    public void showAllWriters(){
        LinkedList<Writer> writers = controller.getAll();
        
        if (writers == null){
            return;
        }
        
        System.out.println("Show list posts?"
                + "\nyes or no:");
        String check = scanner.nextLine();
        
        if (check.toLowerCase().equals("yes")){
            writers.forEach(elem -> {
                System.out.println("Writer id: " + elem.getId() + 
                               "\nWriter name: " + elem.getName());
                
                if (!elem.getPosts().isEmpty()){
                    System.out.println("Writer posts:");
                    elem.getPosts().forEach(post -> System.out.println("Post id: " + post.getId() +
                                                                         "\nPost name: " + post.getName() +
                                                                         "\nPost content: " + post.getContent() + 
                                                                         "\nLabels:\n" +
                                                                         post.getLabels().toString()));
                }
                else {
                    System.out.println("Post labels: empty.");
                }});
        }
        if (check.toLowerCase().equals("no")){
            writers.forEach(post -> {
                System.out.println("Writer id: " + post.getId() + 
                                   "\nWriter name: " + post.getName());
            });
        }
    }
    
    public void addPostInWriter(){
        long idWriter;
        long idPost;
        
        System.out.println("Enter id writer:");
        if (scanner.hasNextLong()){
            idWriter = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idWriter) == null){
            return;
        }
        
        System.out.println("Enter id post:");
        if (scanner.hasNextLong()){
            idPost = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (postController.get(idPost) == null){
            return;
        }
        
        controller.addPost(idWriter, idPost);
        System.out.println("Post added in writer.");
    }
    
    public void addPostsInWriter(){
        long idWriter;
        long idPost;
        List<Long> idPosts = new ArrayList<>();
        
        System.out.println("Enter id writer:");
        if (scanner.hasNextLong()){
            idWriter = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idWriter) == null){
            return;
        }
        
        System.out.println("Enter 'exit' to stop.");
        System.out.println("Enter list id posts:");
        
        String check = scanner.nextLine();
        while (true){
            if (check.toLowerCase().equals("exit")){
                break;
            }
            
            if (scanner.hasNextLong()){
                idPost = scanner.nextLong();
            }
            else {
                System.out.println("Please, enter a number.");
                continue;
            }

            if (postController.get(idPost) == null){
                continue;
            }
            
            idPosts.add(idPost);
        }
        
        controller.addPosts(idWriter, idPosts);
        System.out.println("Label added in post");
    }
    
    public void deletePostFromWriter(){
        long idWriter;
        long idPost;
        
        System.out.println("Enter id writer:");
        if (scanner.hasNextLong()){
            idWriter = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idWriter) == null){
            return;
        }
        
        System.out.println("Enter id post:");
        if (scanner.hasNextLong()){
            idPost = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (postController.get(idPost) == null){
            return;
        }
        
        controller.deletePost(idWriter, idPost);
        System.out.println("Post was deleted from writer.");
    }
    
    public void deletePostsFromWriter(){
        long idWriter;
        
        System.out.println("Enter id writer:");
        if (scanner.hasNextLong()){
            idWriter = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idWriter) == null){
            return;
        }
        
        controller.deletePosts(idWriter);
        System.out.println("Posts was deleted from writer.");
    }
    
    public void updateWriter(){
        long id;
        Writer writer;
        
        System.out.println("Enter id writer");
        
        if (scanner.hasNextLong()){
            id = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        writer = controller.get(id);
        
        if (writer == null){
            return;
        }
        
        System.out.println("Enter name:");     
        writer.setName(scanner.nextLine());
        
        controller.update(id, writer);
        System.out.println("Writer was updated.");
    }
    
    public void deleteWriter(){
        long id;
        Writer writer;
        
        System.out.println("Enter id writer");
        
        if (scanner.hasNextLong()){
            id = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        writer = controller.get(id);
        
        if (writer == null){
            return;
        }
        
        controller.delete(id);
        System.out.println("Writer was deleted.");
    }
}
