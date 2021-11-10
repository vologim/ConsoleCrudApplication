
package com.mikhail_golovackii.consoleapplication.view;

import com.mikhail_golovackii.consoleapplication.controller.LabelController;
import com.mikhail_golovackii.consoleapplication.model.Label;
import java.util.LinkedList;
import java.util.Scanner;

public class LabelView {
    
    private final LabelController controller = new LabelController();
    private Scanner scanner = new Scanner(System.in);
    private String check;
    
    public void createLabel(){
        System.out.println("Enter name label:");
        check = scanner.next();
        
        Label label = new Label(check);
        controller.create(label);
        
        System.out.println("Label created.");
    }
    
    public void showLabel(){
        System.out.println("Enter id label:");
              
        if (scanner.hasNextLong()){
            Label label = controller.get(scanner.nextLong());
            
            if (label == null){
                return;
            }
            
            System.out.println("Label id: " + label.getId());
            System.out.println("\nLabel name: " + label.getName());
        }
        else {
            System.out.println("Please, enter a number.");
            System.out.println();
        }
    }
    
    public void showAllLabels(){
        LinkedList<Label> labels = controller.getAll();
        
        if (labels != null){
            labels.forEach(elem -> System.out.println("Label id: " + elem.getId() + 
                                                      "\nLabel name: " + elem.getName() + "\n"));
        }
    }
    
    public void updateLabel(){
        System.out.println("Enter id lable:");
        
        if (scanner.hasNextLong()){
            Long id = scanner.nextLong();
            Label label = controller.get(id);
            
            if (label != null){
                System.out.println("Enter name lable:");
                check = scanner.next();
                label.setName(check);
                controller.update(id, label);
            }
        }
        else {
            System.out.println("Please, enter a number.");
            System.out.println();
        }
    }
    
    public void deleteLabel(){
        System.out.println("Enter id label:");
        
        if (scanner.hasNextLong()){
            Long id = scanner.nextLong();
            Label label = controller.get(id);
            
            if (label != null){
                System.out.println("Delete label?");
                System.out.println("id: " + label.getId());
                System.out.println("name: " + label.getName());
                System.out.println("Enter yes/no:");
                
                check = scanner.next();
                
                switch (check.toLowerCase()){
                    case "yes":
                        controller.delete(id);
                        System.out.println("Label was deleted.");
                        break;
                    case "no":
                        System.out.println("Label has not been deleted.");
                        break;
                    default:
                        System.out.println("Need enter yes or no.");
                }
            }
        }
        else {
            System.out.println("Please, enter a number.");
            System.out.println();
        }
    }
}
