import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenPD {

    public static HashMap<String, HashMap<Integer, ParentData>> createMap(String[] parentPaths) throws IOException {
        HashMap<String, HashMap<Integer, ParentData>> hMap = new HashMap<>();
        for (String i : parentPaths) {
            String fileName = getFilename(i);
            HashMap<Integer, ParentData> pData = readFile(i, fileName);
            hMap.put(fileName, pData);
        }

        //System.out.println(hMap.keySet());
        return hMap;
    }

    public static HashMap<Integer, ParentData> readFile(String path, String parentID) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        //LinkedList<ParentData> pData = new LinkedList<>();
        HashMap<Integer, ParentData> testMap = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("#") || line.startsWith("i")) {
                continue;
            }
            String[] lines = line.split("\\s+");
            ParentData object = makeObject(lines[0], lines[1], Integer.parseInt(lines[2]), lines[3], Integer.parseInt(parentID));
            //pData.add(object);
            testMap.put(object.getrsID(), object);
        }

        // System.out.println(testMap.keySet());
        return testMap;
    }

    public static ParentData makeObject(String rsID, String chromosome, int position, String snp, int parentID) {
        ParentData p = new ParentData(rsID, chromosome, position, snp, parentID);
        System.out.println(chromosome);
        return p;
    }

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
