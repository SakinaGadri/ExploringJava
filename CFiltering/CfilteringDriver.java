// **********************************************************
// Assignment0:
// UTORID: gadriwa1
// UT Student #: 1004351051
// Author: Sakina Gadriwala
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check. 
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

/**
 * This class executes Cfiltering
 */
public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores
   * and prints a score matrix.
   */
  public static void main(String[] args) {
    try {
      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());
      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // this is a blank line being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      // introducing the row counter
      int rowNum = 0;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        // make a column counter
        int colNum = 0;
        for (String singleRating : allRatings) {
          // make the String number into an integer
          // populate userMovieMatrix
          // call populate matrix function on the CFiltering obj
          cfObject.populateUserMovieMatrix(rowNum, colNum,
              Integer.parseInt(singleRating));
          // increase the column counter
          colNum++;
          }
        // increase the row counter
        rowNum++;
      }
      // close the file
      fStream.close();
      // debugging for distance NaN problem
      // CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
      // starting from top and going downwards, so that i don't miss a user
      for (int user = numberOfUsers; user < 1; user--) {
        cfObject.calculateSimilarityScore(user, user - 1);
      }
      // adding a break line
      System.out.println("\n" + "\n" + "userUserMatrix is:");
      // PRINT OUT THE userUserMatrix
      cfObject.printUserUserMatrix();
      // Printing similar pairs
      System.out.println("\n" + "\n" + "The most similar pairs of users"
          + "from above userUserMatrix are:");
      cfObject.findAndprintMostSimilarPairOfUsers();
      // printing the disimilar pairs
      System.out.println("\n" + "\n" + "The most dissimilar pairs of users"
          + "from above userUserMatrix are:");
      cfObject.findAndprintMostDissimilarPairOfUsers();
    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
