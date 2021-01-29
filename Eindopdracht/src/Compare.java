import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class om de rsid's en genotypen van de ouders te vergelijken t.o.v. het
 * variant_summary bestand.
 */

public class Compare {

    /**
     * Aanmaken van een set op basis van de keys (rsid's)
     *
     * @param map de HashMap met als keys de rsid's en als value een ParentData
     *            object.
     * @return een Set met de rsid's van een parent.
     */

    public static Set<Integer> createSet2(HashMap<Integer, ParentData> map) {
        Set<Integer> set = new LinkedHashSet<>(map.keySet());
        return set;
    }

    /**
     * Vergelijken van 2 Sets gevuld met de rsid's van beide parents.
     *
     * @param map1 de HashMap van parent 1
     * @param map2 de HashMap van parent 2
     * @return een ArrayList met Match objecten.
     */

    public static ArrayList<Match> compareSetList(
            HashMap<Integer, ParentData> map1,
            HashMap<Integer, ParentData> map2) {


        //Aanmaken van de ArrayList en 2 Sets.
        ArrayList<Match> matches = new ArrayList<>();
        Set<Integer> s1 = createSet2(map1);
        Set<Integer> s2 = createSet2(map2);

        /* Vergelijken van de Sets van beide parents, als de waarde van een Set
        niet toegevoegd kan worden aan de andere Set bestaat deze waarde dus al.
        Er is dan dus overlap tussen de 2 parents.
         */
        for (Integer i : s2) {
            if (!s1.add(i)) {

                //Als er overlap is wordt er een Match object aangemaakt.
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

    /**
     * De ArrayList met Match objecten wordt vergeleken met de HashMap met
     * Variant objecten. Als er overlap is tussen de rsid's wordt er gekeken
     * of een van beide ouders de snp heeft. Mocht dit het geval zijn dan
     * wordt er een boolean naar true gezet in het object en wordt het object
     * aan een andere ArrayList toegevoegd, zodat deze later geprint kan
     * worden.
     *
     * @param dupes ArrayList met Match objecten gebaseerd op matchende rsid's
     *              tussen ouders.
     * @param vMap  HashMap met als key een rsid en als value een Variant object.
     * @return een ArrayList met Match objecten die later naar een bestand
     * geschreven moeten worden.
     */
    public static ArrayList<Match> checkDupes(ArrayList<Match> dupes,
                                              HashMap<Integer, Variant> vMap) {

        ArrayList<Match> confirmedMatches = new ArrayList<>();

        for (Match m : dupes) {
            if (vMap.containsKey(m.getRSID())) {
                Pattern pattern = Pattern.compile(
                        ".*[ " + (m.getParent1Nuclo() +
                                m.getParent2Nuclo()) + "].*");

                Matcher matcher = pattern.matcher(
                        vMap.get(m.getRSID()).getSNP());

                if (matcher.find()) {
                    m.setVarNuclo(vMap.get(m.getRSID()).getSNP());
                    m.setVariantMatch(true);
                    confirmedMatches.add(m);
                }
            }
        }
        return confirmedMatches;
    }
}
