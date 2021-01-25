import java.io.*;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class openFile {
    File file = new File("/Users/niek/Downloads/Homo_sapiens.gene_info");
    List<String> locusList = new LinkedList<>();
    List<List<String>> listList = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        openFile f1 = new openFile();
        f1.readFile();
        f1.makeLists();
        f1.addChromo();
    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String row = "";
        reader.readLine();

        while ((row = reader.readLine()) != null) {
            String[] rowSplit = row.split("\t");
            if (!rowSplit[7].equals("-")) {
                locusList.add(rowSplit[7]);
            }
        }
    }

    public void makeLists() {
        for (int i=0; i<25; i++) {
            List<String> chr = new LinkedList<>();
            listList.add(chr);
        }
    }

    public void addChromo () {
        Pattern pattern = Pattern.compile("(\\d*|X|Y)(q|p)(\\d*)(.(\\d*)|)");
        for (String i : locusList) {
            Matcher matcher = pattern.matcher(i);
            boolean match = matcher.find();

            if (match) {
                switch (matcher.group(1)) {
                    case "1" -> listList.get(0).add(i);
                    case "2" -> listList.get(1).add(i);
                    case "3" -> listList.get(2).add(i);
                    case "4" -> listList.get(3).add(i);
                    case "5" -> listList.get(4).add(i);
                    case "6" -> listList.get(5).add(i);
                    case "7" -> listList.get(6).add(i);
                    case "8" -> listList.get(7).add(i);
                    case "9" -> listList.get(8).add(i);
                    case "10" -> listList.get(9).add(i);
                    case "11" -> listList.get(10).add(i);
                    case "12" -> listList.get(11).add(i);
                    case "13" -> listList.get(12).add(i);
                    case "14" -> listList.get(13).add(i);
                    case "15" -> listList.get(14).add(i);
                    case "16" -> listList.get(15).add(i);
                    case "17" -> listList.get(16).add(i);
                    case "18" -> listList.get(17).add(i);
                    case "19" -> listList.get(18).add(i);
                    case "20" -> listList.get(19).add(i);
                    case "21" -> listList.get(20).add(i);
                    case "22" -> listList.get(21).add(i);
                    case "23" -> listList.get(22).add(i);
                    case "X" -> listList.get(23).add(i);
                    case "Y" -> listList.get(24).add(i);
                }
            }
        }
        System.out.println(listList.get(0));
    }

}
