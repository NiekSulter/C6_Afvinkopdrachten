import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Locus_sort {

    ArrayList<gene_ob> pArm = new ArrayList<>();
    ArrayList<gene_ob> qArm = new ArrayList<>();

    public void printLocus(ArrayList<gene_ob> list) {
        for (gene_ob i : list) {
            String loc = i.getChromap();
            System.out.println(loc);
        }
    }

    public void armSort(ArrayList<gene_ob> list) {
        for (gene_ob i : list) {
            Pattern pattern = Pattern.compile("(\\d*|X|Y)(q|p)(\\d*)(.(\\d*)|)");
            Matcher matcher = pattern.matcher(i.chromap);
            boolean match = matcher.find();

            if(match) {
                if(matcher.group(2).equals("q")) {
                    System.out.println("Dit gen ligt op de Q arm: " + matcher.group(0));
                    qArm.add(i);

                } else if (matcher.group(2).equals("p")) {
                    System.out.println("Dit gen ligt op de P arm: " + matcher.group(0));
                    pArm.add(i);
                }
            }
        }
    }

    public void sortArm() {
        HashMap<Integer, String> tempHash = new HashMap<>();
        for (gene_ob i : qArm) {
            Pattern pattern = Pattern.compile("(\\d*|X|Y)(q|p)(\\d*)(.(\\d*)|)");
            Matcher matcher = pattern.matcher(i.chromap);
            boolean match = matcher.find();
            try {
                if (match) {
                    String mg = matcher.group(1);
                    mg.strip();
                    if (mg.equals("X")) {
                        mg = mg.replace("X", "23");
                    } else if (mg.equals("Y")) {
                        mg = mg.replace("Y", "24");
                    } else if (mg.matches("\\d*")) {
                        mg = mg;
                    }
                    tempHash.put(Integer.parseInt(mg), i.chromap);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }

        System.out.println("Unsorted " + tempHash);

        Map<Integer, String> tempTree = new TreeMap<>(tempHash);

        System.out.println("Sorted " + tempTree);

    }

}
