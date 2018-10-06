// **********************************************************
// Assignment0:
// UTORID: gadriwa1
// UT Student #: 1004351051
// Author: gadriwa1
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
package a0;
import java.lang.Math;
import java.util.Arrays;

/**
 * Cfiltering is a class which contains two matrices:
 * userMovieMatrix stores movie ratings per user
 * userUserMatrix stores similarity scores between two users
 */
public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor of the class Cfiltering
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }

  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    userMovieMatrix = new int [numberOfUsers][numberOfMovies];
    userUserMatrix = new float [numberOfUsers][numberOfUsers];
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {
    // row number is the user and the col number is the movie
    // since that's how we've initialized our matrices
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /**
   * Calculates the distance score between two user's movie ratings
   * @param user1 Reference to first user's ratings for movies
   * @param user2 Reference to second user's ratings for movies
   * @return the distance between two user's movie ratings
   */
  public float distanceCalculator(int user1, int user2) {
    // note: user1 and user2 are row values
    // initialize the total for movie ratings
    double totalMovieRatings = 0;
    // get all the movie ratings for user1 or user2
    int[] movieRating = userMovieMatrix[user1 - 1];
    // first add all movie ratings by traversing through the row
    for (int index = 0; index < movieRating.length; index++) {
      totalMovieRatings += Math.pow((userMovieMatrix[user2 - 1][index] -
          userMovieMatrix[user1 - 1][index]), 2);
    }
    // then take the sqrt of it and return the value
    float distanceCalculated = (float) Math.sqrt(totalMovieRatings);
    // returning the value
    return distanceCalculated;
  }

  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param user1 Reference to first user's ratings for movies 
   * @param user2 Reference to second user's ratings for movies
   * @return similarityScore which is between 0 and 1
   */
  public float calculateSimilarityScore(int user1, int user2) {
    // user1 and user2 are row values. To calculate similarity scores,
    // we need to first calculate distance between the movie points
    // our helper does that for us
    float distanceCalculated = distanceCalculator(user1,user2);
    // and then the similarity score is 1/(1 + distance(user1, user2))
    // formula given in the handout
    float similarityScore = 1/(1+distanceCalculated);
    return similarityScore;
  }

  /**
   * Prints out the movie ratings of the userMovieMatrix with each row being
   * the user and columns being distinct movies and the cell position (i, j)
   * represnting the user rating of the movie j of user i
   */
  private void printUserMovieMatrix() {
    // helper method to see if userMovieMatrix is set up accurately
    // get the number of users = rows
    int numUsers = userMovieMatrix.length;
    for (int rows = 0; rows < numUsers; rows++) {
      // print each row out till you hit the end of the rows
      System.out.println(Arrays.toString(userMovieMatrix[rows]));
    }
  }

  /**
   * Prints the row at the given index
   * @param index The number of row
   * @return a string with all the va
   */
  private String printUserUserMatrixRows(int row) {
   String column = "[";
   // get the row from the user user matrix
   float[] col = userUserMatrix[row];
   // traverse through the entire column and convert each number into a 4
   // digit string
   for (int colNum = 0; colNum < col.length; colNum++) {
     column += String.format("%.4f", col[colNum]) + ", ";
   }
   // remove the extra comma + space and return the entire column with a ] at
   // the end
   String finalCol = column.substring(0, column.length()-2);
   return finalCol + "]";
  }

  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   */
  public void printUserUserMatrix() {
    // the length of the matrix is the # of users we have
    int totalUsers = userMovieMatrix.length;
    // make a for loop that goes through each cell of the userUser Matrix
    // and fills it with the similarity score
    // need two for loops: one for rows and one for columns
    for (int row = 0; row < totalUsers; row++) {
      for (int col = 0; col < totalUsers; col++) {
        // store the score at the row and col in the userUserMatrix
        userUserMatrix[row][col] = calculateSimilarityScore(row + 1,col + 1);
      }
    }
    // We have constructed our matrix WHOOP!
    // once we have done that, we can print the userUserMatrix as wanted
    // use a for loop to get each column and print that out as our result
    for (int row = 0; row < totalUsers; row++) {
      // calling our helper function here
      System.out.println(printUserUserMatrixRows(row));
      }
    }
    // DOPE!


  /**
   * 
   * @param similarTo The value you want your users similar by
   * @return a string of users similar to similarTo
   */
  private String findSimilarPairs(float similarTo) {
    // you only wanna consider the values in the upper triangular of the
    // matrix; you consider the entire matrix, you start looking at values
    // that either repeat or you don't care about
    // say you're at row 0, then your column should start from row +1
    // we want the max value to be 0 and we increase it as 
    // initializing an empty string and the value we wanna be approaching
    String similarPairs = "";
    float currentSimilar = similarTo;
    // using the logic explained above, we set up our loops
    for (int row = 0; row < userUserMatrix.length; row++) {
      for (int col = row +1; col < userUserMatrix.length; col++) {
        // case for similar users
        if (similarTo == 0) {
          // if the similarity score is more than our curr similar
          // meaning we have some users more similar than the one we had before
          if (userUserMatrix[row][col] > currentSimilar) {
            // clear the string out and store that as your similarity score
            similarPairs = "";
            currentSimilar = userUserMatrix[row][col];
            // set the string up for the new users
            similarPairs = "User" + row + " and User" + col;
          } else if (userUserMatrix[row][col] == currentSimilar) {
            // if its the same then add them to our current list of users
            similarPairs += "," + "\n" + "User" + row + " and User" + col;
          }
        }
        // case for dissimilar users
        else if (similarTo == 1) {
          // similar to before the only difference being that the we're
          // looking for a lower score now so the lower the score, the more
          // dissimilar they are
          if (userUserMatrix[row][col] < currentSimilar) {
            similarPairs = "";
            currentSimilar = userUserMatrix[row][col];
            similarPairs = "User" + row + " and User" + col;
          } else if (userUserMatrix[row][col] == currentSimilar) {
            similarPairs += "," + "\n" + "User" + row + " and User" + col;
          }
        }
      }
    }
    // return the string with the extra topping on top aka the similarity score
    // that they're paired by
    return similarPairs + "\n" + "with similarity score of " + 
    String.format("%.4f", currentSimilar);
  }
  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   */
  public void findAndprintMostSimilarPairOfUsers() {
    // we wanna have our pairs close to one, so we want to set our value
    // to 0 and increase from there
    float similarTo = 0;
    // call the helper method using the similar to and store the value in
    // a string
    String similarPairs = findSimilarPairs(similarTo); 
    // print the result
    System.out.println(similarPairs);
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   */
  public void findAndprintMostDissimilarPairOfUsers() {
    // we wanna have our pairs close to one, so we want to set our value
    // to 1 and decrease from there
    float similarTo = 1;
    // call the helper method using the similar to and store the value in
    // a string
    String similarPairs = findSimilarPairs(similarTo); 
    // print the result
    System.out.println(similarPairs);
  }
}
