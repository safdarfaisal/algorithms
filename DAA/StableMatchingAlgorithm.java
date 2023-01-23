
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pair{
    Integer man;
    Integer woman;
}

class Solution {
    public static void main(String[] args){
        Solution  solution = new Solution();
        List<List<Integer>> men = new ArrayList<List<Integer>>();
        List<List<Integer>> women = new ArrayList<List<Integer>>();
        List<Integer> a0 = new ArrayList<>();
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        List<Integer> a3 = new ArrayList<>();
        List<Integer> a4 = new ArrayList<>();
        List<Integer> b0 = new ArrayList<>();
        List<Integer> b1 = new ArrayList<>();
        List<Integer> b2 = new ArrayList<>();
        List<Integer> b3 = new ArrayList<>();
        List<Integer> b4 = new ArrayList<>();

        //Men
        Collections.addAll(a0, 4, 2, 3, 1, 0);
        Collections.addAll(a1, 3, 4, 2, 0, 1);
        Collections.addAll(a2, 4, 1, 0, 2, 3);
        Collections.addAll(a3, 0, 3, 2, 1, 4);
        Collections.addAll(a4, 1, 2, 3, 4, 0);
        //Women
        Collections.addAll(b0, 2, 4, 3, 1, 0);
        Collections.addAll(b1, 2, 4, 1, 3, 0);
        Collections.addAll(b2, 0, 3, 2, 1, 4);
        Collections.addAll(b3, 3, 4, 1, 2, 0);
        Collections.addAll(b4, 4, 2, 3, 1, 0);

        men.add(a0);
        men.add(a1);
        men.add(a2);
        men.add(a3);
        men.add(a4);
        women.add(b0);
        women.add(b1);
        women.add(b2);
        women.add(b3);
        women.add(b4);
        System.out.println(solution.returnStableMatches(women, men));
    }
    int checkPreference(List<Integer> preference, int newPerson, int curPerson){
        if(newPerson == curPerson){
            return newPerson;
        }
        for(Integer pref : preference){
            if(pref == newPerson){
                return newPerson;
            } else if(pref == curPerson){
                return curPerson;
            }
        }
        return -1;
    }
    public List<Pair> returnStableMatches(List<List<Integer>> unfairPref, List<List<Integer>> fairPref){
        int len = menPref.size();
        List<Pair> stableMatch = new ArrayList<Pair>(len);

        int[] engagWomen = new int[len];
        int[] engagMen = new int[len];
        int proposed = 0;
        int[] propMen = new int[len];
        for(int i = 0; i < len; i++){
            engagMen[i] = -1;
            engagWomen[i] = -1;
            propMen[i] = 0;
        }
        while(proposed != len){
            for(int i = 0; i < len; i++){
                while(engagMen[i] == -1) {
                    List<Integer> a = fairPref.get(i);
                    int currentPref = a.get(propMen[i]);
                    int temp =
                            engagWomen[currentPref] != -1 ? checkPreference(unfairPref.get(currentPref), i, engagWomen[currentPref]) : i;
                    if (temp != engagWomen[currentPref]) {
                        if(engagWomen[currentPref] == -1){
                            proposed++;
                        } else {
                            engagMen[engagWomen[currentPref]] = -1;
                        }
                        engagWomen[currentPref] = temp;
                        engagMen[i] = currentPref;
                    }
                    propMen[i]++;
                }
            }
        }
        for(int i = 0; i < len; i++){
            Pair p = new Pair();
            p.man = i;
            p.woman = engagMen[i];
            stableMatch.add(p);
        }
        return stableMatch;
    }
}

