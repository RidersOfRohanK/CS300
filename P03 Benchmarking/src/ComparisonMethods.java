//////////////// COMPARISONMETHODS.JAVA //////////////////////////
//
// Title:    ComparisonMethods.java 2 varying approaches to adding all the numbers between 1 and n
// Course:   CS 300 Fall 2020
//
// Author:   Rohan Kale
// Email:    rkale2@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
public class ComparisonMethods {
  /**
   * manually adds each number between 1 and n
   * Complexity: O(N)
   * @param n - number to add up unitl
   * @return - the sum
   */
  public static long bruteForce(long n){
    long total = 0;
    //iterates through every number
    for (int i = 1; i <= n; i++) {
        total+=i;
    }
    return total;
  }

  /**
   * Uses a formula to calculate the sum of all terms between 1 and n
   * Complexity: O(1)
   * @param n - number to add up unitl
   * @return - the sum
   */
  public static long constantTime(long n){
    //equation more or less provided in the instructions
    long product = n*(n+1);
    return product/2;
  }
}
