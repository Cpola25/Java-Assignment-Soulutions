import java.util.Scanner;

public class PalindromeChecker {
    public static void main (String[] args){

        StringBuilder testMessage = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your word or phrase:");
        String message = scanner.nextLine().toLowerCase();

        for (int i = message.length() - 1; i >= 0; i--){
            testMessage.append(message.charAt(i));
        }

        if(testMessage.toString().equals(message)){
            System.out.println("Your input is a palindrome");
        }else{
            System.out.println("Your input is not a palindrome");
        }
    }

}
