import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class om het variant_summary bestand in te lezen en Variant objecten
 * aan te maken.
 */

public class OpenVS {

    /**
     * Functie om het variant_summery te openen en te parsen.
     *
     * @return een HashMap met als key het rsid en als value een Variant
     * object.
     */

    public static HashMap<Integer, Variant> readFile() {
        HashMap<Integer, Variant> vMap = new HashMap<>();

        try {
            File file =
                    new File("Eindopdracht/resources/variantSummary" +
                            "/variant_summary.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            //Skippen van de headers
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] lines = line.split("\t");

                //Als het RSID of het chromosoomnummer ontbreekt wordt
                // de rij overgeslagen
                if (lines[9].equals("na") ||
                        lines[18].equals("na") || lines[18].equals("Un")) {
                    continue;
                }

                Variant v = makeObjects(lines[18],
                        Integer.parseInt(lines[9]), lines[22], lines[33]);
                vMap.put(v.getRSID(), v);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return vMap;
    }

    /**
     * Functie om het Variant object aan te maken.
     *
     * @param chromosome chromosoomnummer van de snp.
     * @param rsID       het rsid van de snp.
     * @param snp1       eerste kolom (W).
     * @param snp2       tweede kolom (AH).
     * @return het Variant object.
     */
    public static Variant makeObjects(
            String chromosome, int rsID, String snp1, String snp2) {

        return new Variant(chromosome, rsID, snp1, snp2);
    }
}
