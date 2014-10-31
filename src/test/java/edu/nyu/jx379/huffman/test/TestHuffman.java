package edu.nyu.jx379.huffman.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.jx379.huffman.Huffman;
import edu.nyu.cs.jx379.huffman.TreeNode;

public class TestHuffman {
	public static TreeNode root;
	
	
	@Before
	public void before(){
		
	}
	
	@After
	public void after(){
		
	}
	
	@Test
	public void testGetFrequencyMap() {
		Map<Character, Integer> map = Huffman
				.getFrequencyMap("ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa");
		// "f=6 e=7 s=1 c=5 a=13 o=4"
		assertEquals((Integer) map.get('f'), (Integer) 6);
		assertEquals((Integer) map.get('e'), (Integer) 7);
		assertEquals((Integer) map.get('s'), (Integer) 1);
		assertEquals((Integer) map.get('c'), (Integer) 5);
		assertEquals((Integer) map.get('a'), (Integer) 13);
		assertEquals((Integer) map.get('o'), (Integer) 4);
	}

	@Test
	public void testBuildHuffmanTree() {
		Map<Character, Integer> freqMap = Huffman
				.getFrequencyMap("ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa");
		TreeNode root = Huffman.buildHuffmanTree(freqMap);
		assertEquals((Integer) root.getFrequence(), (Integer) 36);
	}

	@Test
	public void testGetWholeMap() {
		Map<Character, Integer> freqMap = Huffman
				.getFrequencyMap("ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa");
		TreeNode root = Huffman.buildHuffmanTree(freqMap);
		Map<Character, String> map = Huffman.getWholeMap(root);
		// f:00
		// e:01
		// s:1010
		// c:100
		// a:11
		// o:1011
		assertEquals((String) map.get('f'), "00");
		assertEquals((String) map.get('e'), "01");
		assertEquals((String) map.get('s'), "1010");
		assertEquals((String) map.get('c'), "100");
		assertEquals((String) map.get('a'), "11");
		assertEquals((String) map.get('o'), "1011");
	}

	@Test
	public void testEncode() {
		assertEquals(
				"100100110000000011111111010101010101011111000011101110111001001001110111011101011111111",
				Huffman.encode(
						"ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa",
						Huffman.buildHuffmanTree(Huffman
								.getFrequencyMap("ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa"))));
	}

	@Test
	public void testDecode() {
		assertEquals(
				"ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa",
				Huffman.decode(
						"100100110000000011111111010101010101011111000"
								+ "011101110111001001001110111011101011111111",
						Huffman.buildHuffmanTree(Huffman
								.getFrequencyMap("ccaffffaaaaeeeeeeeaaffaoocccaoosaaaa"))));
	}

}
