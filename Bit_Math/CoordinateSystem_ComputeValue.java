/**
 * 367. Valid Perfect Square
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 
 * 69. Sqrt(x)
 * Implement int sqrt(int x).   [if can implement this one, then can simply solve 367]
 
 * 50. Pow(x, n) - Implement pow(x, n).
 
 * 356. Line Reflection
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.
 
 * 149. Max Points on a Line
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class CoordinateSystem_ComputeValue {
    /**
     * 149. Max Points on a Line
     * [SOLUTION]: 1. for points[i], compute the slope for points[i] with all other nodes, 
     *             if same x, set slope to Double.MAX_VALUE;
     *             Use Map<Double, Integer> to record <slope, # of nodes that has same slope>
     *             2. may have duplicate points, so set a counter only counts # of nodes that in same place
     * Time: O(n^2)
     * To improve performance, for points[i], only computes points[0~i-1].
     */
    public int maxPoints(Point[] points) {
        if (points == null || points.length < 2) return points.length;
        int size = points.length;
        int maxCount = 0;
        for (int i = 0;i < size;i++) {
            Map<Double, Integer> countMap = new HashMap<>();   // <grad, count>
            int curCount = 0;   // max number in this loop
            int sameCount = 1;  // # of points in same position, contains itself.
            for (int j = 0;j < i;j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    sameCount++;
                } else {
                    double grad = points[i].x != points[j].x ? (double)(points[i].y - points[j].y) / (double)(points[i].x - points[j].x) : Double.MAX_VALUE;
                    if (countMap.containsKey(grad)) {
                        int count = countMap.get(grad);
                        countMap.put(grad, count + 1);
                        curCount = Math.max(curCount, count + 1);
                    } else {
                        countMap.put(grad, 1);
                        curCount = Math.max(curCount, 1);
                    }
                }
            }
            maxCount = Math.max(maxCount, curCount + sameCount);
        }
        return maxCount;
    }
     
}
