/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubestba;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author John
 */
public class StatePDA {
    
    private String name;
    private boolean isFinal;
    private List<TransitionPDA> transisi;
    
    public StatePDA(String name){
        this.name= name;
        transisi = new ArrayList<>();
        isFinal = false;
               
    }
    public StatePDA(String name , boolean isFinal){
        this.name= name;
        transisi = new ArrayList<>();
        this.isFinal = isFinal;
    }
    
    public String getName(){
        return name;
    }
    
    public void addTransition(Character read,Character pop,String push,StatePDA state){
        transisi.add(new TransitionPDA(read, pop, push, state));
    }
    
    public StatePDA getNext(Stack<Character> stack , Character read,Character pop){
        for(TransitionPDA trans:transisi){
            if(trans.getRead().equals(read) && trans.getPop().equals(pop)){
                for(int i=trans.getPush().length()-1; i>=0;i--){
                    stack.add(trans.getPush().charAt(i));
                }
                return trans.getNext();
            }
        }
        return null;
    }
    
    public boolean isFinal(){
        return isFinal;
    }
    
    
}
