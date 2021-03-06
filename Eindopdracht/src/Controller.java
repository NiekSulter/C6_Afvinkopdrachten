import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Centrale class waar alle andere functies en classes vanuit worden aageroepen.
 */

public class Controller {

    public static void main(String[] args) {

        // Array met de bestanden die je wilt vergelijken.
        String[] parentPaths = {
                "Eindopdracht/resources/openSNP/9369.23andme.7677",
                "Eindopdracht/resources/openSNP/8416.23andme.6745",
                "Eindopdracht/resources/openSNP/9697.23andme.8012",
                "Eindopdracht/resources/openSNP/9399.23andme.8323",
                "Eindopdracht/resources/openSNP/9809.23andme.8069"

        };

        checkDownload();

        HashMap<Integer, Variant> vMap = makeVariantMap();

        LinkedHashMap<String, HashMap<Integer, ParentData>> hMap
                = makeParentMap(parentPaths);

        ArrayList<String> fileNames = makeFileNameList(hMap);

        startComparison(fileNames, hMap, vMap);
    }

    /**
     * Aanroepen van de DownloadVS class om het variant_summary bestand
     * te controleren.
     */

    public static void checkDownload() {
        DownloadVS.fileExists();
    }

    /**
     * Aanroepen van de OpenVS class om de HashMap met de Variant bestanden te
     * maken.
     *
     * @return HashMap met Variant objecten.
     */

    public static HashMap<Integer, Variant> makeVariantMap() {

        return OpenVS.readFile();
    }

    /**
     * Aanroepen van de OpenPD class om de 23andme bestanden in te lezen
     * en de bijbehorende LinkedHashMap te maken.
     *
     * @param parentPaths de Array met de paths naar de 23andme bestanden.
     * @return een LinkedHashMap met de parent data.
     */

    public static LinkedHashMap<String, HashMap<Integer, ParentData>>
    makeParentMap(String[] parentPaths) {

        return OpenPD.createMap(parentPaths);
    }

    /**
     * Maken van een ArrayList met de bestandsnamen (parentID's).
     *
     * @param hMap LinkedHashMap met de parent data.
     * @return een ArrayList met de bestandsnamen (parentID's).
     */

    public static ArrayList<String> makeFileNameList(LinkedHashMap<String,
            HashMap<Integer, ParentData>> hMap) {

        return new ArrayList<>(hMap.keySet());
    }

    /**
     * Funcie om elke parent te vergelijken met de andere parents.
     *
     * @param pp   een ArrayList met de bestandsnamen (parentID's).
     * @param hMap de LinkedHashMap met de parent data.
     * @param vMap de HashMap met de Variant data.
     */

    public static void startComparison(
            ArrayList<String> pp,
            LinkedHashMap<String, HashMap<Integer, ParentData>> hMap,
            HashMap<Integer, Variant> vMap) {

        for (int i = 0; i < pp.size(); ) {
            for (int j = 0; j < pp.size(); j++) {
                if (i == j) {
                    continue;
                }

                ArrayList<Match> dupes = Compare.compareSetList(
                        hMap.get(pp.get(i)), hMap.get(pp.get(j)));

                ArrayList<Match> confirmedMatches =
                        Compare.checkDupes(dupes, vMap);

                confirmedMatches.sort(Collections.reverseOrder());

                String fileName = pp.get(i) + "_" + pp.get(j) + ".txt";
                writeFile(confirmedMatches, fileName);
            }
            pp.remove(i);
            i = 0;
        }
    }

    /**
     * Functie om de output per vergelijking tussen 2 parents weg te schrijven
     * naar een .txt bestand.
     *
     * @param confirmedMatches ArrayList met Match objecten
     * @param fileName         filename gegenereerd op basis van de 2 parentID's
     */

    public static void writeFile(ArrayList<Match> confirmedMatches,
                                 String fileName) {
        try {

            File file = new File(
                    "Eindopdracht/resources/output/" + fileName);
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            writer.write("### Chromosome X=23 and Y=24, " +
                    "Mitochondrial=25. ###" + "\n");

            writer.flush();

            writer.write("RSID" + "\t" +
                    "variantNT" + "\t" +
                    "chromosome" + "\t" +
                    "Parent1NT" + "\t" +
                    "Parent2NT" + "\t" +
                    "Parent1ID" + "\t" +
                    "Parent2ID" + "\n");
            writer.flush();

            for (Match m : confirmedMatches) {
                writer.write(m.getRSID() + "\t" +
                        m.getVarNuclo() + "\t" +
                        m.getChromosome() + "\t" +
                        m.getParent1Nuclo() + "\t" +
                        m.getParent2Nuclo() + "\t" +
                        m.getParentID1() + "\t" +
                        m.getParentID2() + "\n");
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
}