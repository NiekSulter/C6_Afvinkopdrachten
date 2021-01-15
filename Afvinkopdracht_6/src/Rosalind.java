import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rosalind {

    File file = new File("Afvinkopdracht_6/src/dataset.txt");

    public static void main(String[] args) throws IOException {
        Rosalind rl = new Rosalind();
        HashMap<String, String> dSet = rl.readFile();
        HashMap<String, String> dSet2 = rl.readFile();
        rl.compareLastChar(dSet, dSet2);
    }

    public HashMap readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String row = "";
        HashMap<String, String> dSet = new HashMap<>();
        String header = "";
        String seq = "";

        while ((row = reader.readLine()) != null) {

            //System.out.println(row);
            if (row.startsWith(">")) {
                header = row;
            } else {
                seq = row;
                dSet.put(header, seq);
            }
        }
        return dSet;
    }

    public void compareLastChar(HashMap<String, String> dSet, HashMap<String, String> dSet2) {
        ArrayList<String> adjList = new ArrayList<>();
        for (Map.Entry<String, String> entry : dSet.entrySet()) {
            for (Map.Entry<String, String> entry2 : dSet2.entrySet()) {
                if (entry.getValue().equals(entry2.getValue())) {
                    continue;
                } else {
                    if (entry.getValue().endsWith(String.valueOf(entry2.getValue().charAt(entry2.getValue().length() - 1)))) {
                        System.out.println(entry.getKey() + " " + entry.getValue() + " " + entry2.getKey() + " " + entry2.getValue());
                        adjList.add(entry.getKey());
                        adjList.add(entry2.getKey());
                    }
                }
            }
        }
        System.out.println(adjList);
    }
}
