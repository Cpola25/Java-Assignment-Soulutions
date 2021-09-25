
//*******************************************************************************************************************************
// COSC 1336 CS 1
// Name: Lizbeth Trujillo
// Date: 09/19/21
// This program will print out monthly payment and total payment after user inputs values for years, interest and loan amount.
//*******************************************************************************************************************************
import java.util.Scanner;

public class LoanPayments {
    public static void main (String[] args){

        // creates a scanner named scanner
        Scanner scanner = new Scanner(System.in);
        // prompts user to input loan years to the console
        System.out.println("Enter the number years for your loan: ");
        double years = scanner.nextDouble();
        // prompts user to input yearlyInterest rate to the console
        System.out.println("Enter your yearly interest rate: ");
        double yearlyInterest = scanner.nextDouble();
        //converts yearlyInterest to monthly interest. Turns percentage input to decimal and divides by 12.
        double monthlyInterest = yearlyInterest /1200;
        // prompts user to input loan amount to the console
        System.out.println("Enter your total loan amount: ");
        double loan = scanner.nextDouble();

        //calculates and rounds monthly pay to two decimal places
        double monthlyPay = Math.round ((loan * monthlyInterest / (1 - 1 / Math.pow(1 + monthlyInterest, years * 12))) *100.0) /100.0;
        //calculates and rounds total pay to two decimal places
        double totalPay = Math.round((monthlyPay * years * 12) * 100.0) /100.0;
        // prints monthly pay
        System.out.println("Your Monthly payment is equal to: $ " + monthlyPay);
        // prints total pay
        System.out.println("Your Yearly payment is equal to: $ " + totalPay);

        scanner.close();

    }

}
