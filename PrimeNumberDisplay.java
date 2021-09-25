public class PrimeNumberDisplay {
    public static void main (String[] args){

        StringBuilder printPrimes = new StringBuilder();

        System.out.println("\n The following are the first 50 prime numbers:");

        int primeNumCounter = 1;

        for (int i = 0; i < 1000; i ++ ){

            if (primeNumCounter == 51){
                break;
            }
            if (isPrimeNumber(i)){
                    printPrimes.append(" ");
                    printPrimes.append(i);

                    if (i < 100){
                        printPrimes.append(" ");

                        if(i < 10){
                            printPrimes.append(" ");
                        }
                    }
                    printPrimes.append(" ");

                    if(primeNumCounter % 10 == 0 &&  primeNumCounter != 0){
                        printPrimes.append("\n");
                    }
                    primeNumCounter++;
            }
        }

        String results = printPrimes.toString();
        System.out.println(results);
    }

    public static boolean isPrimeNumber(int number){
        int temp = 0;
        for (int i = 1; i < number+1; i++) {
            if ((number % i == 0)) {
                temp++;
            }
        }
            return temp == 2;
    }

}
