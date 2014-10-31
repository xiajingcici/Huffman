Huffman encoding allows us to change ordinary string to binary stream, which is safe for data transmission. For example, "ccaffffaa"---->"100100110000"

On contrary, Huffman decoding converts the binary stream into ordinary string. For example, "100100110000"---->"ccaffffaa"

Implementation details:
Firstly, we have input file, which is a large string. To build a optimal binary tree, we need to count the occurrence of each characters and let frequent characters on the top level of the Huffman tree. Because this will greatly decrease the size of the encoding result.

Secondly, when building the Huffman tree, we will use the bottom up approach and store the TreeNode in a priority queue by frequency. Get the two least-frequently character as the leaves, and insert the sum of the two frequency(new TreeNode) into the priority.

Thirdly, encoding by the Huffman tree, we use a recursive DFS function get the map of each character and its Huffman encoding. Then we look up this map and append Huffman codes to the results. 

Fourthly, decoding the binary stream, we iterate the binary string from root to leaf. Once we hit a leaf node, we get a character and update current node to root node until the end of the binary string.

Time Complexity: O(nlgn)

Usage:
Compilation: gradle build
Execution: java -jar Huffman-1.0.jar (Huffman-1.0.jar is located on build/libs folder)

