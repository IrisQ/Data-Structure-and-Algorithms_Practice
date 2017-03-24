/**
 * Intervals:
 * Used: Comparator(as a seperate class/in line), TreeMap
 */


public class IntervalQuestions {
    /**
      * 1. Meeting room:
      * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
      * determine if a person could attend all meetings. eg: Given [[0, 30],[5, 10],[15, 20]], return false.
      * [Solution]: 1. sort by start-time
      *           2. loop through intervals, OVERLAP if cur.start < prev.end
      * Time: O(nlogn) + O(n), sorting + for-loop; Space: O(1).
      */
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        for (int i = 1;i < intervals.length;i++) {
            if (intervals[i].start < intervals[i - 1].end) return false;
        }
        return true;
    }
    
    /**
      * 253. Meeting room-2:
      * find the minimum number of conference rooms required.
      * [Solution]: Create a new class "Point", has time and boolean isStart
      * 1. Create a new List, and all the intervals -> (time, isStart or not), insert into list, sort based on time
      *    if time is the same, [5, 10], [10, 16] need to make sure [5, 10] be placed in front.
      * 2. Now loop and check, if it goes like: start-time, end-time, start-time, end-time.. means no overlap
      *    if there are several start-time next to each other, eg: start-time, start-time, end-time, means there is 
      *    one overlap. Update maxRoom.
      * Time: O(nlgn), Space: 
      */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int len = intervals.length;
        List<Point> points = new ArrayList<>();
        for (int i = 0;i < len;i++) {
            points.add(new Point(intervals[i].start, true));
            points.add(new Point(intervals[i].end, false));
        }
        
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if (p1.val == p2.val) {
                    return p1.isStart ? 1 : -1;
                } else {
                    return p1.val - p2.val;
                }
            }
        });
        
        int startCount = 0;
        int minRoom = 0
        for (Point p:points) {
            if (p.isStart) {
                startCount++;
                minRoom = Math.max(minRoom, startCount);
            } else {
                startCount--;
            }
        }
        return minRoom;
    }
    /* Followup: well.. then, how do we know the TIME PERIOD of MAX number of overlap?
     * In "minRoom = Math.max(minRoom, startCount)", when max == startCount, record the p.val(start-time)
     * And record the next end-time, [time between those two] is the max overlapped interval.
     */    
    class Point {
        int val;
        boolean isStart;
        Point(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }
    }
    
    /**
     * 56. Merge Intervals
     * Given a collection of intervals, merge all overlapping intervals.
     * Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
     * [Solution]: 1. Sort by start time.
     *             2. Store prev Interval, 
     *              if (cur.start <= prev.end) end = Math.max(cur.end, prev.end);
     *              else add prev interal into res, update prev.start/end to cur.start/end       
     * Time: O(nlogn) + O(n), Space: O(1)
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2) return intervals;
        Comparator<Interval> IntervalComprator = new Comparator<Interval>() {
            @Override
            public int compare(Interval v1, Interval v2) {
                return v1.start - v2.start;
            }
        };
        Collections.sort(intervals, IntervalComprator);
        // don't need eatra size, catch the size of the list before processing
        // and delete one in each iteration, insert the merged one at the end
        // then after loop, the left are all merged ones
        int size = intervals.size();
        Interval cur = intervals.remove(0);
        int start = cur.start, end = cur.end;
        for (int i = 1;i < size;i++) {
            cur = intervals.remove(0);
            if (cur.start <= end) {
                end = Math.max(cur.end, end);
            } else {
                intervals.add(new Interval(start, end));
                start = cur.start;
                end = cur.end;
            }
        }
        intervals.add(new Interval(start, end));
        return intervals;
    }
    
    /**
     * 57. Insert Interval
     * SIMPLE: Insert + merge again
     */
    
    /**
     * [Solution-2] Separate into 3 parts: 
     * 1. handle the intervals [before + not overlapped] with newInterval
     * 2. handle the ones [overlapped] with newInterval
     * 3. handle the ones [after + not overlapped] ones
     * MAXIMUM 3 parts, ALSO POSSIBLE 1/3 don't exist.
     * Time: O(n), Extra Space: O(1)
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (newInterval == null) return intervals;
        // 1. add the ones before overlapping
        int idx = 0;
        int len = intervals.size();
        while (idx < len && intervals.get(idx).end < newStart) {
            res.add(intervals.get(idx));
            idx++;
        }
        // 2. add overlapping one
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        while (idx < len && intervals.get(idx).start <= newEnd) {
            newStart = Math.min(newStart, intervals.get(idx).start);
            newEnd = Math.max(newEnd, intervals.get(idx).end);
            idx++;
        }
        res.add(new Interval(newStart, newEnd));
        // 3. add the ones after
        while (idx < len) {
            res.add(intervals.get(idx++));
        }
        return res;
    }
    
    /**
     * 228. Summary Ranges, EASY
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     * eq: given [0,1,2,4,5,7], return ["0->2","4->5","7"]. 
     * >>>>>> Using string is better than StringBuilder, clean and clear.>>>>>>>
     * >>>>A small problem: List<String> res, how to add integer? res.add(someInteger + "");
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int startIdx = 0;
        int len = nums.length;
        for (int i = 1;i < len;i++) {
            if (nums[i] - nums[i - 1] != 1) {
                if (i == startIdx + 1) {
                    res.add(nums[startIdx] + "");
                } else {
                    res.add(nums[startIdx] + "->" + nums[i - 1]);
                }
                startIdx = i;
            } 
        }
        if (startIdx == len - 1) res.add(nums[startIdx] + "");
        else res.add(nums[startIdx] + "->" + nums[len - 1]);
        return res;
    }
    
    /**
     * 163. Missing Ranges
     * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
     * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int n:nums) {
            if (n >= upper) {   // if upper < nums[i], break and jump those those ones after
                lower = n;
                break;
            }
            /* lower is 0, 1, 2, 3.. +1 at a time, if nums jump through some number, it would > lower
             * and the numbers from [lower, nums[i] - 1] would be the part missing.
             */
            int below = n - 1;  
            if (lower == below) res.add(lower + "");
            else if (lower < below) res.add(lower + "->" + below);
            lower = n + 1;
        }
        if (lower == upper) {   // if the last number in nums < upper, add [last + 1, upper] here
            res.add(lower + "");
        } else if (lower < upper) {
            res.add(lower + "->" + upper);
        }
        return res;
    }    
}

class Solution2 {
    /**
     * 218. The Skyline Problem
     * Similar to "Meeting room-2"
     */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        // 1. add into collection, and distinguish between left/right point
        //    left: height < 0, right: height > 0
        for (int[] building:buildings) {
            points.add(new Point(building[0], -building[2]));
            points.add(new Point(building[1], building[2]));
        }
        
        // 2. sorting-based on position, the one has lower position will be put in the front,
        //  if two has same position, means this may be the same building, and need 
        //  to make sure left in front of right.
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if (p1.position == p2.position) {
                    return p1.height - p2.height;
                } else {
                    return p1.position - p2.position;
                }
            }
        });
        // 3. use PriorityQueue in reverse order, always use the highest height available.
        // if p.height < 0: left-side/start of building, now the height of p starts affect result, add into pq.
        // if p.height > 0: right-side/end of building, leave the region p has effect, remove from pq.
        // AND SAVE Prev Point. ONLY WHEN cur.height != prev.height, the point is visible. 
        PriorityQueue<Integer> pq = new PriorityQueue<>(1000, Collections.reverseOrder());
        pq.add(0);
        int prev = 0;
        for (Point p:points) {
            if (p.height < 0) {
                pq.add(-p.height);
            } else {
                pq.remove(p.height);
            }
            int cur = pq.peek();
            if (cur != prev) {
                res.add(new int[]{p.position, cur});
                prev = cur;
            }
        }
        return res;
    }
    
    class Point {
        int position;
        int height;
        
        Point(int position, int height) {
            this.position = position;
            this.height = height;
        }
    }
}

public class Interval {
    int start;
    int end;
    Interval() {start = 0; end = 0;}
    Interval(int s, int e) {start = s; end = e};
}
