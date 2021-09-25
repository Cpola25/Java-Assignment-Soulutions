//************************************************************
// COSC 1336 CS 1
// Name: Lizbeth Trujillo
// Date: 09/19/21
// This program will return the current time in GMT format.
//************************************************************
public class GMT {

    public static void main (String[] args){

        // converts milliseconds into seconds and subtracts time difference using GetTimeZoneConversion method.
        long gmtSeconds =  (System.currentTimeMillis() / 1000) - GetTimeZoneConversion();
        // isolates the amount of seconds that have happened in the present day.
        long secondsElapsedToday = (gmtSeconds % 86400);
        // saves the number of minutes into minutesElapsedToday.
        int minutesElapsedToday = (int)secondsElapsedToday / 60;
        // saves the number of hours into hours.
        int hours = minutesElapsedToday / 60;
        // assigns the accurate amount of seconds to the variable secondsElapsedToday.
        secondsElapsedToday %= 60;
        // assigns the accurate amount of minutes to the variable minutesElapsedToday.
        minutesElapsedToday %= 60;
        // will determine if it is the morning or afternoon and will print out the appropriate string.
        String timeOfDay = "AM";

        if (hours > 12){
            hours -= 12;
            timeOfDay = "PM";
        }

        // will print out the current time in GMT format
        System.out.println("The current time is: " + hours + ":"  + minutesElapsedToday + ":" + secondsElapsedToday + " " + timeOfDay); 

    }
    // this method makes it easy to adapt the program for different time zones.
    public static  long GetTimeZoneConversion (){
        int hourDifference = 5;
        return hourDifference * 3600;
    }

}
