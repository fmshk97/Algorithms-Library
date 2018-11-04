import java.util.HashMap;

class Trie {
	private TrieNode root;
	public Trie() {
		root = new TrieNode();
	}
	public void add(String word) {
		root.add(word);
	}
	public boolean isWordPresent(String word) {
		boolean result = true;
		TrieNode node = root;
		if (word != null) {
			for (int i = 0; i < word.length(); i++) {
				TrieNode child = node.getChild(word.charAt(i));
				if (child == null) {
					result = false;
					break;
				}
				node = child;
			}	
		}
		return result ? node.terminates() : result;
	}
}
class TrieNode {
	char data;
	private HashMap<Character, TrieNode> children;
	private boolean terminates;
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
	}
	public TrieNode(char data) {
		this.data = data;
		children = new HashMap<Character, TrieNode>();
	}
	public void add(String word) {
		if (word == null || word.length() == 0) {
			return;
		}
		char firstChar = word.charAt(0);
		TrieNode child = getChild(firstChar); 
		if (child == null) {
			child = new TrieNode(firstChar);
			children.put(firstChar, child);
		}
		if (word.length() > 1) {
			child.add(word.substring(1));
		}
		else {
			child.setTerminates(true);
		}
	}
	public TrieNode getChild(char input) {
		return children.get(input);
	}
	public boolean terminates() {
		return terminates;
	}
	private void setTerminates(boolean value) {
		terminates = value;
	}
}