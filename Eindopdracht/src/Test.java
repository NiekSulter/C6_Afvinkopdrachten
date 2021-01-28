import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
        String[] parentPaths = {
                "Eindopdracht/resources/openSNP/9369.23andme.7677",
                "Eindopdracht/resources/openSNP/8416.23andme.6745",
                "Eindopdracht/resources/openSNP/9697.23andme.8012",
                "Eindopdracht/resources/openSNP/9399.23andme.8323",
                "Eindopdracht/resources/openSNP/9809.23andme.8069"

        };
        HashMap<Integer, Variant> vMap = OpenVS.readFile();
        LinkedHashMap<String, HashMap<Integer, ParentData>> hMap = OpenPD.createMap(parentPaths);
        ArrayList<String> q = new ArrayList<>(hMap.keySet());
        System.out.println(q);


        for (int i = 0; i < q.size();) {
            for (int j = 0; j < q.size(); j++) {
                if (i == j) {
                    continue;
                }

                System.out.println(q.get(i) + " " + q.get(j));

                ArrayList<Match> dupes = Compare.checkDupes(hMap.get(q.get(i)), hMap.get(q.get(j)));

                ArrayList<Match> confirmedMatches = checkDupes(dupes, vMap);

                confirmedMatches.sort(Collections.reverseOrder());

                String fileName = q.get(i) + "_" + q.get(j) + ".txt";
                writeFile(confirmedMatches, fileName);





                //System.out.println(q.get(i) + " " + q.get(j));
                //String[] paths = new String[] {q.get(i), q.get(j)};
                //ArrayList<String> fileNames = new ArrayList<>(hMap.keySet());
                //ArrayList<Match> dupes = Compare.checkDupes(hMap.get(fileNames.get(0)), hMap.get(fileNames.get(1)));
                //checkDupes(dupes, vMap);
            }
            q.remove(i);
            i=0;
        }

        //writeFile(confirmedMatches);
    }

    public static void getFilesInDirectory() {
        File folder = new File("Eindopdracht/resources/openSNP");
        System.out.println(Arrays.toString(folder.listFiles()));
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

    public static void writeFile(ArrayList<Match> confirmedMatches, String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();

        FileWriter writer = new FileWriter(file);

        writer.write("### Chromosome X=23 and Y=24, Mitochondrial=25. ###" + "\n");
        writer.flush();
        writer.write("RSID" + "\t" + "variantNT" + "\t" + "chromosome" + "\t" + "Parent1NT" + "\t" + "Parent2NT" + "\t" + "Parent1ID" + "\t" + "Parent2ID" + "\n");
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

