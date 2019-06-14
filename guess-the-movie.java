mport java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class GuessTheMovie {
    public static void main(String[] args) throws Exception {

        // opens text file
        File file = new File("movies.txt");

        // reads file content
        Scanner scanner = new Scanner(file);

        // allocates space to store file content
        String[] movies = new String[25];


        // writes file content into array of string
        int index = 0;
        while (scanner.hasNextLine()) {
            movies[index] = scanner.nextLine();
            index += 1;
        }

        // generates a random number
        Random rand = new Random();
        int pickMovie = rand.nextInt(25);

        // indexes random number generated to select a movie and
        // gets length of characters of movie selected to declare an array
        int movieLength = movies[pickMovie].length();
        char movie[] = new char[movieLength];

        //game starts here, prompt
        System.out.print("You are guessing: ");

        // display underscore to hint length of movie title
        // also initializes movie array which updates as game progresses
        for (int i = 0; i < movieLength; i++) {
            if (movies[pickMovie].charAt(i) == ' ') {
                System.out.print(' ');
                movie[i] = ' ';
            } else {
                System.out.print('_');
                movie[i] = '_';
            }
        }
        // prompts
        System.out.print('\n');
        System.out.println("You have guessed (0) wrong letters:");
        System.out.println("Guess a letter: ");

        // read a letter from user
        Scanner guesses = new Scanner(System.in);

        // space to store/keep track of wrongly guessed letters
        StringBuilder wrongLetters = new StringBuilder();

        // counts max number of time you can guess wrongly before game ends
        int chances = 0;

        while (true) {

            // stores users input
            char letterGuessed = guesses.next().charAt(0);

            int correctLetters = 0;

            for (int i = 0; i < movieLength; i++) {

                // checks if user input is correct and updates array
                if (movies[pickMovie].charAt(i) == letterGuessed) {
                    movie[i] = letterGuessed;
                    correctLetters += 1;
                }
            }

            // executes block if user's guess is wrong
            if (correctLetters == 0) {

                // keeps track of wrong guesses
                wrongLetters.append(letterGuessed);
                wrongLetters.append(' ');
                chances = chances + 1;
                System.out.println("You have guessed (" + chances + ") wrong letter(s): " + wrongLetters);

                // Ends game when user has used up all possible chances and hasn't guessed movie right
                if (chances == 10) {
                    System.out.println("You have used up all " + chances + " chances.");
                    System.out.println("The movie is: " + movies[pickMovie]);
                    break;
                }
            }

            // executes block if user's guess is right
            else {

                // Ends game when user guesses movie title correctly
                if (Arrays.equals(movie, movies[pickMovie].toCharArray())) {
                    System.out.println("Yay!! You have guessed " + '"' + movies[pickMovie] + '"' + " correctly");
                    break;
                }
            }

            // Game continues; prompts user for next letter(input) and displays progress
            System.out.print("You are guessing: ");
            for (int k = 0; k < movieLength; k++) {
                System.out.print(movie[k]);
            }
            System.out.print('\n');
            System.out.println("Guess a letter: ");

        }
    }
}
