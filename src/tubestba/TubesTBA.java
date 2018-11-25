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
    
    public static StateFA S;
    public static StateFA P;
    public static StateFA O;
    public static StateFA K;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Meminta Inputan User
        System.out.println("Masukan Sebuah Kalimat");
        Scanner scanner = new Scanner( System.in );
        String kalimat = scanner.nextLine();
        
        // Memecah Kalimat Menjadi Beberapa Kata
        List<String> kata = parseString(kalimat);
        
        // Membuat Push Down Automata
        Stack<Character> stack = new Stack<>();
            
        StatePDA init = new StatePDA("I");
        StatePDA s = new StatePDA("s");
        StatePDA q0 = new StatePDA("q0");
        StatePDA q1 = new StatePDA("q1");
        StatePDA q2 = new StatePDA("q2");
        StatePDA q3 = new StatePDA("q3",true);
        
        init.addTransition(Character.MIN_VALUE, Character.MIN_VALUE, "S#", s);
        
        s.addTransition(Character.MIN_VALUE, 'S', "spX", q0);
        
        q0.addTransition('s', 's', "", q0);
        q0.addTransition('p', 'p', "", q0);
        q0.addTransition('o', 'X', "Y", q1);
        q0.addTransition('k', 'X', "", q2);
        q0.addTransition(Character.MIN_VALUE, 'X', "", q2);
        
        q1.addTransition(Character.MIN_VALUE, 'Y', "", q2);
        q1.addTransition('k', 'Y', "", q2);
        
        q2.addTransition(Character.MIN_VALUE, '#', "", q3);
        
        // Membuat Finite Automata
        CreateSubjek();
        CreatePredikat();
        CreateObjek();
        CreateKeterangan();
        
        // Menjalankan Mesin Automata
        StatePDA current = init;
        current = current.getNext(stack, Character.MIN_VALUE, Character.MIN_VALUE);
        current = current.getNext(stack, Character.MIN_VALUE, stack.pop());
        
        int i = 0;
        while(current!= null && i<kata.size()){
            current = current.getNext(stack, tokenRecognizer(kata.get(i)), stack.pop());
            i++;
        }
        
        while(current != null && !stack.isEmpty()){
            current = current.getNext(stack, Character.MIN_VALUE, stack.pop()); 
        }
        // Mengeluarkan Hasil Apakah Kalimat Diterima Atau Tidak
        System.out.println("Kata Diterima : "+(current != null?current.isFinal():false));
              
    }
    
    
    // Mendeteksi Sebuah Kata Adalah Subjek , Predikat , Objek atau Keterangan
    public static Character tokenRecognizer(String kata){
        if(isAccepted(kata , S)){
            return 's';
        }
        else if(isAccepted(kata , K)){
            return 'k';
        }
        else if(isAccepted(kata , O)){
            return 'o';
        }
        else if(isAccepted(kata , P)){
            return 'p';
        }
        return 'w';
    }
    
    // Memecah Kalimat Mendjadi Beberapa Kata
    public static List<String> parseString(String kalimat){
        kalimat = kalimat.toLowerCase();
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
    
    public static void CreateSubjek(){
        StateFA q0 = new StateFA("is");
        StateFA q1 = new StateFA("a");
        StateFA q2 = new StateFA("knblr");
        StateFA q4 = new StateFA("q4");
        StateFA q3 = new StateFA("iu",true);
        q0.addTransition('a', q1);
        q1.addTransition('k', q4);
        q1.addTransition('n', q2);
        q1.addTransition('b', q2);
        q1.addTransition('l', q2);
        q1.addTransition('r', q2);
        q2.addTransition('i', q3);
        q4.addTransition('u', q3);
        S=q0;
    }
    public static void CreatePredikat(){
        StateFA q0 = new StateFA("q0");
        StateFA q1 = new StateFA("q1");
        StateFA q2 = new StateFA("q2");
        StateFA q3 = new StateFA("q3");
        StateFA q4 = new StateFA("q4");
        StateFA q5 = new StateFA("q5");
        StateFA q6 = new StateFA("q6");
        StateFA q7 = new StateFA("q7");
        StateFA q8 = new StateFA("q8");
        StateFA q9 = new StateFA("q9");
        StateFA q10 = new StateFA("q10");
        StateFA q11 = new StateFA("q11");
        StateFA F = new StateFA("F",true);
        q0.addTransition('m', q1);
        
        q1.addTransition('a', q2);
        q1.addTransition('i', q9);
        
        q2.addTransition('i', q3);
        q2.addTransition('k', q4);
        q2.addTransition('s', q5);//
        q2.addTransition('n', q7);//
        
        q3.addTransition('n', F);
        
        q4.addTransition('a', q3);
        
        q5.addTransition('a', q6);
        
        q6.addTransition('k', F);
        
        q7.addTransition('d', q8);
        
        q8.addTransition('i', F);
        
        q9.addTransition('n', q10);
        
        q10.addTransition('u', q11);
        
        q11.addTransition('m', F);
        P = q0;
    }
    public static void CreateObjek(){
        StateFA q0 = new StateFA("is");
        StateFA q1 = new StateFA("b");
        StateFA q2 = new StateFA("a");
        StateFA q3 = new StateFA("o");
        StateFA q4 = new StateFA("u");
        StateFA q5 = new StateFA("s");
        StateFA q7 = new StateFA("jt");
        StateFA q6 = new StateFA("l");
        StateFA q8 = new StateFA("k");
        StateFA q9 = new StateFA("auo",true);

        q0.addTransition('b', q1);
        q1.addTransition('a', q2);
        q1.addTransition('o', q3);
        q1.addTransition('u', q4);
        q2.addTransition('s', q5);
        q2.addTransition('t', q7);
        q2.addTransition('j', q7);
        q3.addTransition('l', q6);
        q4.addTransition('k', q8);
        q6.addTransition('a', q9);
        q8.addTransition('u', q9);
        q5.addTransition('o', q9);
        q7.addTransition('u', q9);
        O = q0;
    }
    public static void CreateKeterangan(){
        //di kali , di kabin , di kota , di kantin , di kampus
        StateFA q0 = new StateFA("q0");
        StateFA q1a = new StateFA("q1a");
        StateFA q1b = new StateFA("q1b");
        StateFA q1c = new StateFA("q1c");
        StateFA q2 = new StateFA("q2");
        StateFA q3 = new StateFA("q3");//
        StateFA q4 = new StateFA("q4");//
        StateFA q5 = new StateFA("q5");
        StateFA q6 = new StateFA("q6");
        StateFA q7 = new StateFA("q7");
        StateFA q8 = new StateFA("q8");
        StateFA q9 = new StateFA("q9");
        StateFA q12= new StateFA("q12"); //
        StateFA q13= new StateFA("q13"); 
        StateFA q16= new StateFA("q16");
        StateFA q18= new StateFA("q18",true); //
        StateFA q19 = new StateFA("q19");


        q0.addTransition('d', q1a);
        q1a.addTransition('i', q1b);
        q1b.addTransition(' ', q1c);
        q1c.addTransition('k', q2);
        q2.addTransition('a', q3);
        q2.addTransition('o', q4);
        q3.addTransition('l', q5);
        q3.addTransition('b', q6);
        q4.addTransition('t', q7);
        q3.addTransition('n', q8);
        q3.addTransition('m', q9);   
        q5.addTransition('i', q18);
        q6.addTransition('i', q19);
        q19.addTransition('n', q18);
        q7.addTransition('a', q18);
        q8.addTransition('t', q12);
        q12.addTransition('i', q19);
        q9.addTransition('p', q13);
        q13.addTransition('u', q16);
        q16.addTransition('s', q18);          
        K = q0;
    }
    

 
    public static boolean isAccepted(String kata , StateFA initialState){
            StateFA current = initialState;
            int i=0;
            while(current!=null && i<kata.length()){
                current = current.getNext(kata.charAt(i));
                i++;
            }
            return current !=null ? current.getIsFinal() : false;
    }
    
    //Subjek
    //aku ani abi ali ari
    
    //predikat
    //main makan minum mandi masak
    
    //objek
    //bola boling baju baso botol
    
    //keterangan
    //di kali , di kabin , di kota , di kantin , di kampus    
    
}
