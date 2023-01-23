

class Q1833 {
    public static void main(String[] args) {
        int[] costs = {1,6,3,1,2,5};
        System.out.println(maxIceCream(costs, 20));
    }
    public static int maxIceCream(int[] costs, int coins) {
        int max = 0;
        int maxIce = 0;
        for(int cost: costs){
            if(max < cost){
                max = cost;
            }
        }
        int length = max > coins ? coins : max;
//        int length = 100000;
        int[] sortedCosts = new int[length];
        for(int cost: costs){
            if(cost <= length){
                sortedCosts[cost - 1]++;
            }
        }
        for(int i = 0; i < length; i++){
            if(sortedCosts[i] != 0) {
                if (coins / (i + 1) >= sortedCosts[i]) {
                    coins -= sortedCosts[i] * (i + 1);
                    maxIce += (sortedCosts[i]);
                } else {
                    maxIce += (coins / (i + 1));
                    break;
                }
            }
        }
        return maxIce;
    }
}