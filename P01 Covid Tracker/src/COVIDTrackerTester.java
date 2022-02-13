//////////////// COVIDTrackerTester (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PO1 CovidTracker
// Course:   CS 300 Fall 2020
//
// Author:   Rohan Kale
// Email:    rkale2@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:        None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

public class COVIDTrackerTester {
  public static void main(String[] args) {

    System.out.println(testAddTest());
    System.out.println(testRemoveIndividual());
    System.out.println(testGetIndividualStats());
    System.out.println(testgetPopStats());


  }

  /**
   * Checks whether addTest() works as expected
   *
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testAddTest() {
    boolean testWorks = false;
    String[] neg = {"AB999", "PS88", "CV78", "RK987", "OI999", "YT71", "GG66", "DH666", null, null, null, null, null};
    String[] pos = {"AB999", "LN987", "CZ78", "EQ888", null, null, null, null, null};

    COVIDTracker c = new COVIDTracker();


//test to see if adds normally
    boolean added = c.addTest(pos, neg, "QK231", true);
    if (added && pos[4].equals("QK231")) {
      testWorks = true;
    } else {
      return false;
    }

    //test to see if first arr index is null if still can add
    pos[0] = null;

    added = c.addTest(pos, neg, "ZB953", true);
    if (added && pos[0].equals("ZB953")) {
      testWorks = true;
    } else {
      return false;
    }

    //test to see if all arr indices are null if can add
    neg[0] = null;
    neg[1] = null;
    neg[2] = null;
    neg[3] = null;
    neg[4] = null;
    neg[5] = null;
    neg[6] = null;
    neg[7] = null;

    added = c.addTest(pos, neg, "RI765", false);
    if (added && neg[0].equals("RI765")) {
      testWorks = true;
    } else {
      return false;
    }
    //test to see if arr has no values returns false
    String[] newPos = {};

    added = c.addTest(newPos, neg, "RI765", true);

    if (!added) {
      testWorks = true;
    } else {
      return false;
    }
    //test to see if can handle diff initialization
    String[] newNeg = new String[5];

    added = c.addTest(pos, newNeg, "RI765", false);

    if (added && newNeg[0].equals("RI765")) {
      testWorks = true;
    } else {
      return false;
    }

    //test to see if can handle full array
    String[] newPos2 = {"PP987"};

    added = c.addTest(newPos2, neg, "RI765", true);

    if (!added) {
      testWorks = true;
    } else {
      return false;
    }
    //test to see if can handle full array
    String[] newNeg2 = {"YY654"};

    added = c.addTest(pos, newNeg2, "RI765", false);

    if (!added) {
      testWorks = true;
    } else {
      return false;
    }


    return testWorks;
  }

  /**
   * Checks whether removeIndividual() works as expected
   *
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testRemoveIndividual() {
    boolean hasPassed = false;
    String[] neg = {"AB999", "PS88", "CV78", "RK987", "OI999", "YT71", "GG66", "DH666", null, null, null, null, null};
    String[] pos = {"AB999", "LN987", "CZ78", "EQ888", null, null, null, null, null};

    COVIDTracker c = new COVIDTracker();

    boolean removed = c.removeIndividual(pos, neg, "AB999");

    //can remove an individual from both arrays
    if (removed && neg[7] == null && pos[3] == null) {
      hasPassed = true;
    } else {
      return false;
    }
    //can remove individual when present twice in an array
    neg[5] = "AB999";

    removed = c.removeIndividual(pos, neg, "AB999");

    if (removed && neg[7] == null && pos[3] == null && neg[6] == null) {
      hasPassed = true;
    } else {
      return false;
    }

    //won't remove anything when the ID does not match
    removed = c.removeIndividual(pos, neg, "999");

    if (!removed) {
      hasPassed = true;
    } else {
      return false;
    }

    //will return false because there is nothing to remove
    String[] newPos = {};
    removed = c.removeIndividual(newPos, neg, "AB999");

    if (!removed) {
      hasPassed = true;
    } else {
      return false;
    }


    return hasPassed;
  }

  /**
   * Checks whether getIndividualStats() works as expected
   *
   * @return true if method functionality is verified, false otherwise
   */
  public static boolean testGetIndividualStats() {
    boolean passed = false;

    String[] neg = {"AB999", "PS88", "CV78", "RK987", "AB999", "YT71", "GG66", "DH666", null, null, null, null, null};
    String[] pos = {"AB999", "LN987", "CZ78", "EQ888", null, null, null, null, null};

    COVIDTracker c = new COVIDTracker();

    //test if works with normal array
    String stats = c.getIndividualStats(pos, neg, "AB999");
    if (stats.equals("Total tests: " + 3 + "\n" + "Positive: " + 1 + "\n" + "Negative: " + 2)) {
      passed = true;
    } else {
      return false;
    }

    //test if works when no instances found
    stats = c.getIndividualStats(pos, neg, "ZZ000");
    if (stats.equals("Total tests: " + 0 + "\n" + "Positive: " + 0 + "\n" + "Negative: " + 0)) {
      passed = true;
    } else {
      return false;
    }

    //test if works when array is not initialized

    String[] newPos = {};
    String[] newNeg = {};
    stats = c.getIndividualStats(newPos, newNeg, "AB999");
    if (stats.equals("Total tests: " + 0 + "\n" + "Positive: " + 0 + "\n" + "Negative: " + 0)) {
      passed = true;
    } else {
      return false;
    }
    //test to see if all is null and one is not present still works
    String[] newPos2 = {null, null, null, null};
    String[] newNeg2 = {};
    stats = c.getIndividualStats(newPos2, newNeg2, "AB999");
    if (stats.equals("Total tests: " + 0 + "\n" + "Positive: " + 0 + "\n" + "Negative: " + 0)) {
      passed = true;
    } else {
      return false;
    }


    return passed;
  }

  /**
   * Checks whether getPopStats() works as expected
   *
   * @return true if method functionality is verified, false otherwise
   */

  public static boolean testgetPopStats() {
    boolean passed = false;

    String[] neg = {"AB999", "PS88", "CV78", "RK987", "AB999", "YT71", "GG66", "DH666", null, null, null, null, null};
    String[] pos = {"AB999", "LN987", "CZ78", "EQ888", null, null, null, null, null};

    COVIDTracker c = new COVIDTracker();

    String stats = c.getPopStats(pos, neg);


    if (stats.equals("Total tests: " + 12 + "\n" + "Total individuals tested: " + 10
                             + "\n" + "Percent positive tests: " + 33.33333333333333 + "%\n" + "Percent positive individuals: " +
                             40.0 + "%")) {
      passed = true;
    } else {
      return false;
    }

    //tests if works when arrays are negative
    String[] newPos = {};
    String[] newNeg = {};
    stats = c.getPopStats(newPos, newNeg);
    if (stats.equals("Total tests: " + 0 + "\n" + "Total individuals tested: " + 0
                             + "\n" + "Percent positive tests: " + 0.0 + "%\n" + "Percent positive individuals: " +
                             0.0 + "%")) {
      passed = true;
    } else {
      return false;
    }

    //tests when equal size with all unique

    String [] newPos2 = {"AB300","RD200","RT100"};
    String [] newNeg2 = {"AB500","RD600","RT700"};
    stats = c.getPopStats(newPos2, newNeg2);




    if (stats.equals("Total tests: " + 6 + "\n" + "Total individuals tested: " + 6
                             + "\n" + "Percent positive tests: " + 50.0 + "%\n" + "Percent positive individuals: " +
                             50.0 + "%")) {
      passed = true;
    } else {
      return false;
    }

    return passed;
  }


}