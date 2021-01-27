import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class OpenVS {

    public static HashMap<Integer, Variant> readFile() throws IOException {
        LinkedList<Variant> variantList = new LinkedList<>();
        HashMap<Integer, Variant> vMap = new HashMap<>();
        File file = new File("Eindopdracht/resources/variantSummary/variant_summary.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] lines = line.split("\t");

            //RSID, Chromosome, Alternative1, Alternative2
            //System.out.println(lines[9] + " " + lines[18] + " " + lines[22] + " " + lines[33]);

            if (lines[9].equals("na") || lines[18].equals("na")) {
                continue;
            }
            Variant v = makeObjects(lines[18], Integer.parseInt(lines[9]), lines[22], lines[33]);
            variantList.add(v);
            vMap.put(v.getRSID(), v);
        }

        return vMap;
    }

    public static Variant makeObjects(String chromosome, int rsID, String snp1, String snp2) {
        Variant v = new Variant(chromosome, rsID, snp1, snp2);
        return v;
    }
}
