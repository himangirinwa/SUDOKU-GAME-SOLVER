import java.util.*;

class App {

    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        System.out.println("\n||+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++||");
        System.out.println("\t\t|| - SUDOKU GAME & SOLVER - ||");
        System.out.println("||+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++||");

        System.out.println("\n\n1. SOLVE SUDOKU");
        System.out.println("2. PLAY GAME");
        System.out.println("3. EXIT");
        System.out.print("ENTER CHOICE : ");
        int choice = in.nextInt();

        switch(choice){
            case 1 :
                Solver s = new Solver();
                s.menu();
                break; 

            case 2 : 
                Generator g = new Generator();
                g.menu();
                break;
            case 3 : 
                System.exit(0);
            default :
                System.out.println("INVALID CHOICE!!");
        }

        in.close();
    }
}