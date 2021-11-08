
package com.mikhail_golovackii.consoleapplication.view;

import com.mikhail_golovackii.consoleapplication.controller.LabelController;
import com.mikhail_golovackii.consoleapplication.controller.PostController;
import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.model.Post;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    
    private final PostController controller = new PostController();
    private final LabelController labelController = new LabelController();
    private Scanner scanner = new Scanner(System.in);
    
    public void createPost(){
        System.out.println("Enter name post:");
        String name = scanner.nextLine();
        System.out.println("Enter content post:");
        String content = scanner.nextLine();

        System.out.println("Add label in post?\n"
                + "Enter number:\n"
                + "1: no\n"
                + "2: add label\n"
                + "3: add labels\n");
        
        if (scanner.hasNextInt()){
            int answer = scanner.nextInt();
            long id;
            Post post = new Post(name, content);
            Label label;
            
            switch (answer){
                
                case 1:
                    controller.create(post);
                    System.out.println("Post created.");
                    break;
                    
                case 2:
                    System.out.println("Enter id label:");

                    if (scanner.hasNextLong()){
                        id = scanner.nextLong();
                    }
                    else {
                        System.out.println("Please, enter a number.");
                        System.out.println("Post is not created.");
                        return;
                    }
                    
                    label = labelController.get(id);
                    if (label == null){
                        System.out.println("Post is not created.");
                        return;
                    }
                    
                    controller.create(post, id);
                    System.out.println("Post created.");
                    break;
                    
                case 3:
                    List<Long> idList = new ArrayList<>();
                    System.out.println("Enter 'exit' to stop.");
                    System.out.println("Enter list id labels:");
                    String check = scanner.nextLine();
                    
                    while (true){
                        if (check.toLowerCase().equals("exit")){
                            break;
                        }

                        if (scanner.hasNextLong()){
                            id = scanner.nextLong();
                            if (labelController.get(id) != null){
                                idList.add(id); 
                            }
                        }
                        else {
                            System.out.println("Please, enter a number.");
                            System.out.println();
                        }
                    }
                    controller.create(post, idList);
                    System.out.println("Post created.");
                    break;
                default:
                    System.out.println("Please, enter a number 1, 2 or 3.");
                    System.out.println("Post is not created.");
                    break;
                        
            }
        }
        else {
            System.out.println("Please, enter a number.");
            System.out.println();
        }
    }
    
    public void showPost(){
        System.out.println("Enter id post:");
        Post post;  
        
        if (scanner.hasNextLong()){
            post = controller.get(scanner.nextLong());       
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (post == null){
            return;
        }
        
        System.out.println("Show list labels?"
                + "yes or no:");
        String check = scanner.nextLine();
        
        if (check.toLowerCase().equals("yes")){
            System.out.println("Post id: " + post.getId() + 
                               "Post name: " + post.getName() +
                               "Post content: " + post.getContent());
            if (!post.getLabels().isEmpty()){
                System.out.println("Post labels:");
                post.getLabels().forEach(elem -> System.out.println("Label id: " + elem.getId() +
                                                                    "Label name: " + elem.getName()));
            }
            else {
                System.out.println("Post labels: empty.");
            }
        }
        if (check.toLowerCase().equals("no")){
            System.out.println("Post id: " + post.getId() + 
                               "Post name: " + post.getName() +
                               "Post content: " + post.getContent());
        }
    }
    
    public void showAllPosts(){
        LinkedList<Post> posts = controller.getAll();
        
        if (posts == null){
            return;
        }
        
        System.out.println("Show list labels?"
                + "yes or no:");
        String check = scanner.nextLine();
        
        if (check.toLowerCase().equals("yes")){
            posts.forEach(post -> {
                System.out.println("Post id: " + post.getId() + 
                               "Post name: " + post.getName() +
                               "Post content: " + post.getContent());
                if (!post.getLabels().isEmpty()){
                    System.out.println("Post labels:");
                    post.getLabels().forEach(elem -> System.out.println("Label id: " + elem.getId() +
                                                                        "Label name: " + elem.getName()));
                }
                else {
                    System.out.println("Post labels: empty.");
                }
            });
            
        }
        if (check.toLowerCase().equals("no")){
            posts.forEach(post -> {
                System.out.println("Post id: " + post.getId() + 
                               "Post name: " + post.getName() +
                               "Post content: " + post.getContent());
            });
        }
    }
    
    public void addLabelInPost(){
        long idPost;
        long idLabel;
        
        System.out.println("Enter id post:");
        if (scanner.hasNextLong()){
            idPost = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idPost) == null){
            return;
        }
        
        System.out.println("Enter id label:");
        if (scanner.hasNextLong()){
            idLabel = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (labelController.get(idLabel) == null){
            return;
        }
        
        controller.addLabel(idPost, idLabel);
        System.out.println("Label added in post.");
    }
    
    public void addLabelsInPost(){
        long idPost;
        long idLabel;
        List<Long> idLabels = new ArrayList<>();
        
        System.out.println("Enter id post:");
        if (scanner.hasNextLong()){
            idPost = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idPost) == null){
            return;
        }
        
        System.out.println("Enter 'exit' to stop.");
        System.out.println("Enter list id labels:");
        
        String check = scanner.nextLine();
        while (true){
            if (check.toLowerCase().equals("exit")){
                break;
            }
            
            if (scanner.hasNextLong()){
                idLabel = scanner.nextLong();
            }
            else {
                System.out.println("Please, enter a number.");
                continue;
            }

            if (labelController.get(idLabel) == null){
                continue;
            }
            
            idLabels.add(idLabel);
        }
        
        controller.addLabels(idPost, idLabels);
        System.out.println("Label added in post");
    }
    
    public void deleteLabelFromPost(){
        long idPost;
        long idLabel;
        
        System.out.println("Enter id post:");
        if (scanner.hasNextLong()){
            idPost = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idPost) == null){
            return;
        }
        
        System.out.println("Enter id label:");
        if (scanner.hasNextLong()){
            idLabel = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (labelController.get(idLabel) == null){
            return;
        }
        
        controller.deleteLabel(idPost, idLabel);
        System.out.println("Label was deleted from post.");
    }
    
    public void deleteLabelsFromPost(){
        long idPost;
        
        System.out.println("Enter id post:");
        if (scanner.hasNextLong()){
            idPost = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        if (controller.get(idPost) == null){
            return;
        }
        
        controller.deleteLabels(idPost);
        System.out.println("Labels was deleted from post.");
    }
    
    public void updatePost(){
        long id;
        Post post;
        
        System.out.println("Enter id post");
        
        if (scanner.hasNextLong()){
            id = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        post = controller.get(id);
        
        if (post == null){
            return;
        }
        
        System.out.println("Enter name:");     
        post.setName(scanner.nextLine());
        System.out.println("Enter content:");
        post.setContent(scanner.nextLine());
        
        controller.update(id, post);
        System.out.println("Post was updated.");
    }
    
    public void deletePost(){
        long id;
        Post post;
        
        System.out.println("Enter id post");
        
        if (scanner.hasNextLong()){
            id = scanner.nextLong();
        }
        else {
            System.out.println("Please, enter a number.");
            return;
        }
        
        post = controller.get(id);
        
        if (post == null){
            return;
        }
        
        controller.delete(id);
        System.out.println("Post was deleted.");
    }
}
