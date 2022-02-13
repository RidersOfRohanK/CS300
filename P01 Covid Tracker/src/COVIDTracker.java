//////////////// COVIDTracker (INCLUDE IN EVERY FILE) //////////////////////////
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
// Persons:         Ben, Helped me think about a better way to find a unique individual
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

public class COVIDTracker {
  /**
   * Adds ID to proper test array if there is room
   *
   * @param pos   the current array of positive tests
   * @param neg   the current array of negative tests
   * @param id    the tested individuals unique identifier
   * @param isPos true if test was positive, negative otherwise
   * @return true if new record added, false otherwise
   */
  public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos) {

    if (isPos && pos.length > 0) {
      int nullIndex = findFirstNullIndex(pos);
      //if not present exit out
      if (nullIndex == -1) {
        return false;
      }
      pos[nullIndex] = id;
      return true;
    }
    //extra check if length is 0
    else if (isPos && pos.length <= 0) {
      return false;
    } else if (neg.length > 0) {
      int nullIndex = findFirstNullIndex(neg);
      if (nullIndex == -1) {
        return false;
      }
      neg[nullIndex] = id;
      return true;

    }
    //extra check if the array is empty
    else if (!isPos && neg.length <= 0) {
      return false;
    }
    return false;
  }

  /**
   * Finds the first null index
   *
   * @param arr the array that will be searched
   * @return the index  of the first non null index
   */

  private static int findFirstNullIndex(String[] arr) {
    //helper method
    if (arr.length > 0) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == null) {
          return i;
        }
      }
    }

    return -1;
  }

  /**
   * removes all instances of an individual from both positive and negative arrays
   *
   * @param pos the current array of positive tests
   * @param neg the current array of negative tests
   * @param id  the tested individuals unique identifier
   * @return true if the individual was removed, false otherwise
   */
  public static boolean removeIndividual(String[] pos, String[] neg, String id) {
    boolean removedFromPos = removeFromArray(pos, id);
    boolean removedFromNeg = removeFromArray(neg, id);
    //reorders in the method to avoid having problems elsewhere
    //makes sure that everything is just cleaned up immediately
    reorderArray(pos);
    reorderArray(neg);
    if (removedFromPos || removedFromNeg) {
      return true;
    }
    return false;
  }

  /**
   * removes an individual from a given array
   *
   * @param arr array where the individual will be removed from
   * @param id  the unique identifier of the individual being removed
   * @return true if the individual was removed, false otherwise
   */
  private static boolean removeFromArray(String[] arr, String id) {
    boolean removed = false;

    if (arr.length > 0) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] != null && arr[i].equals(id)) {
          arr[i] = null;
          removed = true;


        }
      }
    }


    return removed;
  }

  /**
   * puts null values at the end of the given array
   *
   * @param arr given array to be reordered
   */
  private static void reorderArray(String[] arr) {
    if (arr.length > 0) {
      String[] reordered = new String[arr.length];
      int index = 0;

      //if I just don't assign the variable then the value will automatically default to null, which is what I want
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] != null) {
          reordered[index] = arr[i];
          index++;
        }
      }

      for (int i = 0; i < reordered.length; i++) {
        arr[i] = reordered[i];
      }
    }
  }

  /**
   * returns a String giving the population stats such as number of positive tests, negative tests,
   * proportions of individuals tested to positive cases and number of people tested.
   *
   * @param pos the current array of positive tests
   * @param neg the current array of negative tests
   * @return a String containing the statistics
   */
  public static String getPopStats(String[] pos, String[] neg) {
    int posTests = findTotNumTests(pos);
    int negTests = findTotNumTests(neg);
    int totalTests = posTests + negTests;
    int numIndividuals = findUniqueIDs(pos, neg);
    double proportionPosToNeg = 0;
    //to avoid div by 0 error
    if (negTests > 0) {
      proportionPosToNeg = (posTests / (double) totalTests) * 100;
    }
    double proportionPosToIndv = 0;
    if (numIndividuals > 0) {
      //cast to avoid integer division
      proportionPosToIndv = (posTests / (double) numIndividuals) * 100;
    }
    String popStats = "Total tests: " + totalTests + "\n" + "Total individuals tested: " + numIndividuals
                              + "\n" + "Percent positive tests: " + proportionPosToNeg + "%\n" + "Percent positive individuals: " +
                              proportionPosToIndv + "%";
    return popStats;
  }

  /**
   * Finds the Unique Identifiers in both arrays
   *
   * @param pos the current array of positive tests
   * @param neg the current array of negative tests
   * @return the number of unique identifiers
   */
  private static int findUniqueIDs(String[] pos, String[] neg) {
    //if neither array is initialized no point in merging
    if (pos.length > 0 || neg.length > 0) {
      String[] bigArray = mergeArrays(pos, neg);
      int count = 0;
      while (bigArray[0] != null) {
        //mergeArrays then removed from that array until there is nothing left
        //each time something gets deleted count increases
        removeFromArray(bigArray, bigArray[0]);

        count++;
        reorderArray(bigArray);
      }

      return count;
    }
    return 0;

  }

  /**
   * merges the positive and negative arrays to more easily find pop stats
   *
   * @param pos the current array of positive tests
   * @param neg the current array of negative tests
   * @return a larger combined array
   */
  private static String[] mergeArrays(String[] pos, String[] neg) {
    String[] superArray = new String[pos.length + neg.length];

    for (int i = 0; i < pos.length; i++) {
      superArray[i] = pos[i];
    }

    for (int i = 0; i < neg.length; i++) {
      superArray[i + pos.length] = neg[i];
    }

    reorderArray(superArray);
    return superArray;
  }

  /**
   * @param arr finds the total amount of identifiers in an array
   * @return the number of identifiers in the array
   */
  private static int findTotNumTests(String[] arr) {
    //loops through and just adds each time it finds a non null entity
    int totTests = 0;
    if (arr.length > 0) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] != null) {
          totTests++;
        }
      }
    }
    return totTests;
  }

  /**
   * Finds the total positive and negative cases, and tests administered
   *
   * @param pos the current array of positive tests
   * @param neg the current array of negative tests
   * @param id  the tested individuals unique identifier
   * @return String showing the stats for positive, negative and total tests
   */
  public static String getIndividualStats(String[] pos, String[] neg, String id) {
    int poscount = 0, negcount = 0, totalCount;
    if (pos.length > 0) {
      for (int i = 0; i < pos.length; i++) {

        if (pos[i] != null && pos[i].equals(id)) {
          poscount++;
        }
      }
    }
    if (neg.length > 0) {
      for (int i = 0; i < neg.length; i++) {
        if (neg[i] != null && neg[i].equals(id)) {
          negcount++;
        }
      }
    }

    totalCount = negcount + poscount;
    String popStats = "Total tests: " + totalCount + "\n" + "Positive: " + poscount + "\n" + "Negative: " + negcount;
    return popStats;
  }

}
