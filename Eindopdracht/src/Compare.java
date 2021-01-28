import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compare {

    //oud
    public Set<Integer> createSet(LinkedList<ParentData> list) {
        Set<Integer> set = new LinkedHashSet<>();
        for (ParentData i : list) {
            set.add(i.getrsID());
        }
        return set;
    }

    public static Set<Integer> createSet2(HashMap<Integer, ParentData> map) {
        Set<Integer> set = new LinkedHashSet<>(map.keySet());
        return set;
    }

    public static ArrayList<Match> compareSetList(HashMap<Integer, ParentData> map1, HashMap<Integer, ParentData> map2) {
        ArrayList<Match> matches = new ArrayList<>();
        Set<Integer> s1 = createSet2(map1);
        Set<Integer> s2 = createSet2(map2);

        for (Integer i : s2) {
            if (!s1.add(i)) {
                //System.out.println("dupe" + i);
                Match m = new Match(map1.get(i).getParentID(),
                        map2.get(i).getParentID(),
                        map1.get(i).getChromosome(),
                        map1.get(i).getGenotype(),
                        map2.get(i).getGenotype(),
                        i);
                matches.add(m);
            }
        }
        return matches;
    }

    public static ArrayList<Match> checkDupes(ArrayList<Match> dupes, HashMap<Integer, Variant> vMap) {
        int x = 0;
        ArrayList<Match> confirmedMatches = new ArrayList<>();

        for (Match m : dupes) {
            if (vMap.containsKey(m.getRSID())) {
                Pattern pattern = Pattern.compile(".*[ " + (m.getParent1Nuclo() + m.getParent2Nuclo()) + "].*");
                Matcher matcher = pattern.matcher(vMap.get(m.getRSID()).getSNP());
                if (matcher.find()) {
                    m.setVarNuclo(vMap.get(m.getRSID()).getSNP());
                    m.setVariantMatch(true);
                    confirmedMatches.add(m);
                    x += 1;
                }
            }
        }
        System.out.println(x);
        return confirmedMatches;
    }
}
