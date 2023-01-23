import java.util.ArrayList;
import java.util.List;

class Scratch {
    public static void main(String[] args) {
        
    }
    private int longestPath = 1;
    private List<Integer>[] adj;
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int u = i, v = parent[i];
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(0, -1, s);
        return longestPath;
    }
    private int dfs(int currentNode, int parent, String s) {
        int maxLen = 0, secondMaxLen = 0;
        for (int child : adj[currentNode]) {
            if (child == parent) {
                continue;
            }

            int maxLenFromChild = dfs(child, currentNode, s);
            if (s.charAt(child) == s.charAt(currentNode)) {
                continue;
            }

            if (maxLenFromChild > maxLen) {
                secondMaxLen = maxLen;
                maxLen = maxLenFromChild;
            }
            else if (maxLenFromChild > secondMaxLen) {
                secondMaxLen = maxLenFromChild;
            }
        }

        longestPath = Math.max(longestPath, maxLen + secondMaxLen + 1);     // we update the so far discovered longest path length
        return maxLen + 1;
    }
}
}