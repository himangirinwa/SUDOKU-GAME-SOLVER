import java.util.*;
import java.io.*;

public class Solver {
    Scanner in = new Scanner(System.in);
    protected int[][] Arr = new int[9][9];
    void menu(){
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("\n- - MENU - -");
        System.out.println("\n1. ENTER SUDOKU MANUALLY");
        System.out.println("2. READ SUDOKU FROM A FILE");
        System.out.println("3. EXIT");
        System.out.print("ENTER CHOICE : ");
        int choice = in.nextInt();

        switch(choice){
            case 1:
                System.out.println("\n\n ENTER SUDOKU : \n(NOTE : ENTER 0 IN PLACE OF EMPTY CELL)");
                for(int i=0; i<9; i++){
                    for(int j=0; j<9; j++){
                        Arr[i][j] = in.nextInt();
                    }
                }
                
                if(sudoSolver(0, 0)){
                    System.out.println("\n---------------------------------------------------------------\n");
                    System.out.println("\n\tSOLUTION : \n");
                    printBoard(Arr);
                    System.out.println("\n---------------------------------------------------------------\n\n");
                }
                else{
                    System.out.println("\n\n\n\tSOLUTION DOESN'T EXIST FOR THE GIVEN SUDOKU!");
                }
                break;

            case 2:
                System.out.print("\n\n ENTER FILE NAME : ");
                String file = in.next();
                in.close();
                try {
                    File myObj = new File(file);
                    in = new Scanner(myObj);
                    
                    for(int i=0; i<9; i++){
                        for(int j=0; j<9; j++){
                            if(in.hasNext())
                                Arr[i][j] = in.nextInt();
                        }
                    }
                    in.close();
                  
                } catch (FileNotFoundException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
                }
                if(sudoSolver(0, 0)){
                    System.out.println("\n---------------------------------------------------------------\n\n");
                    System.out.println("\n\tSOLUTION : \n");
                    printBoard(Arr);
                    System.out.println("\n---------------------------------------------------------------\n\n");
                }
                else{
                    System.out.println("\n\n\n\tSOLUTION DOESN'T EXIST FOR THE GIVEN SUDOKU!");
                }
                break;
                

            case 3: 
                System.exit(1);
            
            default :
                System.out.println("INVALID CHOICE!!");
        }
    }

    //function to print the sudoku board
    void printBoard(int[][] Arr)
    {
        System.out.println("\n\t|- - - - - - - - - - - - - - - - - - - |");

        for (int i = 0; i < 9; i++)
        {
            System.out.print("\t   ");
            if( i%3 == 0 && i != 0)
            System.out.print("- - - - - - - - - - - - - - - - - - -\n\t   ");

            for (int j = 0; j < 9; j++)
            {
                if (j % 3 == 0 && j != 0 )
                    System.out.print("  |  ");

                System.out.print(Arr[i][j] + "  ");
            }

            System.out.println();
        }

        System.out.println("\t|- - - - - - - - - - - - - - - - - - - |");
    }

    boolean isValid(int num, int r, int c)
    {
        int i;

        //row check
        for(i=0; i<9; i++)
        {
            if(Arr[r][i]== num && c!= i)
                return false;
        }

        //column check
        for( i=0; i<9; i++)
        {
            if(Arr[i][c]== num && r!= i)
                return false;
        }


        //check subgrid (3x3)
        int m = (int) Math.floor((r)/3);
        //floor() returns the largest possible integer value
        // which is less than or equal to the given argument.
        int n = (int) Math.floor((c)/3);

        for(int g= m*3; g < ( m*3 + 3); g++)
            for(int h= n*3; h<( n*3 + 3); h++)
            {
                if(Arr[g][h]==num && (!(g == m && h == n) ))
                    return false;
            }

        return true;

    }

    boolean sudoSolver(int r, int c){
        if(r == 8 && c == 9)
            return true;
        /*checks if the search has reached the end of the
        sudoku to avoid further bactracking*/

        if(c == 9){
            r++;
            c = 0;
        }
        /*this checks if the column value has become 9 and has gone out of
        limit of sudoku . if yes, then it moves the cursor next row first column*/

        //searching for empty cell
        if(Arr[r][c] > 0)
        {
            return sudoSolver( r, c+1);
        }

        for (int var = 1 ; var < 10 ; var++)
        {

            //this checks if the value we will be inserting will be valid or not
            if(isValid(var, r, c))
            {
                Arr[r][c] = var;

                if (sudoSolver(r, c + 1))
                    return true;
            }

            //removing the assigned number since our assumption was wrong .
            // and we go for next assumption

            Arr[r][c] = 0;
        }

        return false;
    }

}

