import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class UnionFind{
    char parent[];
    int rank[];

    UnionFind(int size){
        parent = new char[size];
        for(int i = 0; i < size; i++){
            parent[i] = (char)('a' + i);
        }
        rank = new int[size];
    }

    char find(char a){
        int index = a - 'a';
        if(parent[index] != a){
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

    public void union(char a, char b){
        int indexA = (int)(find(a) - 'a');
        int indexB = (int)(find(b) - 'a');
        if(a == b){
            return ;
        }
        if(find(a) > find(b)){
            parent[indexA] = find(b);
        } else if (find(a) < find(b)){
            parent[indexB] = find(a);
        }
    }

}

class Scratch {
    public static void main(String[] args) {
        System.out.println(smallestEquivalentString("nlbldbllkhdibaleegjinfdidmjkhgbndcqgklapacedlekgaa", "qdgepkfjlggpcbjqknhcmbddfqfkqbqpolemhakcmjgodekdgd", "wvfezafeziwwxpztsllntquhrglctqayxuobmzffymdszwcaua"));
    }
    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        HashMap<Character, List<Character>>  lexicoGeneration = new HashMap<>();
        for(int i = 0; i < s1.length(); i++){
            Character a = s1.charAt(i), b = s2.charAt(i);
            lexicoGeneration.computeIfAbsent(a,value -> new ArrayList<Character>()).add(b);
            lexicoGeneration.computeIfAbsent(b,value -> new ArrayList<Character>()).add(a);
        }
        UnionFind u = new UnionFind(26);
        for(char a : lexicoGeneration.keySet()){
            for(char b : lexicoGeneration.get(a).stream().distinct().toList()){
                    u.union(u.find(a), u.find(b));
            }
        }
        char[] a = new char[baseStr.length()];
        for(int i = 0; i < baseStr.length(); i++){
            char x = baseStr.charAt(i);
            a[i] = u.find(x);
        }
        return String.copyValueOf(a);
    }
}