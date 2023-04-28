/* 
 author @D 
*/
import java.util.Random;
import java.util.Scanner;

public class MagicNumber {
//Creating an array  for the user to give me at max 5 guesses.
    int guess[] = new int[5];
/*Method if only one person is guessing the MagicNumber
/Attempts to check for each int in the array and see if its equal to our magic
/number.
*/
    boolean guessed(MagicNumber num, int magic) {
        for (int element : guess) {
            if (element == magic) {
                break;
            } else {
                return false;
            }
        }
        return true;
    }
/* Method overloading, if two people are guessing to see who has the higher 
    Extrasensory Perception(ESP).
    If one user guesses the correct answer before the other one then we let them
    know they have the higher ESP. However if they guess at the same time 
    (position of array) then we say they are equal in their ESP value.
    If neither guesses then we return false.
    */
    public static class ESP extends MagicNumber {
        boolean guessed(ESP p1, ESP p2, int magic) {
            for (int i = 0; i < guess.length; i++) {
                if (p1.guess[i] == magic && p1.guess[i] != p2.guess[i]) {
                    System.out.println("Player 1 has higher ESP.");
                    System.out.println(p1.guess[i] + " is the correct value at "
                            + "position " + (i + 1));
                    return true;
                } else if (p2.guess[i] == magic && p1.guess[i] != p2.guess[i]) {
                    System.out.println("Player 2 has higher ESP.");
                    System.out.println(p2.guess[i] + " is the correct value at "
                            + "position " + (i + 1));
                    return true;
                } else if (p2.guess[i] == p1.guess[i] && p1.guess[i] == magic) {
                    System.out.println("It would seem both of you have HIGH ESP"
                            + " because you guessed it at the same time. "
                            + "You didn't cheat did you?");
                    System.out.println(p2.guess[i] + " is the correct value at "
                            + "position " + (i + 1));
                    return true;
                }
            }
            return false;
        }
    }
/* Create a random number in the main. I originally wanted to do this in the 
    class however when doing so it would make a new random number for each
    ESP user since I am making a NEW ESP each time. 
    Set a counter for each while statement (i try and use while instead of for 
    loops every time) that will take the users guesses. After doing so it will 
    call the method to test for what was guessed and let the user know the 
    outcome. If the method returns false then it will tell them that neither of 
    them have high enough ESP to find the correct answer. 
    */
    public static void main(String[] args) {
        Random random = new Random();
        int rand = random.nextInt(100 - 1) + 1;
        final int magic = rand;
        /*prints out the random number so i can verify code is working.
        / comment out if not needed any longer and just want to run program 
        / without revealing the answer.
        */
        System.out.println(magic);
        ESP p1 = new ESP();
        ESP p2 = new ESP();
        int count = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please guess five numbers between 1-100 Player 1: ");
        System.out.println("Player 1: ");
        while (count < 5) {
            int guess = sc.nextInt();
            p1.guess[count] = guess;
            count++;
        }
        count = 0;
        System.out.println("Player 2: ");
        while (count < 5) {
            int guess = sc.nextInt();
            p2.guess[count] = guess;
            count++;
        }
        if (p2.guessed(p1, p2, magic) == false) {
            System.out.println("Sorry neither of you have any ESP.");
        }
    }
}
