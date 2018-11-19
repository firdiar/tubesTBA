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
        
        
        if(current != null){
            System.out.println("Kata Diterima : "+current.isFinal());
        }else{
            System.out.println("Kata Diterima : false");
        }
        
        
       
        
        
        
        
//        stack.clear();
//        stack.add('#');
//        stack.add('S');
//        processStack(stack, Character.MIN_VALUE , stack.pop());
//        
//        
//        
//        boolean stillCan = true;
//        int j = 0;
//        //System.out.println(processStack(stack, "s" , stack.pop()));
//        while(stillCan && j<kata.size()){
//            stillCan = processStack(stack, tokenRecognizer(kata.get(j)) , stack.pop());
//            j++;
//        }
//        
//        
//        boolean isAccepted = false;
//        if(stillCan){
//            emptyStack(stack);
//            isAccepted = stack.isEmpty();
//        }
//        
//        System.out.println("Kata Di Terima : "+isAccepted);
        
    }
    
    public static Character tokenRecognizer(String kata){
        if(isSubject(kata)){
            return 's';
        }

        if(isAdverb(kata)){
            return 'k';
        }
       

        if(isObject(kata)){
            return 'o';
        }
  

        if(isPredikat(kata)){
            return 'p';
        }

        return Character.MIN_VALUE;
    }



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
    

    
    //fungsi yang berisikan automata untuk mengecek CFG dari kalimat
//    public static boolean processStack(Stack<Character> stack , Character input , Character pop){
//        if(input.equals(Character.MIN_VALUE) && pop.equals('S')){
//            stack.add('X');
//            stack.add('p');
//            stack.add('s');
//            return true;
//        }else if(pop.equals('X')){
//            if(input .equals( 'o')){
//                stack.add('Y');
//                return true;
//            }else if(input.equals('k')){
//                
//                return true;
//            }else if(input.equals(Character.MIN_VALUE)){
//                return true;
//            }
//            return false;
//        }else if(pop.equals('Y')){
//            if(input.equals(Character.MIN_VALUE)){
//                return true;
//            }else if(input.equals( 'k')){
//                
//                return true;
//            }
//            return false;
//        }else if(Character.isLowerCase(input) && input.equals( pop)){
//            return true;
//        }
//        
//        return false;
//    
//    }
    public static boolean isAdverb(String kata){
            //di kali , di kabin , di kota , di kantin , di kampus

            StateFA q0 = new StateFA(" ");
            StateFA q1 = new StateFA("di ");
            StateFA q2 = new StateFA("k");
            StateFA q3 = new StateFA("ao");
            StateFA q4 = new StateFA("lbtnm");
            StateFA q5 = new StateFA("iatp");
            StateFA q6 = new StateFA("niu");
            StateFA q7 = new StateFA("ns",true);
            
            q0.addTransition('d', q1);
            q0.addTransition('i', q1);
            q0.addTransition(' ', q1);
            q1.addTransition('k', q2);
            q2.addTransition('a', q3);
            q2.addTransition('o', q3);
            q3.addTransition('l', q4);
            q3.addTransition('b', q4);
            q3.addTransition('t', q4);
            q3.addTransition('n', q4);
            q3.addTransition('m', q4);
            q4.addTransition('i', q5);
            q4.addTransition('a', q5);
            q4.addTransition('t', q5);
            q4.addTransition('p', q5);
            q5.addTransition('n', q6);
            q5.addTransition('i', q6);
            q5.addTransition('u', q6);
            q6.addTransition('n', q7);
            q6.addTransition('s', q7);
            StateFA current = q0;
            int i=0;
            while(current!=null && i<kata.length()){
                current = current.getNext(kata.charAt(i));
                i++;
            }
            return current !=null ? current.getIsFinal():false;
    }
    
    public static boolean isObject(String kata){
    //bola batu baju baso buku

        StateFA q0 = new StateFA("is");
        StateFA q1 = new StateFA("b");
        StateFA q2 = new StateFA("oau");
        StateFA q3 = new StateFA("ltjsk");
        StateFA q4 = new StateFA("auo",true);
        q0.addTransition('b', q1);
        q1.addTransition('a', q2);
        q1.addTransition('o', q2);
        q1.addTransition('u', q2);
        q2.addTransition('l', q3);
        q2.addTransition('t', q3);
        q2.addTransition('j', q3);
        q2.addTransition('s', q3);
        q2.addTransition('k', q3);
        q3.addTransition('a', q4);
        q3.addTransition('u', q4);
        q3.addTransition('o', q4);
        
        StateFA current = q0;
        int i =0;
        while(current!=null && i<kata.length()){
            current = current.getNext(kata.charAt(i));
            i++;
        }
        return current == null ? false : current.getIsFinal();
                
                
    }

    public static boolean isSubject(String kata){
            // aku ani abi ali ari

            StateFA q0 = new StateFA("is");
            StateFA q1 = new StateFA("a");
            StateFA q2 = new StateFA("knblr");
            StateFA q3 = new StateFA("iu",true);
            q0.addTransition('a', q1);
            q1.addTransition('k', q2);
            q1.addTransition('n', q2);
            q1.addTransition('b', q2);
            q1.addTransition('l', q2);
            q1.addTransition('r', q2);
            q2.addTransition('i', q3);
            q2.addTransition('u', q3);

            StateFA current = q0;
            int i=0;
            while(current!=null && i<kata.length()){
                current = current.getNext(kata.charAt(i));
                i++;
            }

            return current==null ? false : current.getIsFinal();

    }

    public static boolean isPredikat(String kata){
        
        //main makan minum mandi masak
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
        
        
        
        

        StateFA current = q0;
        int i=0;
        while(current!=null && i<kata.length()){
            current = current.getNext(kata.charAt(i));
            i++;
        }
        
        return current!= null? current.getIsFinal():false;
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
