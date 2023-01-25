import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Cache {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Name: Lizbeth Trujillo" + "\n");
        System.out.print("Enter the number of cache blocks: ");

        int numCacheBlocks = scanner.nextInt();

        //calculate bit offset
        int offset = (int) (Math.log(numCacheBlocks) / Math.log(2));

        System.out.println("Select Replacement Policy: ");
        System.out.println("1: LRU ( Least Recently Used ) ");
        int replacementPolicy = scanner.nextInt();

        System.out.print("Enter a sequence of addresses to use in the cache: ");

        int[] sequence = new int[numCacheBlocks];

        for (int i = 0; i < numCacheBlocks; i++) {
            sequence[i] = scanner.nextInt();
        }

        System.out.println("Choose cache Associativity: ");
        System.out.println("1: Direct-Mapped  2: Set Associative 3: Fully Associative");
        int associativity = scanner.nextInt();
        int sizeOfAssociativity;

        switch (associativity){
            case 1:
                //calls directMapped function
                System.out.println("Direct Mapped");
                directMapped(offset, sequence);
                break;
            case 2:
                //calls setAssocitive function
                System.out.println("Choose associativity size: ");
                System.out.println("2: 2-way 4: 4-way");
                sizeOfAssociativity = scanner.nextInt();
                if(sizeOfAssociativity == 2){
                    System.out.println("2-Way Set Associative");
                    setAssociative(2, sequence);

                }else{
                    System.out.println("4-Way Set Associative");
                    setAssociative(4, sequence);

                }
                break;
            case 3:
                //calls fully associative function
                System.out.println("Fully Associative");
                fullyAssociative(offset, sequence);
                break;
            default:
                System.out.println("Not a Valid Input");
                break;
        }
    }

    public static void directMapped(int offset, int[] sequence) {
        HashMap<String, String> cacheContent = new HashMap<>();
        int hit = 0; //Track hits
        int miss = 0;//Track misses

        //to store offset
        String oS = "";

        //iterates through input sequence
        for (int number : sequence) {
            if (number == 0) {
                oS = "00";
            } else {

                //collects offset bits
                String binaryNum = Integer.toBinaryString(number);
                for (int i = offset; i > 0; i--) {
                    oS += binaryNum.charAt(binaryNum.length() - i);
                }
            }
            //if the hashmap has an entry matching our current query it is a hit else it is a miss
            if (cacheContent.containsValue(" MEM[" + number + "] ")) {
                hit++;
            } else {
                miss++;
            }

            //put current number query into the hashmap
            // will replace old value with new one
            cacheContent.put(oS, " MEM[" + number + "] ");

            //print out current cache block content
            System.out.println(cacheContent.values());

            //reset offset tracker
            oS = "";
        }

        //prints hit/miss results
        System.out.println();
        System.out.println("Hit/Miss ratio: " + hit + "/" + miss);

    }


    public static void setAssociative(int associativity, int[] sequence) {
        int hit = 0;  //tracks hit
        int miss = 0; //tracks miss


        if (associativity == 2) {

            //stack keeps track of LRU for set 0
            Stack<Integer> Set0 = new Stack<>();

            //stack keeps track of LRU for set 1
            Stack<Integer> Set1 = new Stack<>();

            //will keep track of set0 sub list
            ArrayList<String> block0 = new ArrayList<>();
            //will keep track of set1 sub list
            ArrayList<String> block1 = new ArrayList<>();

            //iterate through input sequence
            for (int number : sequence) {

                //binaryString of number Query
                String binaryNum = "0000" + Integer.toBinaryString(number);

                //get single digit offset
                String offSet = binaryNum.substring(binaryNum.length() - 1);

                switch (offSet) {
                    case "0":
                        //if number is in LRU stack remove it since it is no longer the LRU
                        if (Set0.contains(number)) {
                            Set0.remove(Set0.indexOf(number));
                        }
                        //push number to the LRU tracking stack to add it to the bottom of the LRU "queue"
                        Set0.push(number);
                        //if the 0 set list does not have this element and the list is at its limited size
                        //run replacement logic
                        if ((!block0.contains(" MEM[" + number + "] ")) && block0.size() == 2) {
                            //retrieve LRU element frm stack
                            int num = Set0.remove(0);

                            //find LRU in list and replace it with current number query
                            block0.set(block0.indexOf(" MEM[" + num + "] "), " MEM[" + number + "] ");

                            //add miss
                            miss++;
                        }else if (!block0.contains(" MEM[" + number + "] ")) {
                            //if number is not in the list and it is not full add it
                            block0.add(" MEM[" + number + "] ");

                            //add miss
                            miss++;
                        }else{
                            //is a hit
                            hit++;
                        }
                        break;

                    case "1":
                        //if number is in LRU stack remove it since it is no longer the LRU
                        if (Set1.contains(number)) {
                            Set1.remove(Set1.indexOf(number));
                        }
                        //if set 1 is empty then go into replace function
                        if ((!block1.contains(" MEM[" + number + "] ")) && block1.size() == 2) {
                            //retrieve LRU element frm stack
                            int num = Set1.remove(0);
                            //find LRU in list and replace it with current number query
                            block1.set(block1.indexOf(" MEM[" + num + "] "), " MEM[" + number + "] ");
                            miss++;
                        }
                        if (!block1.contains(" MEM[" + number + "] ")) {
                            block1.add(" MEM[" + number + "] ");
                            miss++;
                        }
                        break;

                    default:
                        System.out.println("Is not binary number. Check for error");
                        break;
                }
                //append 0 list and 1 list into temp list to print current cache contents.
                ArrayList<String> temp = new ArrayList<>();
                temp.addAll(block0);
                temp.addAll(block1);
                System.out.println(temp);
            }

        } else {

            Stack<Integer> Set00 = new Stack<>();
            Stack<Integer> Set01 = new Stack<>();
            Stack<Integer> Set10 = new Stack<>();
            Stack<Integer> Set11 = new Stack<>();

            ArrayList<String> block00 = new ArrayList<>();
            ArrayList<String> block01 = new ArrayList<>();
            ArrayList<String> block10 = new ArrayList<>();
            ArrayList<String> block11 = new ArrayList<>();

            for (int number : sequence) {

                //binaryString of number Query
                String binaryNum = "0000" + Integer.toBinaryString(number);
                String offSet = binaryNum.substring(binaryNum.length() - 2);
                switch (offSet) {
                    case "00":
                        if (Set00.contains(number)) {
                            Set00.remove(Set00.indexOf(number));
                        }
                        Set00.push(number);

                        if ((!block00.contains(" MEM[" + number + "] ")) && block00.size() == 4) {
                            int num = Set00.remove(0);
                            block00.set(block00.indexOf(" MEM[" + num + "] "), " MEM[" + number + "] ");
                            miss++;
                        }else if (!block00.contains(" MEM[" + number + "] ")) {
                            block00.add(" MEM[" + number + "] ");
                            miss++;
                        }else {
                            hit++;
                        }

                        break;

                    case "01":
                        if (Set01.contains(number)) {
                            Set01.remove(Set01.indexOf(number));
                        }
                        //if set 1 is empty then go into replace function
                        if ((!block01.contains(" MEM[" + number + "] ")) && block01.size() == 4) {
                            int num = Set01.remove(0);
                            block01.set(block01.indexOf(" MEM[" + num + "] "), " MEM[" + number + "] ");
                            miss++;
                        } else if (!block01.contains(" MEM[" + number + "] ")) {
                            block01.add(" MEM[" + number + "] ");
                            miss++;
                        }else {
                            hit++;
                        }

                        break;


                    case "10":

                        if (Set10.contains(number)) {
                            Set10.remove(Set10.indexOf(number));
                        }
                        //if set 1 is empty then go into replace function
                        if ((!block10.contains(" MEM[" + number + "] ")) && block10.size() == 4) {
                            int num = Set10.remove(0);
                            block10.set(block10.indexOf(" MEM[" + num + "] "), " MEM[" + number + "] ");
                            miss++;
                        }else if (!block10.contains(" MEM[" + number + "] ")) {
                            block10.add(" MEM[" + number + "] ");
                            miss++;
                        } else {
                            hit++;
                        }
                        break;
                    case "11":

                        if (Set11.contains(number)) {
                            Set11.remove(Set11.indexOf(number));
                        }
                        //if set 1 is empty then go into replace function
                        if ((!block11.contains(" MEM[" + number + "] ")) && block11.size() == 4) {
                            int num = Set11.remove(0);
                            block11.set(block11.indexOf(" MEM[" + num + "] "), " MEM[" + number + "] ");
                            miss++;
                        }else if (!block11.contains(" MEM[" + number + "] ")) {
                            block11.add(" MEM[" + number + "] ");
                            miss++;
                        }else {
                            hit++;
                        }
                        break;

                    default:
                        System.out.println("Is not binary number. Check for error");
                        break;
                }

                ArrayList<String> temp = new ArrayList<>();
                temp.addAll(block00);
                temp.addAll(block01);
                temp.addAll(block10);
                temp.addAll(block11);

                System.out.println(temp);
            }
        }

        System.out.println();
        //store in array list and pop off last element.
        System.out.println("Hit/Miss Ratio: " + hit + "/" + miss);
    }


    public static void fullyAssociative(int offset, int[] sequence) {
        int hit = 0;
        int miss = 0;
        Stack<Integer> queue = new Stack<>();

        ArrayList<String> cacheContent = new ArrayList<>();

        for (int number : sequence) {
            //binaryString of number Query
            String binaryNum = "0000" + Integer.toBinaryString(number);
            String offSet = binaryNum.substring(binaryNum.length() - offset);

            if (queue.contains(number)) {
                queue.remove(queue.indexOf(number));
            }
            queue.push(number);

            if ((!cacheContent.contains(" MEM[" + number + "] ")) && cacheContent.size() == Math.pow(offset, 2)) {
                int num = queue.remove(0);
                cacheContent.set(cacheContent.indexOf(" MEM[" + num + "] "), " MEM[" + number + "] ");
                miss++;
            }else if (!cacheContent.contains(" MEM[" + number + "] ")) {
                cacheContent.add(" MEM[" + number + "] ");
                miss++;
            }else {
                hit++;
            }

            System.out.println(cacheContent);

        }
        System.out.println("Hit/Miss ratio: " + hit + "/" + miss);
    }

}
