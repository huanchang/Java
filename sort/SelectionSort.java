public class SelectionSort{
	private int[] array;
	private int count;
	
	// Constructor for array
	public SelectionSort( int size ) {
		array = new int[size];
		count = 0;
	}
	
	// Insert integer
	public void insert( int data ) {
		if ( count == array.length ) {
			 System.out.println( "Failed to insert. Array is fully filled.");
		} else {
			array[count] = data;
			count++;
		}
	}
	
	public void insertAll( int[] data ) {
		for( int i = 0; i < data.length; i++ ) {
			array[count] = data[i];
			count++;
		}
	}
	
	// Print out array data in console
	public void display() {
		System.out.print( "Array: " );
		for ( int i = 0; i < count; ++i ) {
			System.out.printf( "%d", array[i] );
			if ( i == count - 1 ) {
				System.out.print("\n");
			} else {
				System.out.print(", ");
			}
		}
	}
	
	// Sort array
	public void sort() {
		System.out.printf( "Selection Sort: ");
		
		// Selection sort
		int swapCount = 0, comparisonCount = 0;
		
		for (  int i = count - 1 ; i > 0 ; i-- ) {
			int min = i;
			
			for ( int j = 0; j < i; j++ ) {
				if ( array[j] > array[j+1] ) {
					min = j;
				}
				comparisonCount++;
			}
			
			if ( min != i ) {
				swap( i, min);
				swapCount++;
			}
		}
		
		System.out.printf( "\tComparison: %d, \tSwap: %d.\n", comparisonCount, swapCount );
	}
	
	private void swap( int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}