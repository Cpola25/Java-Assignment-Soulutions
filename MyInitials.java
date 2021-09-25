
//**********************************************************
// COSC 1336 CS 1
// Name: Lizbeth Trujillo
// Date: 09/19/21
// This program will print out my initials which are L T
//**********************************************************

public class MyInitials {

    public static void main(String[] args) {

        //for loop goes through 10 cycles in order to print initials L and T
        for(int i = 1; i <11; i ++){
            switch(i){
                case 1:
                    System.out.println(" LLLL       TTTTTTTTTTTTTT");
                    break;
                case 2:
                    System.out.println(" LLLL       TTTT TTTT TTTT");
                    break;
                case 3:
                    System.out.println(" LLLL       TT   TTTT   TT");
                    break;
                case 9:
                    System.out.println(" LLLL     LLL    TTTT       ");
                    break;
                case 10:
                    System.out.println(" LLLLLLLLLLLL    TTTT      ");
                    break;
                default:
                    System.out.println(" LLLL            TTTT     ");
            }
        }
    }

}
