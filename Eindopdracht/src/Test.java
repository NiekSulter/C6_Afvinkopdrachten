import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        String[] parentPaths = {
                "Een",
                "Twee",
                "Drie",
                "Vier",
                "Vijf"
        };
        ArrayList<String> q = new ArrayList<>(Arrays.asList(parentPaths.clone()));

        for (int i = 0; i < q.size();) {
            for (int j = 0; j < q.size(); j++) {
                if (i == j) {
                    continue;
                }
                System.out.println(q.get(i) + " " + q.get(j));
            }
            q.remove(i);
            i=0;

        }
    }
}

