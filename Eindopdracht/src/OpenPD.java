import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class OpenPD {

    public static HashMap<String, LinkedList<ParentData>> createMap(String[] parentPaths) throws IOException {
        HashMap<String, LinkedList<ParentData>> hMap = new HashMap<>();
        for (String i : parentPaths) {
            LinkedList<ParentData> pData = readFile(i);
            hMap.put(i.split("/")[3], pData);
        }
        return hMap;
    }

    public static LinkedList<ParentData> readFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        LinkedList<ParentData> pData = new LinkedList<>();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            String[] lines = line.split("\\s+");
            ParentData object = makeObject(lines[0], lines[1], Integer.parseInt(lines[2]), lines[3]);
            pData.add(object);
        }
        return pData;
    }

    public static ParentData makeObject(String rsID, String chromosome, int position, String snp) {
        ParentData p = new ParentData(rsID, chromosome, position, snp);
        return p;
    }

}
