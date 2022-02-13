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

public class PokemonNode {
  private Pokemon data; // data field of this PokemonNode
  private PokemonNode leftChild; // reference to the left child
  private PokemonNode rightChild; // reference to the right child

  /**
   * Constructor
   * @param data the pokemon being added
   */
  public PokemonNode(Pokemon data) {
    leftChild = null;
    rightChild = null;

    if (data == null) {
      throw new IllegalArgumentException("the pokemon is null");
    } else {
      this.data = data;

    }
  }

  /**
   * returns the left child
   * @return the pokemon node for the left child
   */
  public PokemonNode getLeftChild() {
    return leftChild;
  }

  /**
   * returns the right child
   * @return the pokemon node for the right child
   */
  public PokemonNode getRightChild() {
    return rightChild;
  }
  /**
   * returns the Pokemon that the node represents
   * @return the pokemon for the node
   */
  public Pokemon getPokemon() {
    return data;
  }

  /**
   * sets the left child
   */
  public void setLeftChild(PokemonNode leftChild) {
    this.leftChild = leftChild;
  }

  /**
   * sets the right child
   */
  public void setRightChild(PokemonNode rightChild) {
    this.rightChild = rightChild;
  }
}
