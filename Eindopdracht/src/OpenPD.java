import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class om de 23andme bestanden te openen.
 */

public class OpenPD {

    /**
     * Functie om de datastructuur van de 23andme bestanden te maken.
     *
     * @param parentPaths Een array met de paths van de 23andme bestanden
     *                    die je wilt vergelijken.
     * @return LinkedHashMap met als key het ID van de parent en als value een
     * nested HashMap met als key het rsid en als value een ParentData object.
     */

    public static LinkedHashMap<String, HashMap<Integer, ParentData>> createMap
    (String[] parentPaths) {

        LinkedHashMap<String, HashMap<Integer, ParentData>> hMap
                = new LinkedHashMap<>();

        for (String i : parentPaths) {
            String fileName = getFilename(i);
            HashMap<Integer, ParentData> pData = readFile(i, fileName);
            hMap.put(fileName, pData);
        }
        return hMap;
    }

    /**
     * Functie om de 23andme bestanden te openen en parsen.
     *
     * @param path     path van een 23andme bestand meegegeven uit de
     *                 createMap functie.
     * @param parentID Het ID van de parent.
     * @return de HashMap die in de createMap wordt toegevoegd
     * aan de LinkedHashMap
     */

    public static HashMap<Integer, ParentData> readFile
    (String path, String parentID) {

        //Aanmaken van de HashMap
        HashMap<Integer, ParentData> testMap = new HashMap<>();

        try {

            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = reader.readLine()) != null) {

            /*Als de line start met een # of een i wordt de line overgeslagen.
              Lines met een # zijn comments en lines met een i zijn internal,
              deze rsid's zijn niet terug te vinden in het variant_summary
              bestand en dus overbodig om mee te nemen */

                if (line.startsWith("#") || line.startsWith("i")) {
                    continue;
                }
                String[] lines = line.split("\\s+");
                ParentData object = makeObject
                        (lines[0], lines[1], Integer.parseInt(lines[2]),
                                lines[3], Integer.parseInt(parentID));

                testMap.put(object.getrsID(), object);
            }


        } catch (IOException e) {
            System.out.println(e);
        }
        return testMap;
    }

    /**
     * Functie om het ParentID
     *
     * @param rsID       rsid van de parent.
     * @param chromosome chromosoomnummer van de nucleotiden.
     * @param position   position van de nucleotiden.
     * @param snp        het genotype van de parent.
     * @param parentID   het id van de parent (eerste 4 cijfers van het 23
     *                   bestand).
     */

    public static ParentData makeObject
    (String rsID, String chromosome,
     int position, String snp, int parentID) {

        return new ParentData(
                rsID,
                chromosome,
                position,
                snp,
                parentID);
    }

    /**
     * Functie om de filename van het 23andme bestand te parsen en het id van
     * de ouders eruit te halen.
     *
     * @param parentPath path van het bestand meegeven uit
     * @return parentID
     */

    public static String getFilename(String parentPath) {
        String s = parentPath.split("/")[3];
        Pattern pattern = Pattern.compile("^[^.]*");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "error";
        }

    }

}
