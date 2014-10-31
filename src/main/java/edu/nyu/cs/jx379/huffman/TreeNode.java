package edu.nyu.cs.jx379.huffman;

/**
 * @author Jing Xia
 * TreeNode is data structure for the Huffman Tree Node.
 */
public class TreeNode implements Comparable<TreeNode> {
	private int frequence;
	private char character;
	private TreeNode left;
	private TreeNode right;

	/**
	 * @param c	The char.
	 * @param f The frequence of the char in the original string.
	 * The constructor.
	 */
	public TreeNode(char c, int f) {
		frequence = f;
		character = c;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(TreeNode o) {
		if (this.frequence < o.frequence) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * @return The frequence
	 */
	public int getFrequence() {
		return frequence;
	}

	/**
	 * @param frequence	The setter for frequence.
	 */
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	/**
	 * @return The char.
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * @param character	The setter for char.
	 */
	public void setCharacter(char character) {
		this.character = character;
	}

	/**
	 * @return The left tree/leaf.
	 */
	public TreeNode getLeft() {
		return left;
	}

	/**
	 * @param left The setter for left tree/leaf.
	 */
	public void setLeft(TreeNode left) {
		this.left = left;
	}

	/**
	 * @return The right tree/leaf.
	 */
	public TreeNode getRight() {
		return right;
	}

	/**
	 * @param right	The setter for right tree/leaf.
	 */
	public void setRight(TreeNode right) {
		this.right = right;
	}

}
