import java.io.*;
import java.util.LinkedList;

public class OpenVS {

    public static void main(String[] args) throws IOException {
        OpenVS o1 = new OpenVS();
        readFile();
    }

    public static void readFile() throws IOException {
        File file = new File("Eindopdracht/resources/variantSummary/variant_summary.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] lines = line.split("\t");

            //RSID, Chromosome, Alternative1, Alternative2
            System.out.println(lines[9] + " " + lines[18] + " " + lines[22] + " " + lines[33]);

            if (lines[9].equals("na") || lines[18].equals("na")) {
                continue;
            }
            makeObjects(lines[18], Integer.parseInt(lines[9]), lines[22], lines[33]);
        }
    }

    public static void makeObjects(String chromosome, int rsID, String snp1, String snp2) {
        LinkedList<Variant> variantList = new LinkedList<>();
        Variant v = new Variant(chromosome, rsID, snp1, snp2);
        variantList.add(v);
    }
}
