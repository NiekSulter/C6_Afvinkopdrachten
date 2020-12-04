import java.io.*;
import java.util.*;

public class openFile {
    File file = new File("/Users/niek/Downloads/Homo_sapiens.gene_info");
    ArrayList<gene_ob> gene_list = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        long t1 = System.currentTimeMillis();
        openFile of = new openFile();
        of.readFile();
        of.compare();
        of.printObjectList();
        long t2 = System.currentTimeMillis();
        System.out.println("Total time: " + (t2 - t1) + "ms");

    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String row = "";
        reader.readLine();

        while ((row = reader.readLine()) != null) {
            String[] rowS = row.split("\t");

            gene_ob gene = new gene_ob(Integer.parseInt(rowS[1]), rowS[2], rowS[7]);
            gene_list.add(gene);

        }
    }

    public void printObjectList() {
        for (gene_ob i : gene_list) {
            System.out.println(i.getChromap());
        }
    }

    public void compare() {
        Collections.sort(gene_list, Comparator.comparing(gene_ob::getChromap));
    }
}
