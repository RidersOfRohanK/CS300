//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Pokemon Catalog
// Course:   CS 300 Fall 2020
//
// Author:   Rohan Kale
// Email:    rkale2@wisc.edu
// Lecturer: (Hobbes LeGault)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods
 * defined in the class PokemonTree.
 *
 */

public class PokemonTreeTester {

	/**
	 * Checks the correctness of the implementation of both addPokemon() and
	 * toString() methods implemented in the PokemonTree class. This unit test
	 * considers at least the following scenarios. (1) Create a new empty
	 * PokemonTree, and check that its size is 0, it is empty, and that its string
	 * representation is an empty string "". (2) try adding one Pokemon and then
	 * check that the addPokemon() method call returns true, the tree is not empty,
	 * its size is 1, and the .toString() called on the tree returns the expected
	 * output. (3) Try adding another Pokemon which is more powerful than the one at
	 * the root, (4) Try adding a third Pokemon which is less powerful than the one
	 * at the root, (5) Try adding at least two further Pokemons such that one must
	 * be added at the left subtree, and the other at the right subtree. For all the
	 * above scenarios, and more, double check each time that size() method returns
	 * the expected value, the add method call returns true, and that the
	 * .toString() method returns the expected string representation of the contents
	 * of the binary search tree in an ascendant order from the most powerful
	 * Pokemon to the least powerful one. (6) Try adding a Pokemon whose CP value
	 * was used as a key for a Pokemon already stored in the tree. Make sure that
	 * the addPokemon() method call returned false, and that the size of the tree
	 * did not change.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPokemonToStringSize() {
		PokemonTree tree = new PokemonTree();
		if(!tree.isEmpty())return false;
		if(tree.size()!=0)return false;
		if(!tree.toString().equals(""))return false;

		if(!tree.addPokemon(new Pokemon("Pikachu","3,3,3")))return false;
		if(tree.isEmpty())return false;
		if(tree.size()!=1)return false;
		if(!tree.toString().equals("[Pikachu CP:333 (A:3 S:3 D:3)]"))return false;

		if(!tree.addPokemon(new Pokemon("Lugia","10,10,10")))return false;
		if(tree.isEmpty())return false;
		if(tree.size()!=2)return false;
		if(tree.height()!=2)return false;
		if(!tree.toString().equals("[Pikachu CP:333 (A:3 S:3 D:3)]\n" +
												  "[Lugia CP:1110 (A:11 S:1 D:0)]\n"))return false;


		if(!tree.addPokemon(new Pokemon("Ratata","1,2,1")))return false;
		if(tree.isEmpty())return false;
		if(tree.size()!=3)return false;
		if(tree.height()!=2)return false;

		if(!tree.toString().equals("\n[Ratata CP:121 (A:1 S:2 D:1)]\n"+"[Pikachu CP:333 (A:3 S:3 D:3)]\n" +
												   "[Lugia CP:1110 (A:11 S:1 D:0)]\n"
		))return false;

		Pokemon p  = tree.lookup(121);

		if(tree.addPokemon(new Pokemon("Ratata","1,2,1")))return false;
		if(tree.isEmpty())return false;
		if(tree.size()!=3)return false;
		if(tree.height()!=2)return false;


		return true;
	}//TODO

	/**
	 * This method checks mainly for the correctness of the PokemonTree.lookup()
	 * method. It must consider at least the following test scenarios. (1) Create a
	 * new PokemonTree. Then, check that calling the lookup() method with any valid
	 * CP value must throw a NoSuchElementException. (2) Consider a PokemonTree of
	 * height 3 which consists of at least 5 PokemonNodes. Then, try to call
	 * lookup() method to search for the Pokemon at the root of the tree, then a
	 * Pokemon at the right and left subtrees at different levels. Make sure that
	 * the lookup() method returns the expected output for every method call. (3)
	 * Consider calling .lookup() method on a non-empty PokemonTree with a CP value
	 * not stored in the tree, and ensure that the method call throws a
	 * NoSuchElementException.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPokemonAndLookup() {
		boolean works = false;
		PokemonTree tree = new PokemonTree();
		try{
			tree.lookup(100);
		}catch (NoSuchElementException e){
			works = true;
		}
		if(!works)return works;

		tree.addPokemon(new Pokemon("Pikachu","3,3,3"));
		tree.addPokemon(new Pokemon("Ratata","2,2,2"));
		tree.addPokemon(new Pokemon("Zubat","1,1,1"));
		tree.addPokemon(new Pokemon("Luigi","5,5,5"));
		tree.addPokemon(new Pokemon("Mario","7,7,7"));

		if(tree.height()!=3)return false;
		if(tree.size()!=5)return false;


		Pokemon p1 = tree.lookup(333);
		if(p1.compareTo(new Pokemon("Pikachu","3,3,3"))!=0)return false;

		Pokemon p2 = tree.lookup(222);
		if(p2.compareTo(new Pokemon("Ratata","2,2,2"))!=0)return false;

		Pokemon p3 = tree.lookup(111);
		if(p3.compareTo(new Pokemon("Zubat","1,1,1"))!=0)return false;

		Pokemon p4 = tree.lookup(555);
		if(p4.compareTo(new Pokemon("Luigi","5,5,5"))!=0)return false;

		Pokemon p5 = tree.lookup(777);
		if(p5.compareTo(new Pokemon("Mario","7,7,7"))!=0)return false;

		works = false;
		try{
			tree.lookup(900);
		}catch (NoSuchElementException e){
			works = true;
		}
		if(!works)return works;

		return works;
	}

	/**
	 * Checks for the correctness of PokemonTree.height() method. This test must
	 * consider several scenarios such as, (1) ensures that the height of an empty
	 * Pokemon tree is zero. (2) ensures that the height of a tree which consists of
	 * only one node is 1. (3) ensures that the height of a PokemonTree with the
	 * following structure for instance, is 4.
	 *      (*)
	 *     /   \
	 *   (*)   (*)
	 *     \   / \
	 *    (*)(*) (*)
	 *           /
	 *         (*)
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testHeight() {
	PokemonTree tree = new PokemonTree();
	if(tree.isEmpty()){
		if(tree.height()!=0)return false;
	}else{
		return false;
	}
	tree.addPokemon(new Pokemon("Picarchu","5,5,5"));
	if(tree.isEmpty())return false;
	if(tree.height()!=1)return false;

	tree.addPokemon(new Pokemon("Rat","2,2,2"));
	tree.addPokemon(new Pokemon("Bat","3,3,3"));
	tree.addPokemon(new Pokemon("Cat","7,7,7"));
	tree.addPokemon(new Pokemon("Gat","6,6,6"));
	tree.addPokemon(new Pokemon("Mat","9,9,9"));
	tree.addPokemon(new Pokemon("Pat","8,8,8"));

	if(tree.height()!=4)return false;

	return true;

	}

	/**
	 * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetLeastPowerfulPokemon() {
	  PokemonTree tree = new PokemonTree();
	  if(tree.isEmpty()){
	    Pokemon  p = tree.getLeastPowerfulPokemon();
	    if(p!=null)return false;
      }else{
	    return false;
      }

      tree.addPokemon(new Pokemon("Picarchu","5,5,5"));
      if(tree.getLeastPowerfulPokemon().compareTo(new Pokemon("Picarchu","5,5,5"))!=0)return false;

      tree.addPokemon(new Pokemon("Rat","2,2,2"));
      tree.addPokemon(new Pokemon("Bat","3,3,3"));
      tree.addPokemon(new Pokemon("Cat","7,7,7"));
      tree.addPokemon(new Pokemon("Gat","6,6,6"));
      tree.addPokemon(new Pokemon("Mat","9,9,9"));
      tree.addPokemon(new Pokemon("Pat","8,8,8"));

      if(tree.getLeastPowerfulPokemon().compareTo(new Pokemon("Rat","2,2,2"))!=0) return false;


	  return true;
    }

	/**
	 * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetMostPowerfulPokemon() {
      PokemonTree tree = new PokemonTree();
      if(tree.isEmpty()){
        Pokemon  p = tree.getMostPowerfulPokemon();
        if(p!=null)return false;
      }else{
        return false;
      }

      tree.addPokemon(new Pokemon("Picarchu","5,5,5"));
      if(tree.getMostPowerfulPokemon().compareTo(new Pokemon("Picarchu","5,5,5"))!=0)return false;

      tree.addPokemon(new Pokemon("Rat","2,2,2"));
      tree.addPokemon(new Pokemon("Bat","3,3,3"));
      tree.addPokemon(new Pokemon("Cat","7,7,7"));
      tree.addPokemon(new Pokemon("Gat","6,6,6"));
      tree.addPokemon(new Pokemon("Pat","9,9,9"));
      tree.addPokemon(new Pokemon("Mat","8,8,8"));

      if(tree.getMostPowerfulPokemon().compareTo(new Pokemon("Pat","9,9,9"))!=0) return false;


      return true;

    }

	/**
	 * Calls the test methods
	 *
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println(testAddPokemonToStringSize());
		System.out.println(testAddPokemonAndLookup());
        System.out.println(testHeight());
        System.out.println(testGetLeastPowerfulPokemon());
      System.out.println(testGetMostPowerfulPokemon());
	}

}
