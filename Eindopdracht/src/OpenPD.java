import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class OpenPD {

    public static void main(String[] args) throws IOException {
        HashMap<String, LinkedList<ParentData>> hMap = new HashMap<>();
        OpenPD pd = new OpenPD();
        String[] parentPaths = new String[] {"Eindopdracht/resources/openSNP/8416.23andme.6745",
                "Eindopdracht/resources/openSNP/8840.23andme.7183",
                "Eindopdracht/resources/openSNP/9369.23andme.7677",
                "Eindopdracht/resources/openSNP/9399.23andme.8323",
                "Eindopdracht/resources/openSNP/9809.23andme.8069"};

        for(String i : parentPaths) {
            LinkedList<ParentData> pData = pd.readFile(i);
            hMap.put(i.split("/")[3], pData);
        }

        System.out.println(hMap.keySet());

    }

    public LinkedList<ParentData> readFile(String path) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        LinkedList<ParentData> pData = new LinkedList<>();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            String[] lines = line.split("\\s+");
            //System.out.println(lines[0] + " " + lines[1] + " " + lines[2] + " " + lines[3]);
            ParentData object = makeObject(lines[0], lines[1],Integer.parseInt(lines[2]), lines[3]);
            pData.add(object);
        }

        return pData;
    }

    public ParentData makeObject(String rsID, String chromosome, int position, String snp) {
        ParentData p = new ParentData(rsID, chromosome, position, snp);
        return p;
    }

}
