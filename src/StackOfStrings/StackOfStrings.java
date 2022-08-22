package StackOfStrings;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class StackOfStrings {

    private ArrayList<String>data;

    public StackOfStrings(){
        this.data = new ArrayList<>();
    }

    public void push(String item){
        this.data.add(item);
    }
    public String pop(){
        if (isEmpty()){
            return null;
        }
        return data.remove(data.size() - 1);
    }
    public String peek(){
        if (isEmpty()){
            return null;
        }
        return data.get(data.size() - 1);
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
}
