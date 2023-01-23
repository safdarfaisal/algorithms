import java.util.*;

class Q134 {
    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if(Arrays.stream(gas).sum() < Arrays.stream(cost).sum()){
            return -1;
        }
        int[] available = new int[gas.length];
        int[] totalFromZero = new int[gas.length];
        int[] totalFromEnd = new int[gas.length];
        for(int i = 0; i < gas.length; i++){
            available[i] = (gas[i] - cost[i]);
            totalFromZero[i] = (i == 0) ? 0 : totalFromZero[i - 1] + available[i - 1];
        }
        for(int i = gas.length - 1; i >= 0; i--){
            totalFromEnd[i] = (i == gas.length - 1) ? 0 : totalFromEnd[i+1] + available[i+1];
        }
        int cycleCost = available[gas.length - 1];
        int sum = 0;
        int length = available.length;
        int k;
        for(int i = 0; i < length ; i++){
            k = i;
            if(available[k] >= 0){
                sum += available[k++];
                while(k % length != i && sum >= 0){
                    sum += available[(k % length)];
                    k++;
                }
                if(sum >= 0){
                    return i;
                }
                sum = 0;
            }
        }
        return -1;
    }
}

/*
-2, -2, -2, 3, 3
to nth
-3, -1, 1, 3, 0;
to 1st
0, 2, 4, 6,
0,2,4,6.3
 */

