import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
	
	private List<LineSegment> seg = new ArrayList<>();;
	
	/*
	 *  finds all line segments containing 4 points
	 */
	public BruteCollinearPoints(Point[] points)  {
		if (points == null || points.length == 0) {
            throw new NullPointerException("argument is null");
        }
		/*
		if (points.length < 4)
			throw new IllegalArgumentException("less than 4 points"); 
		*/
		
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);
        checkDuplicateNullPoint(points);
        
        for (int p = 0; p < copy.length -3; p++) {
        	for (int q = p + 1; q < copy.length - 2; q++) {
        		for (int r = q + 1; r < copy.length - 1; r++) {
        			for (int s = r + 1; s < copy.length; s++) {
        				if (Double.compare(copy[p].slopeTo(copy[q]), copy[p].slopeTo(copy[r])) == 0 && 
        						Double.compare(copy[p].slopeTo(copy[q]), copy[p].slopeTo(copy[s])) == 0) {
        					LineSegment l1 = new LineSegment(copy[p],copy[s]);
        					seg.add(l1);
        				}
        			}
        		}
        	}
        }
       
	}
	
	/*
	 *  the number of line segments
	 */
	public int numberOfSegments() {
		return seg.size();
	}
	
	/*
	 *   the line segments
	 */
	public LineSegment[] segments() {
		return seg.toArray(new LineSegment[seg.size()]);
	}
	/*
	 * Array has to be sorted before check duplicate and null elements
	 */
	private void checkDuplicateNullPoint(Point[] ps) {
		Arrays.sort(ps);
		for (int i = 0; i < ps.length-1; i++) {
			if (ps[i] == null)
				throw new NullPointerException("Contains null point");
			
			if (ps[i].compareTo(ps[i+1]) == 0)
				throw new IllegalArgumentException("Duplicated points");
			
		}
	}
	
	
}
