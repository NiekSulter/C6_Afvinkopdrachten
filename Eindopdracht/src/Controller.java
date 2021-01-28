import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Controller {

    public static void main(String[] args) throws IOException, InterruptedException {

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

        LinkedHashMap<String, HashMap<Integer, ParentData>> hMap = makeParentMap(parentPaths);

        ArrayList<String> fileNames = makeFileNameList(hMap);

        startComparrison(fileNames, hMap, vMap);
    }

    public static void checkDownload() throws IOException, InterruptedException {
        DownloadVS.fileExists();
    }

    public static HashMap<Integer, Variant> makeVariantMap() throws IOException {
        HashMap<Integer, Variant> vMap = OpenVS.readFile();
        return vMap;
    }

    public static LinkedHashMap<String, HashMap<Integer, ParentData>> makeParentMap(String[] parentPaths) throws IOException {
        LinkedHashMap<String, HashMap<Integer, ParentData>> hMap = OpenPD.createMap(parentPaths);
        return hMap;
    }

    public static ArrayList<String> makeFileNameList(HashMap<String, HashMap<Integer, ParentData>> hMap) {
        ArrayList<String> fileNames = new ArrayList<>(hMap.keySet());
        return fileNames;
    }

    public static void startComparrison(ArrayList<String> pp, LinkedHashMap<String, HashMap<Integer, ParentData>> hMap,
                                        HashMap<Integer, Variant> vMap) throws IOException {
        for (int i = 0; i < pp.size(); ) {
            for (int j = 0; j < pp.size(); j++) {
                if (i == j) {
                    continue;
                }

                ArrayList<Match> dupes = Compare.compareSetList(hMap.get(pp.get(i)), hMap.get(pp.get(j)));

                ArrayList<Match> confirmedMatches = Compare.checkDupes(dupes, vMap);

                confirmedMatches.sort(Collections.reverseOrder());

                String fileName = pp.get(i) + "_" + pp.get(j) + ".txt";
                writeFile(confirmedMatches, fileName);
            }
            pp.remove(i);
            i = 0;
        }
    }

    public static void writeFile(ArrayList<Match> confirmedMatches, String fileName) throws IOException {
        File file = new File("Eindopdracht/resources/output/" + fileName);
        file.createNewFile();

        FileWriter writer = new FileWriter(file);

        writer.write("### Chromosome X=23 and Y=24, Mitochondrial=25. ###" + "\n");
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
    }
}