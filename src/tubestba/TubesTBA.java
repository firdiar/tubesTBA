/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubestba;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

/**
 *
 * @author John
 */
public class TubesTBA {
    
    
    //Character.MIN_VALUE means ''
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Masukan Sebuah Kalimat");
        Scanner scanner = new Scanner( System.in );
        String kalimat = scanner.nextLine();
        List<String> kata = parseString(kalimat);
        
        Stack<Character> stack = new Stack<>();
        stack.add('#');
        stack.add('S');
        processStack(stack, Character.MIN_VALUE , stack.pop());
        
        
        
        boolean stillCan = true;
        int i = 0;
        //System.out.println(processStack(stack, "s" , stack.pop()));
        while(stillCan && i<kata.size()){
            stillCan = processStack(stack, tokenRecognizer(kata.get(i)) , stack.pop());
            i++;
        }
        
        
        boolean isAccepted = false;
        if(stillCan){
            emptyStack(stack);
            isAccepted = stack.isEmpty();
        }
        
        System.out.println("Kata Di Terima : "+isAccepted);
        
    }
    
    public static Character tokenRecognizer(String kata){
        if(isSubject(kata)){
            return 's';
        }
        if(isObject(kata)){
            return 'o';
        }
        return kata.charAt(0);
    }
    public static boolean isObject(String kata){
        boolean s=false;
            
        if(kata.length()==4){
             if(kata.charAt(0)=='s'){
               if(kata.charAt(1)=='o'){
                   if(kata.charAt(2)=='d'){
                       if(kata.charAt(3)=='a'){
                       s=true;
                       }
                   }
               }
            }
        }
        if(kata.length()==3){
            if(kata.charAt(0)=='k'){
               if(kata.charAt(1)=='u'){
                   if(kata.charAt(2)=='e'){
                       s=true;
                     }
               }
            }
        }
        if(kata.length()==4){
            if(kata.charAt(0)=='b'){
               if(kata.charAt(1)=='o'){
                   if(kata.charAt(2)=='l'){
                       if(kata.charAt(3)=='a'){
                       s=true;
                       }
                   }
               }
            }
        }
        if(kata.length()==6){
             if(kata.charAt(0)=='h'){
               if(kata.charAt(1)=='a'){
                   if(kata.charAt(2)=='d'){
                      if(kata.charAt(3)=='i'){
                          if(kata.charAt(4)=='a'){
                              if(kata.charAt(5)=='h')
                                s=true;   
                          }
                      }
                         
                   }
               }
            }
        }
        if(kata.length()==5){     
             if(kata.charAt(0)=='t'){
               if(kata.charAt(1)=='u'){
                   if(kata.charAt(2)=='g'){
                      if(kata.charAt(3)=='a'){
                          if(kata.charAt(4)=='s')
                              s=true; 
                      }
                         
                   }
               }
            }
        }
            return s;  
            
    }

    public static boolean isSubject(String kata){
        boolean s=false;
        if(kata.length()==3){
            if(kata.charAt(0)=='a'){
               if(kata.charAt(1)=='k'){
                   if(kata.charAt(2)=='u'){
                       s=true;
                     }
               }
            }
            else if(kata.charAt(0)=='d'){
                    if(kata.charAt(1)=='i'){
                        if(kata.charAt(2)=='a'){
                            s=true;
                   }
               }
            }
        }
        if(kata.length()==4){
             if(kata.charAt(0)=='k'){
               if(kata.charAt(1)=='a'){
                   if(kata.charAt(2)=='m'){
                      if(kata.charAt(3)=='u')
                          s=true;
                   }
               }else if(kata.charAt(1)=='i'){
                   if(kata.charAt(2)=='t'){
                      if(kata.charAt(3)=='a')
                          s=true;
                   }
               }
            }
             
             if(kata.charAt(0)=='s'){
               if(kata.charAt(1)=='a'){
                   if(kata.charAt(2)=='y'){
                      if(kata.charAt(3)=='a')
                          s=true;
                   }
               }
            }
        }
            return s;  
            
    }

    public static List<String> parseString(String kalimat){
        //String[] kata = kalimat.split(" ");
        int i=0;
        String temp = "";
        List<String> kata = new ArrayList<>();
        
        while(i < kalimat.length()){
            if(kalimat.charAt(i) != ' '){
               temp += kalimat.charAt(i);
            }else if(kalimat.charAt(i) == ' '&& !temp.equals("di")){
                kata.add(temp);
                temp = "";
            }else if(temp.equals("di")){
                temp+=" ";
            }
            i++;
        }
        kata.add(temp);
        
        return kata;
    }
    
    public static void emptyStack(Stack<Character> stack){
        
        boolean stillCan = true;
        while (stillCan && !stack.isEmpty()) {            
            stillCan = processStack(stack, Character.MIN_VALUE , stack.pop());
        }
    
    }
    
    //fungsi yang berisikan automata untuk mengecek CFG dari kalimat
    public static boolean processStack(Stack<Character> stack , Character input , Character pop){
        if(input.equals(Character.MIN_VALUE) && pop.equals('S')){
            stack.add('X');
            stack.add('p');
            stack.add('s');
            return true;
        }else if(pop.equals('X')){
            if(input .equals( 'o')){
                stack.add('Y');
                return true;
            }else if(input.equals('k')){
                
                return true;
            }else if(input.equals(Character.MIN_VALUE)){
                return true;
            }
            return false;
        }else if(pop.equals('Y')){
            if(input.equals(Character.MIN_VALUE)){
                return true;
            }else if(input.equals( 'k')){
                
                return true;
            }
            return false;
        }else if(Character.isLowerCase(input) && input.equals( pop)){
            return true;
        }
        
        return false;
    
    }
    
    //Subjek
    // aku kamu dia saya kita
    
    //predikat
    //memainkan mengerjakan memberikan memakan meminum
    
    //objek
    //bola tugas hadiah kue soda
    
    //keterangan
    //di lapangan , di rumah , di kosan , di kantin , di kampus
    
    
}
