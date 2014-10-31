package edu.nyu.cs.jx379.huffman;

//TreeNode is data structure for the Huffman Tree Node.
public class TreeNode implements Comparable<TreeNode> {
	private int frequence;
	private char character;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(char c, int f) {
		frequence = f;
		character = c;
	}

	public int compareTo(TreeNode o) {
		if (this.frequence < o.frequence) {
			return -1;
		} else {
			return 1;
		}
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

}
