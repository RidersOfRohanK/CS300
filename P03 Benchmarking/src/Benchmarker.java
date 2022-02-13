//////////////// BENCHMARKER.JAVA //////////////////////////
//
// Title:    Benchmarker.java checks speeds of certain methods
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

import java.io.*;
import java.util.NoSuchElementException;


public class Benchmarker {
  public static void main(String[] args) {
    File f = new File("benchmark.txt");

    long[] Ns = new long[10];

    //fills the entire array with powers of 1000, increasing
    for (int i = 0; i < Ns.length; i++) {
      Ns[i] = (long)Math.pow(1000,i+1);
    }

    createResultsFile(f, Ns);
  }

  /**
   *
   * @param n - number being passed into the 2 tests
   * @return - a string to be inputted into the file
   * @throws NoSuchElementException
   */
  public static String compare(long n) throws NoSuchElementException {
    //takes current time and subtracts by the end time to find how long method takes
    long time1 = System.currentTimeMillis();
    long bruteForceResult = ComparisonMethods.bruteForce(n);
    long time2 = System.currentTimeMillis();

    //takes current time and subtracts by the end time to find how long method takes
    long time3 = System.currentTimeMillis();
    long constantTimeResult = ComparisonMethods.constantTime(n);
    long time4 = System.currentTimeMillis();

    long bruteForceTime = time2 - time1;
    long formulaTime = time4 - time3;

    //Checks that both elements are equal
    if (bruteForceResult != constantTimeResult) {
      throw new NoSuchElementException("The times did not match, brute force resulted in "
                                               + bruteForceTime + " while the constant time resulted in " + constantTimeResult);
    }
    String result = n + "\t" + bruteForceTime + "\t" + formulaTime + "\n";
    return result;
  }

  /**
   *
   * @param f - file for things to be written on
   * @param queryNs - Arr of numbers to be checked
   */
  public static void createResultsFile(java.io.File f, long[] queryNs) {
    FileWriter writer = null;
    try {
      writer = new FileWriter(f);
    } catch (IOException e) {
      System.out.println("​Exception encountered, unable to complete method.");// if file writer is unable to be made
    }
    for (int i = 0; i < queryNs.length; i++) {
      String s = null;
      try {
        s = compare(queryNs[i]);
      } catch (NoSuchElementException e) {//if the results are not the same across the 2 tests then this is triggered
        System.out.println(e.getMessage());
      }
      try {
        writer.write(s);//adds the string to the file
      } catch (IOException e) {
        System.out.println("Exception encountered while writing for value N = " + queryNs[i]);
      }
    }

    try {
      writer.close();//if writer fails to close then this catches it
    } catch (IOException e) {
      System.out.println("Exception encountered while closing file.");
    }
  }
}
