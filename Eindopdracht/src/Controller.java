import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public static void main(String[] args) throws IOException {
        Controller c = new Controller();
        c.start();

    }

    public void start() throws IOException {
        String[] parentPaths = {
                "Eindopdracht/resources/openSNP/8416.23andme.6745",
                "Eindopdracht/resources/openSNP/9369.23andme.7677"};

        //LinkedList<Variant> vList = OpenVS.readFile();
        HashMap<Integer, Variant> vMap = OpenVS.readFile();
        HashMap<String, HashMap<Integer, ParentData>> hMap = OpenPD.createMap(parentPaths);

        ArrayList<Match> dupes = Compare.checkDupes(hMap.get("8416"), hMap.get("9369"));


        int x = 0;

        System.out.println(vMap.keySet());

        for (Match m : dupes) {
            if (vMap.containsKey(m.getRSID())) {
                Pattern pattern = Pattern.compile(".*[ " + (m.getParent1Nuclo() + m.getParent2Nuclo()) + "].*");
                Matcher matcher = pattern.matcher(vMap.get(m.getRSID()).getSNP());
                if (matcher.find()) {
                    //System.out.println("Parent 1 Nucl " + m.getParent1Nuclo() + " Parent 2 Nucl " + m.getParent2Nuclo());
                    //System.out.println(vMap.get(m.getRSID()).getSNP());
                    m.setVarNuclo(vMap.get(m.getRSID()).getSNP());
                    m.setVariantMatch(true);
                    x += 1;
                }


            }
        }

        System.out.println(x);
    }
}
