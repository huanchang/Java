public class TestTrie{
	public static void main ( String[] args ) {
		Trie root = new Trie();
		root.insert( "ab" );
		System.out.println( root.search("a") ? "True" : "False");
		System.out.println( root.search("ab") ? "True" : "False");
		System.out.println( root.startsWith("a") ? "True" : "False");
		System.out.println( root.startsWith("ab") ? "True" : "False");
	}
}