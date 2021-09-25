import java.util.Scanner;

public class SubtractionTest {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int grade = 0;
        for (int i = 0; i < 5; i++){
            int number1 = (int) (Math.random()*100);
            int number2 = (int) (Math.random()*100);
            int answer = number1 -number2;
            System.out.println("What is " + number1 + " - " + number2 + " equal to?");
            System.out.println("Type your answer Below");
            int response =  scanner.nextInt();
                if (response == answer){
                    grade++;
                    System.out.println("True");
            }else{
                    System.out.println("False");
                }
        }
        System.out.println("Your grade is " + grade + " out of 5");

        scanner.close();
    }
}
 /* Exercise 2. (Problem: An Advanced Math Learning Tool) The Math subtraction learning tool program
        generates just one question for each run. To create one question, the program randomly generates two
        integers between 0 and 99, say, number1 and number2, and displays a question such as “What is number1
        + number2?” to the student. After the student types the answer, the program displays a message to
        indicate whether the answer is true or false. You can use a loop to generate questions repeatedly. Write a
        Java program that generates five questions and reports the number of the correct answers after a student
        answers all five questions

  */