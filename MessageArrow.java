//************************************************************************
// COSC 1336 CS 1
// Name: Lizbeth Trujillo
// Date: 09/19/21
// This program will print out an arrow with any message the user wants.
//************************************************************************
public class MessageArrow {
    public static void main (String[] args){

        // calls method printArrow
        printArrow("Time Flies Like an Arrow");


    }

    // this method can be called to print an arrow with the users desired string.
    public static void printArrow (String message){

        for(int i = 1; i< 8; i++ ) {
            if (i == 4) {
                System.out.println("************** " + message);
            } else if (i == 1 || i == 7) {
                System.out.println("       *");
            } else if (i == 2 || i == 6) {
                System.out.println("          *");
            } else {
                System.out.println("            *");
            }
        }

        }
}
