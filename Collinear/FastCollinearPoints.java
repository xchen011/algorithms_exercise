import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
	/*
	 *  finds all line segments containing 4 or more points
	 */
	
	private List<LineSegment> seg = new ArrayList<>();
	
	public FastCollinearPoints(Point[] points) {
		if (points == null) {
            throw new NullPointerException("argument is null");
        }
		/*
		if (points.length < 4)
			throw new IllegalArgumentException("less than 4 points"); 
			*/
		Point[] copy = Arrays.copyOf(points, points.length);
		Arrays.sort(copy);
		checkDuplicateNullPoint(points);
		
	
        
        for (int i = 0; i < copy.length - 3; i++) {
        	Arrays.sort(copy);
        	Arrays.sort(copy, copy[i].slopeOrder());
        	
        	for(int p = 0, first = 1, last = 2; last < copy.length; last++) {
        		while (last < copy.length && 
        				Double.compare(copy[p].slopeTo(copy[first]), copy[p].slopeTo(copy[last])) == 0)
        			last++;
        		
        		if (last - first >= 3 && copy[p].compareTo(copy[first]) < 0) {
        			LineSegment line = new LineSegment(copy[p], copy[last-1]);
        			seg.add(line);        			
        		}
        		
        		first = last;
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
	 *   // the line segments
	 */
	public LineSegment[] segments() {
		return seg.toArray(new LineSegment[numberOfSegments()]);
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
