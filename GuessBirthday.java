import java.util.Scanner;

public class GuessBirthday {
public static void main (String[] args){

    int birthday = 0;

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter True or False if your Birthdate is in the set \n");

    System.out.println("Is your birthdate in Set 1? ");
    System.out.println("Set 1: { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31 }");
    boolean answer = scanner.nextBoolean();
    if (answer){
        birthday += 1;
    }

    System.out.println("Is your birthdate in Set 2? ");
    System.out.println("Set 2: { 2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23, 26, 27, 30, 31 }");
    answer = scanner.nextBoolean();
    if (answer){
        birthday += 2;
    }

    System.out.println("Is your birthdate in Set 3? ");
    System.out.println("Set 3: { 4, 5, 6, 7, 12, 13, 14, 15, 20, 21, 22, 23, 28, 29, 30, 31 }");
    answer = scanner.nextBoolean();
    if (answer){
        birthday += 4;
    }

    System.out.println("Is your birthdate in Set 4? ");
    System.out.println("Set 4: { 8, 9, 10, 11, 12, 13 ,14, 15, 24, 25, 26, 27, 28, 29, 30, 31 }");
    answer = scanner.nextBoolean();
    if (answer){
        birthday += 8;
    }

    System.out.println("Is your birthdate in Set 5? ");
    System.out.println("Set 5: { 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 }");
    answer = scanner.nextBoolean();
    if (answer){
        birthday += 16;
    }

    System.out.println("My guess is that your birth day is the " + birthday);
// scanner accepts an array
    scanner.close();

}
}
