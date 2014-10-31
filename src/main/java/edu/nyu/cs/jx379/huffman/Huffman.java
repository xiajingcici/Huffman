package edu.nyu.cs.jx379.huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Huffman {

	// get each character and its occurrence map
	public static Map<Character, Integer> getFrequencyMap(String s) {
		if (s == null)
			throw new NullPointerException("Input String cannot be null");
		if (s.length() < 2)
			throw new IllegalArgumentException(
					"Input must have at least two characters");
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] chararray = s.toCharArray();
		for (char c : chararray) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}

	// build Huffman Tree using the output data from getFrequencyMap
	public static TreeNode buildHuffmanTree(Map<Character, Integer> map) {
		// store TreeNode in PriorityQueue according to frequencies
		PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>();
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			TreeNode t = new TreeNode(entry.getKey(), entry.getValue());
			pq.offer(t);
		}
		while (!pq.isEmpty()) {
			if (pq.size() == 1) {// get the root
				break;
			} else if (pq.size() > 1) {// continue add node bottom up
				TreeNode t0 = pq.poll();
				TreeNode t1 = pq.poll();
				TreeNode tParent = new TreeNode('#', t0.getFrequence()
						+ t1.getFrequence());
				tParent.setLeft(t0);
				tParent.setRight(t1);
				pq.offer(tParent);
			} else {
				throw new IllegalStateException("The queue cannot be empty.");
			}
		}
		return pq.poll();
	}

	// DFS the Huffman tree, add 0/1 recursively
	private static void getMapping(TreeNode node, Map<Character, String> map,
			String s) {
		// base case: node is a leaf node
		if (node.getLeft() == null && node.getRight() == null) {
			map.put(node.getCharacter(), s);
			return;
		}
		// recursively add 0/1 when left/right is not leaf
		getMapping(node.getLeft(), map, s + "0");
		getMapping(node.getRight(), map, s + "1");
	}

	// get character and its encode, for example: a->11
	public static Map<Character, String> getWholeMap(TreeNode node) {
		Map<Character, String> map = new HashMap<Character, String>();
		String s = "";
		getMapping(node, map, s);
		return map;
	}

	// encode a given string using the Huffman Tree
	public static String encode(String oriStr, TreeNode root) {
		if (oriStr == null || oriStr.isEmpty()) {
			return "";
		}
		Map<Character, String> wholeMap = getWholeMap(root);
//		for(Character c:wholeMap.keySet()){
//			System.out.println(c+":"+wholeMap.get(c));
//		}
		char[] cArray = oriStr.toCharArray();
		StringBuilder result = new StringBuilder();
		for (char c : cArray) {
			result.append(wholeMap.get(c));
		}
		return result.toString();
	}

	// decode a giving binary string to an ordinary string
	public static String decode(String binaryStr, TreeNode root) {
		StringBuilder result = new StringBuilder();
		char[] bArray = binaryStr.toCharArray();
		TreeNode current = root;
		/**
		 * iterate the binary string from root to leaf: once we hit a leaf node,
		 * we get a character and update current node to root node until the end
		 * of the binary string
		 */
		for (char b : bArray) {
			if ('0' == b) {
				current = current.getLeft();
			} else if ('1' == b) {
				current = current.getRight();
			} else {
				throw new IllegalArgumentException(
						"Binary String can only contain 0 or 1.");
			}
			if (current.getLeft() == null && current.getRight() == null) {
				result.append(current.getCharacter());
				current = root;
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String input = "ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa";
		System.out.println("The input string is " + input);
		Map<Character, Integer> freqMap = getFrequencyMap(input);
//		for (Entry<Character, Integer> entry : freqMap.entrySet()) {
//			System.out.println(entry);
//		}
		TreeNode root = buildHuffmanTree(freqMap);
//		System.out.println(root.getFrequence());
		String encodingResult = encode(input, root);
		System.out.println("The result for encoding the input string is "
				+ encodingResult);
		String decodingResult = decode(encodingResult, root);
		System.out.println("The result for decoding the binary string is "
				+ decodingResult);
	}

}
