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
        return 'w';
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
    
//    public static void emptyStack(Stack<Character> stack){
//        
//        boolean stillCan = true;
//        while (stillCan && !stack.isEmpty()) {            
//            stillCan = processStack(stack, Character.MIN_VALUE , stack.pop());
//        }
//    
//    }
    
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
        boolean k= false;
        
        if (kata.charAt(0)=='d' && kata.charAt(1)=='i' && kata.charAt(2)==' '&& kata.length()>=8){
           if(kata.charAt(3)=='k'){
               if(kata.charAt(4)=='a'){
                   if(kata.charAt(5)=='n'){
                       if( kata.charAt(6)=='t'){
                        if (kata.charAt(7)=='i'){
                           if (kata.length()> 8 && kata.charAt(8)=='n'){
                             k = true;
                        }
                       }
                   }
                     }
               }
            }
           
           if(kata.charAt(3)=='k'){
               if(kata.charAt(4)=='a'){
                   if(kata.charAt(5)=='m'){
                       if( kata.charAt(6)=='p'){
                        if (kata.charAt(7)=='u'){
                           if (kata.length()> 8&& kata.charAt(8)=='s'){
                             k = true; 
                        }
                       }
                   }
                     }
               }
            }
           
           
           if(kata.charAt(3)=='r'){
               if(kata.charAt(4)=='u'){
                   if(kata.charAt(5)=='m'){
                       if( kata.charAt(6)=='a'){
                        if (kata.charAt(7)=='h'){
                            k =true;
                       }
                   }
                     }
               }
            }
           
           
           if(kata.charAt(3)=='k'){
               if(kata.charAt(4)=='o'){
                   if(kata.charAt(5)=='s'){
                       if( kata.charAt(6)=='a'){
                        if (kata.charAt(7)=='n'){
                           k =true;
                        }
                   }
                     }
               }
            }
           
           
           
           if(kata.charAt(3)=='l'){
               if(kata.charAt(4)=='a'){
                   if(kata.charAt(5)=='p'){
                       if( kata.charAt(6)=='a'){
                        if (kata.charAt(7)=='n'){
                           if (kata.length()> 10 &&kata.charAt(8)=='g'){
                             if (kata.charAt(9)=='a'){
                                 if (kata.charAt(10)=='n'){
                             k = true;
                        }
                        }
                        }
                       }
                   }
                     }
               }
            }
           
        } 
        return k;
    }
    
    public static boolean isObject(String kata){
    //bola batu baju baso buku

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

            StateFA current = q0;
            int i=0;
            while(current!=null && i<kata.length()){
                current = current.getNext(kata.charAt(i));
                i++;
            }
            return current==null ? false : current.getIsFinal();
    }
    
    
    public static boolean isPredikat(String kata){
        boolean s=false;

            if(kata.charAt(0)=='m' && kata.length()>=4){
               if(kata.charAt(1)=='a'){
                   if(kata.charAt(2)=='i'){
                       if(kata.charAt(3)=='n'){
                            s=true;
                        }
                   }
                   if(kata.charAt(2)=='k'){
                       if(kata.charAt(3)=='a'){
                           if(kata.length()>4 && kata.charAt(4)=='n'){
                            s=true;
                            }
                        }
                   }
                   if(kata.charAt(2)=='n'){
                       if(kata.charAt(3)=='d'){
                           if(kata.length()>4 &&kata.charAt(4)=='i'){
                            s=true;
                            }
                        }
                   }
                   if(kata.charAt(2)=='r'){
                       if(kata.charAt(3)=='a'){
                           if(kata.length()>4 &&kata.charAt(4)=='h'){
                            s=true;
                            }
                        }
                   }
               }
               if(kata.charAt(1)=='i'){
                   if(kata.charAt(2)=='n'){
                       if(kata.charAt(3)=='u'){
                           if(kata.length()>4 &&kata.charAt(4)=='m'){
                                s=true;
                            }
                        }
                   }
               }
            }
          
            return s;  
            
    }
    
    //Subjek
    // aku ani abi andi ari
    
    //predikat
    //main makan minum mandi marah
    
    //objek
    //bola boling baju baso botol
    
    //keterangan
    //di lapangan , di rumah , di kosan , di kantin , di kampus
    
    
}
