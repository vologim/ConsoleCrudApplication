
package com.mikhail_golovackii.consoleapplication;

import com.mikhail_golovackii.consoleapplication.view.LabelView;
import com.mikhail_golovackii.consoleapplication.view.PostView;
import com.mikhail_golovackii.consoleapplication.view.WriterView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");
        
        final LabelView labelView = new LabelView();
        final PostView postView = new PostView();
        final WriterView writerView = new WriterView();
        
        Scanner scanner = new Scanner(System.in);
        String check;
        
        while (true){
            System.out.println("Enter:");
            System.out.println("1: create label, post or writer");
            System.out.println("2: show label, post or writer");
            System.out.println("3: update");
            System.out.println("4: delete");
            System.out.println("exit: exit the application");
            
            check = scanner.nextLine();
            
            switch (check.toLowerCase()){
                case "1":
                    System.out.println("Create:");
                    System.out.println("1: label");
                    System.out.println("2: post");
                    System.out.println("3: writer");
                    System.out.println("enter any value to exit");
                    
                    check = scanner.nextLine();
                        
                    switch (check.toLowerCase()){
                        case "1":
                            labelView.createLabel();
                            break;
                        case "2":
                            postView.createPost();
                            break;
                        case "3":
                            writerView.createWriter();
                            break;
                        default:
                            System.out.println("Element not created");
                            break;    
                    }
                    break;
                case "2":
                    System.out.println("Show:");
                    System.out.println("1: label");
                    System.out.println("2: post");
                    System.out.println("3: writer");
                    System.out.println("4: all labels");
                    System.out.println("5: all posts");
                    System.out.println("6: all writers");
                    System.out.println("enter any value to exit");
                    
                    check = scanner.nextLine();
                        
                    switch (check.toLowerCase()){
                        case "1":
                            labelView.showLabel();
                            break;
                        case "2":
                            postView.showPost();
                            break;
                        case "3":
                            writerView.showWriter();
                            break;
                        case "4":
                            labelView.showAllLabels();
                            break;
                        case "5":
                            postView.showAllPosts();
                            break;
                        case "6":
                            writerView.showAllWriters();
                            break;
                        default:
                            System.out.println("Element not created");
                            break;    
                    }
                    break;
                case "3":
                    System.out.println("Update:");
                    System.out.println("1: label");
                    System.out.println("2: post");
                    System.out.println("3: writer");
                    System.out.println("4: add label in post");
                    System.out.println("5: add labels in post");
                    System.out.println("6: add post in writer");
                    System.out.println("7: add posts in writer");
                    System.out.println("8: delete label from post");
                    System.out.println("9: delete labels from post");
                    System.out.println("10: delete post from writer");
                    System.out.println("11: delete posts from writer");
                    System.out.println("enter any value to exit");
                    
                    check = scanner.nextLine();
                        
                    switch (check.toLowerCase()){
                        case "1":
                            labelView.updateLabel();
                            break;
                        case "2":
                            postView.updatePost();
                            break;
                        case "3":
                            writerView.updateWriter();
                            break;
                        case "4":
                            postView.addLabelInPost();
                            break;
                        case "5":
                            postView.addLabelsInPost();
                            break;
                        case "6":
                            writerView.addPostInWriter();
                            break;
                        case "7":
                            writerView.addPostsInWriter();
                            break;
                        case "8":
                            postView.deleteLabelFromPost();
                            break;
                        case "9":
                            postView.deleteLabelsFromPost();
                            break;
                        case "10":
                            writerView.deletePostFromWriter();
                            break;
                        case "11":
                            writerView.deletePostsFromWriter();
                            break;
                        default:
                            System.out.println("Element not created");
                            break;    
                    }
                    break;
                case "4":
                    System.out.println("Delete:");
                    System.out.println("1: label");
                    System.out.println("2: post");
                    System.out.println("3: writer");
                    System.out.println("enter any value to exit");
                    
                    check = scanner.nextLine();
                        
                    switch (check.toLowerCase()){
                        case "1":
                            labelView.deleteLabel();
                            break;
                        case "2":
                            postView.deletePost();
                            break;
                        case "3":
                            writerView.deleteWriter();
                            break;
                        default:
                            System.out.println("Element not created");
                            break;    
                    }
                    break;
                case "exit":
                    System.out.println("Bye!");
                    return;
                default:
                    break;  
            }
        }
    }
}