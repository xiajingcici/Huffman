package edu.nyu.cs.jx379.huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
	public static Map<Character,Integer> getFrequencyMap(String s){
		if(s==null) throw new NullPointerException("Input String cannot be null");
		if(s.length()<2) throw new IllegalArgumentException("Input must have at least two characters");
		Map<Character,Integer> map=new HashMap<Character,Integer>();
		char[] chararray=s.toCharArray();
		for(char c:chararray){
			if(map.containsKey(c)){
				map.put(c, map.get(c)+1);
			}else{
				map.put(c, 1);
			}
		}
		return map;		
	}	

	public static TreeNode buildHuffmanTree(Map<Character,Integer> map){
		PriorityQueue<TreeNode> pq=new PriorityQueue<TreeNode>();
		for(Map.Entry<Character,Integer> entry: map.entrySet()){
			TreeNode t=new TreeNode(entry.getKey(),entry.getValue());			
			pq.offer(t);
		}
		while(!pq.isEmpty()){
			if(pq.size()==1){
				break;
			}else if(pq.size()>1){
				TreeNode t0 = pq.poll();
				TreeNode t1 = pq.poll();
				TreeNode tParent = new TreeNode('#',t0.frequence+t1.frequence);
				tParent.left=t0;
				tParent.right=t1;
				pq.offer(tParent);
			}else{
				throw new IllegalStateException("Cannot be less than 1.");
			}			
		}
		return pq.poll();		
	}
	
	private static void getMapping(TreeNode node, Map<Character, String> map, String s) {
		if(node.left==null&&node.right==null){
			map.put(node.character, s);
			return;
		}
		getMapping(node.left,map,s+"0");
		getMapping(node.right,map,s+"1");
	}

	public static Map<Character, String> getWholeMap(TreeNode node){
		Map<Character, String> map = new HashMap<Character, String>();
		String s = "";
		getMapping(node,map,s);
		return map;
	}
	
	public static String encode(String oriStr, TreeNode root){
		if(oriStr==null||oriStr.isEmpty()){
			return "";
		}
		Map<Character, String> wholeMap = getWholeMap(root);
		char[] cArray = oriStr.toCharArray();
		StringBuilder result = new StringBuilder();
		for(char c:cArray){
			result.append(wholeMap.get(c));
		}
		return result.toString();
	}
	
	public static String decode(String binaryStr, TreeNode root){
		StringBuilder result = new StringBuilder();
		char[] bArray = binaryStr.toCharArray();
		TreeNode current = root;		
		for(char b:bArray){
			if('0'==b){
				current = current.left;				
			}else if('1'==b){
				current = current.right;
			}else{
				throw new IllegalArgumentException("Binary String can only contain 0 or 1.");
			}
			if(current.left==null&&current.right==null){				
				result.append(current.character);
				current = root;				
			}
		}
		return result.toString();
	}
		
	
	public static void main(String[] args) {
		String s="ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa";
		System.out.println(s);
		Map<Character,Integer> freqMap = getFrequencyMap(s);
		TreeNode root = buildHuffmanTree(freqMap);
		String result = encode(s,root);
		System.out.println(result);
		String result2 = decode(result,root);
		System.out.println(result2);
	}

}
