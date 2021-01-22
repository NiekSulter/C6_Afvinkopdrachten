import java.io.*;

public class OpenVS {

    public static void main(String[] args) throws IOException {
        OpenVS o1 = new OpenVS();
        readFile();
    }

    public static void readFile() throws IOException {
        File file = new File("Eindopdracht/resources/variantSummary/variant_summary.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";

        while ((line = reader.readLine()) != null) {
            String[] lines = line.split("\t");
            System.out.println(lines[9] + " " + lines[18] + " " + lines[22]+ " " + lines[33]);
        }
    }

    public static void makeObjects(int chromosome, int rsID, String snp) {
        Variant v = new Variant(chromosome, rsID, snp);
    }

}
