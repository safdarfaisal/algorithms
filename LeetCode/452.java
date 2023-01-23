import java.util.*;

class Q452 {
    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points){
        int minArrows = 0;
        List<int[]> pointList = new ArrayList<>(Arrays.stream(points).toList());
        pointList.sort(Comparator.comparingInt(valueA -> valueA[0]));
        int[] currentOverlap = null;
        int[] indexList = new int[10000];
        while(pointList.size() != 0) {
            int overlapCounter = 1;
            for(int i = 0; i < pointList.size(); i++){
                int[] point = pointList.get(i);
                if(currentOverlap == null){
                    overlapCounter = 1;
                    currentOverlap = pointList.get(i);
                    indexList[0] = i;
                } else {
                    boolean overlap = false;
                    if(point[0] <= currentOverlap[1] && point[0] >= currentOverlap[0]){
                        currentOverlap[0] = point[0];
                        overlap = true;
                    }
                    if(point[1] <= currentOverlap[1] && point[1] >= currentOverlap[0]){
                        currentOverlap[1] = point[1];
                        overlap = true;
                    }
                    if(overlap){
                        indexList[overlapCounter] = i;
                        overlapCounter++;
                    }
                }
            }
            currentOverlap = null;
            for(int i = overlapCounter - 1; i >= 0; i--){
                pointList.remove(indexList[i]);
                indexList[i] = 0;
            }
            minArrows++;
        }
        return minArrows;
    }
}

