import java.util.*;

class UnionFind{
    int parent[];
    int rank[];

    UnionFind(int size){
        parent = new int[size];
        for(int i = 0; i < size; i++){
            parent[i] = i;
        }
        rank = new int[size];
    }

    int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        int xParent = find(x);
        int yParent = find(y);
        if(xParent == yParent){
            return ;
        } else if(rank[xParent] < rank[yParent]){
            parent[xParent] = yParent;
        } else if(rank[xParent] > rank[yParent]){
            parent[yParent] = xParent;
        } else {
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}


class Solution {
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        Map <Integer, List<Integer>> adjacency = new HashMap<Integer, List<Integer>>();
        for(int[] edge : edges){
            int pointA = edge[0];
            int pointB = edge[1];
            adjacency.computeIfAbsent(pointA, value -> new ArrayList<Integer>()).add(pointB);
            adjacency.computeIfAbsent(pointB, value -> new ArrayList<Integer>()).add(pointA);
        }
        int n = vals.length;
        TreeMap <Integer, List<Integer>> valuesToNodes = new TreeMap<>();
        for(int i = 0; i < n; i++) {
            valuesToNodes.computeIfAbsent(vals[i], value -> new ArrayList<Integer>()).add(i);
        }
        UnionFind u = new UnionFind(n);
        int goodPaths = 0;
        for(int value : valuesToNodes.keySet()) {
            if (!adjacency.containsKey(value)) {
                continue;
            }
            for (int node : valuesToNodes.get(value)) {
                for (int neighbour : adjacency.get(node)) {
                    if (vals[node] >= vals[neighbour]) {
                        u.union(node, neighbour);
                    }
                }
            }
            HashMap<Integer, Integer> disjoint = new HashMap<Integer,Integer>();
            for(int x : valuesToNodes.get(value)){
                disjoint.put(u.find(x), disjoint.getOrDefault(u.find(x), 0) + 1);
            }
            for(int key : disjoint.keySet()){
                System.out.println(disjoint.keySet());
                int size = disjoint.get(key);
                goodPaths += (size * (size + 1))/2;
            }
        }
        return goodPaths;
    }
}
