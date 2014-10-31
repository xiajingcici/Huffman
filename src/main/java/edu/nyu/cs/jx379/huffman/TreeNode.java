package edu.nyu.cs.jx379.huffman;

public class TreeNode implements Comparable<TreeNode> {
	int frequence;
	char character;
	TreeNode left;
	TreeNode right;

	TreeNode(char c, int f) {
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

}
