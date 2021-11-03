
package com.mikhail_golovackii.consoleapplication;

import com.mikhail_golovackii.consoleapplication.model.Label;
import com.mikhail_golovackii.consoleapplication.repository.impl.JsonLabelRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        
        Label label = new Label("new");
        
        JsonLabelRepositoryImpl o = new JsonLabelRepositoryImpl();
        Long l = 9999999999999999l;
//        o.create(new Label("one"));
//        o.create(new Label("two"));
//        o.create(new Label("tree"));
//        o.create(new Label("four"));
//        o.create(new Label("five"));
//        o.create(new Label("six"));

        System.out.println(o.get((long)3));
//        System.out.println(o.getAll());
//        
//        o.update((long) 3, label);
//        System.out.println(o.get((long)3));
        
//        o.delete((long) 1);
//        o.update(l, label);
    }
}
