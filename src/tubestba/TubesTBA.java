/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubestba;

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
        String[] kata = parseString(kalimat);
        
        Stack<Character> stack = new Stack<>();
        stack.add('#');
        stack.add('S');
        processStack(stack, Character.MIN_VALUE , stack.pop());
        
        
        
        boolean stillCan = true;
        int i = 0;
        //System.out.println(processStack(stack, "s" , stack.pop()));
        while(stillCan && i<kata.length){
            stillCan = processStack(stack, tokenRecognizer(kata[i]) , stack.pop());
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
        return kata.charAt(0);
    }
    public static String[] parseString(String kalimat){
        
        return kalimat.split(" ") ;
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
