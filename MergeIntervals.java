// Google interview question:

import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;


public class MergeIntervals{
    public static void main(String[] args) {
        Interval[] intervals = new Interval[5];
        
        intervals[0] = new Interval(1,4);
        intervals[1] = new Interval(2,5);
        intervals[2] = new Interval(3,4);
        intervals[3] = new Interval(6,19);
        intervals[4] = new Interval(8,10);
        System.out.println("Before merge:");
        for ( int i = 0; i < intervals.length; ++i ) {
            System.out.printf("[%d, %d]\n", intervals[i].left, intervals[i].right);
        }
        
        
        Interval[] res = mergeIntervals(intervals);
        
        System.out.println("Result after merge:");
        for ( int i = 0; i < res.length; ++i ) {
            System.out.printf("[%d, %d]\n", res[i].left, res[i].right);
        }
    }
    
    public static Interval[] mergeIntervals(Interval[] intervals) {

        // Sort intervals according to their left number
        Arrays.sort(intervals, new IntervalComparator() );

        int curr = 0;
        int next = curr +1;

        int right = intervals[curr].right;

        ArrayList<Interval> res = new ArrayList<Interval>();
    
        while ( next < intervals.length ) {
            if ( right >= intervals[next].left ) {
                // overlapping
                right = Math.max(right, intervals[next].right);
            } else {
                // not overlapping then move current to next and add current to res
                res.add(new Interval(intervals[curr].left, right ));
                curr = next;
                right = intervals[curr].right;
            
            }
            next++;

         }

        res.add(new Interval(intervals[curr].left, right ));
        
        Interval[] resArray = new Interval[res.size()];
        
        for ( int i = 0; i < res.size(); ++i) {
            resArray[i] = new Interval(res.get(i).left, res.get(i).right);
        }

        return resArray;
    }
}




class IntervalComparator implements Comparator<Interval>{
	@Override
	public int compare(Interval i1, Interval i2) {
		return i1.left - i2.left;
 	}
 }

class Interval{
	public int left;
	public int right;
	public Interval(int l, int r) {
		left = l;
		right = r; 
	 }
 }