import java.util.Random;

public class SortDemo{
	// This class is used for present sorting algorithms
	public static void main( String[] args ) {
		bubbleSort();
	}
	
	
	public static void bubbleSort(){
		int size = 100;
		
		// Create test data array
		int[] test = new int[size];
		// Use a random object to generate random integer for testing bubble sort
		Random rand = new Random();
		
		for ( int i = 0; i < size; i++ ) {
			test[i] = rand.nextInt( 100) ;
		}
		
		
		BubbleSort bubble = new BubbleSort( size );
		
		bubble.insertAll( test );
		bubble.sort();
		
		SelectionSort selection = new SelectionSort( size );
		selection.insertAll( test );
		selection.sort();
	}
}