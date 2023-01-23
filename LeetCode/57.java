import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Scratch {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> l = new ArrayList<>(Arrays.stream(intervals).toList());
        l.add(newInterval);
        l.sort((A,B) -> A[0] - B[0]);
        Stack<int[]> s = new Stack<int[]>();
        for(int[] p : l){
            if(!s.isEmpty() && overlap(p, s.peek())){
                int[] a = new int[2];
                a = s.pop();
                a[1] = p[1];
                s.push(a);
            } else {
                s.push(p);
            }
        }
        int[][] x = new int[s.size()][2];
        int i = 0;
        while(!s.isEmpty()){
            x[i++] = s.pop();
        }
        return x;
    }
    boolean overlap(int[] a, int[] b){
        return a[0] <= b[1] && a[1] > b[1];

    }
}

/*

[ ]
{ }

[{}]
[{]}

intervals =
[[1,3],[6,9]]
newInterval =
[2,5]

[1,5], 6
Output
[[1,5],[6,9]]
 */

/*
[[1,2],[3,5], [4,8], [6,7],[8,10],[12,16]]
newInterval =
[4,8]

[3,8]
[1,2]
 */
