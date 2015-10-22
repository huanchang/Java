import java.util.Arrays;
import java.util.Comparator;

public class MaxProfit{
    public static void main(String[] args) {
        Ad[] ads = new Ad[6];
        ads[0] = new Ad((float)1.5,0,3);
        ads[1] = new Ad((float)3.6,1,4);
        ads[2] = new Ad((float)4.2,4,5);
        ads[3] = new Ad((float)1.8,3,6);
        ads[4] = new Ad((float)4.6,2,6);
        ads[5] = new Ad((float)2.7,0,2);
        
        System.out.printf("Result:%.1f\n",maxProfit(ads,6));
    }

    public static float maxProfit(Ad[] ads, int T) {
        if ( T <= 0 || ads== null) {
            return 0;
        }
        // Sort all ads according to its start time, time complexity O(NlogN).
        Arrays.sort(ads, new AdComparator());
        for ( int i = 0; i < ads.length; ++i) {
            ads[i].display();
        }

        // Dynamic programming use an array
        float[] profits = new float[T+1];
        for ( int i = 0; i < T+1; ++i ) {
            profits[i] = 0;
        }
        
        int previous = 0;
        
        // Time complexity is O(N) and extra space O(T)
        
        for ( int i = 0; i < ads.length ; ++i) {
            int endTime = ads[i].endTime;
            int startTime = ads[i].startTime;
            if (endTime > T) {
                break;
            }
            System.out.printf("previous[%d],curr[%d]\n",previous,endTime);
            while (endTime > previous + 1) {
                profits[previous+1] = profits[previous];
                previous++;
            }
            profits[endTime] = Math.max(profits[endTime-1], Math.max( profits[endTime], ads[i].profit+profits[startTime]));
            previous = endTime;
        
        }
        
        for ( int i = 0; i <= T; ++i) {
            System.out.printf("profits[%d]=%.1f\n",i,profits[i]);
        }
        return profits[T];
    }
}


class AdComparator implements Comparator<Ad>{
	@Override
	public int compare(Ad ad1, Ad ad2) {
		return ad1.endTime - ad2.endTime;
	}
}

class Ad{
	float profit;
	int startTime;
	int endTime;
	public Ad(float p, int s, int e) {
		this.profit = p;
		this.startTime = s;
		this.endTime = e;
	}
	
	public void display() {
	    System.out.printf("ad[%.1f,%d,%d]\n",profit, startTime, endTime);
	}
}