import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hashmap {
    Map<String, String> map = new HashMap<>();
    String[] Namen = {"Alanine", "Arginine", "Asparagine", "Aspartic acid", "Cysteine", "Glutamine", "Glutamic acid",
            "Glycine", "Histidine", "Isoleucine", "Leucine", "Lysine", "Methionine", "Phenylalanine", "Proline",
            "Serine", "Threonine", "Tryptophan", "Tyrosine", "Valine"};
    String[] eenLetter = {"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F",
            "P", "S", "T", "W", "Y", "V"};
    String[] drieLetter = {"Ala", "Arg", "Asn", "Asp", "Cys", "Gln", "Glu", "Gly", "His", "Ile", "Leu", "Lys",
            "Met", "Phe", "Pro", "Ser", "Thr", "Trp", "Tyr", "Val"};

    public static void main(String[] args) {
        Hashmap h1 = new Hashmap();
        String input = h1.userInput();
        int len = input.length();
        h1.mapGen(len);
        h1.hashPrintSingle(input);
    }

    public void mapGen(int input) {
        if (input == 3) {
            for (int i = 0; i < drieLetter.length; ) {
                map.put(drieLetter[i], (Namen[i] + ", " + eenLetter[i]));
                i++;
            }
        } else if (input == 1) {
            for (int i = 0; i < eenLetter.length; ) {
                map.put(eenLetter[i], (Namen[i] + ", " + drieLetter[i]));
                i++;
            }
        } else {
            for (int i = 0; i < Namen.length; ) {
                map.put(Namen[i], (drieLetter[i] + ", " + eenLetter[i]));
                i++;
            }
        }
    }

    public void hashPrintAll() {
        for (String i : map.keySet()) {
            System.out.println(i + " " + map.get(i));
        }
    }

    public void hashPrintSingle(String key) {
        System.out.println(key + " " + map.get(key));
    }

    public String userInput() {
        Scanner object = new Scanner(System.in);
        System.out.println("Voer in wat je wilt weten: ");

        String uI = object.nextLine();
        return uI;

    }

}
