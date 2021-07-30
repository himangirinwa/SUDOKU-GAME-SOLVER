import java.util.*;

public class Generator extends  Solver{
    Scanner in = new Scanner(System.in);
    int[] initial = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 
    int[][] solution = new int[9][9];
    Generator(){
        //initializing
        for(int i =0; i<9; i++){
            for(int j =0; j<9; j++){
                Arr[i][j] = 0;
            }
        }
    }
    
    void menu(){
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("\n\t\t\t\t-- WELCOME TO THE GAME -- ");
        System.out.print("\n\n\t\tINSTRUCTONS :  \n\t\t\t1 . ENTER COORDINATES OF THE ELEMENT TO BE INSERTED\n\t\t\t IN THE FORMAT (row_number) (col_number) . \n");
        System.out.print("\n\t\t\t2 . AN ELEMENT SHOULD APPEAR ONLY ONCE IN ITS ROW, COLUMN , AND BLOCK(3*3) .");
        System.out.print("\n\t\t\t3 . ENTER -1 TO EXIT");
        System.out.print("\n\n\n\t\tCHOOSE COMPLEXITY FOR YOUR PROBLEM STATEMENT : \n");
        System.out.print("\t\t1 . SIMPLE \n\t\t2 . MEDIUM \n\t\t3 . HARD \n\n\t\t\tENTER CHOICE : ");
        int choice = in.nextInt();
        int min, max, rand;
        switch(choice){
            case 1:
                min = 12;   max = 29;
                rand = (int) ((Math.random() * (max - min)) + min);;
                // System.out.println("RAND : "+ rand);
                this.sudoGnenerator(rand);
                break;
            
            case 2:
                min = 30;   max = 52;
                rand = (int) ((Math.random() * (max - min)) + min);
                // System.out.println("RAND : "+ rand);
                this.sudoGnenerator(rand);
                break;
            
            case 3:
                min = 53;   max = 75;
                rand =(int) ((Math.random() * (max - min)) + min);
                // System.out.println("RAND : "+ rand);
                this.sudoGnenerator(rand);
                break;
        }
    }
    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    //function to generate sudoku
    void sudoGnenerator(int voids){
        // System.out.println("Entered sudo generator");
        randomize(initial, 9);
        // System.out.println("INITAIL ARRAY = ");
        for(int i=0; i<9; i++){
            Arr[0][i] = initial[i];
            // System.out.print(initial[i]);
        }

        boolean b = sudoSolver(0, 0);
        // System.out.println("Sudo Solved");
        // printBoard(Arr);
        // System.out.println(b);
        if(b){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    solution[i][j] = Arr[i][j];
                }
            }
            // printBoard(solution);
            creatingVoids(voids);
            //creating voids to mske it solvable
            game(voids);
        }
        else{
            System.out.println("SOME UNWANTED ERROR OCCURED!");
        }
    }

    void creatingVoids(int voids){
        
        int n = voids;
        while(n >=0){
            
            Random random = new Random();
            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = random.nextInt(9);
            int j = random.nextInt(9);
 
            // System.out.println(i+" "+j);
            if (Arr[i][j] != 0)
            {
                n--;
                Arr[i][j] = 0;
            }
        }
    }


    // ramdomizing a array using fisher yates algo
    static void randomize( int arr[], int n){
        // Creating a object for Random class
        Random r = new Random();
         
        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = n-1; i > 0; i--) {
             
            // Pick a random index from 0 to i
            int j = r.nextInt(i+1);
             
            // Swap arr[i] with the element at random index
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    void game(int voids){
        int i=0, j=0, val=0;

        do{
            printBoard(Arr);
            
            //getting coordinates of the inserted element
            System.out.print("\n\n\t(Enter -1 TO END)\n\tENTER THE COORDINATES OF THE POSITION TO INSERT ELEMENT AT : ");
            i = in.nextInt() - 1;
            j = in.nextInt() - 1;

            //checking if the given position is editable
            if(Arr[i][j] == 0){
                System.out.println("\n\tENTER THE ELEMENT : ");
                val = in.nextInt();
                boolean b = isValid(val, i, j);

                if(b){
                    Arr[i][j] = val;
                    voids--;
                }
                else{
                    System.out.println("\n\t\tOHH! INCORRECT SUBSTITUTE \n\t\t YOU LOST!! BETTER LUCK NEXT TIME");
                    printBoard(solution);
                    break;
                }
            }
        }while((i!=-1 || j!=-1 || val!=-1) && (voids > 0));

        if(voids == 0){
            System.out.println("\n\t\tWOHOOO!! GREAT JOB!\n\t\t YOU WON");
        }

    }
}
