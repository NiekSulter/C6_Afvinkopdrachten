import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class compareLists {

    List<String> al1 = new ArrayList<>();
    List<String> al2 = new ArrayList<>();
    List<String> al3 = new ArrayList<>();
    List<String> alRet = new ArrayList<>();

    public compareLists(String input1, String input2, String input3, int index) {
        makeLists(input1, input2, input3);
        compare(index);
    }

    public void makeLists(String input1, String input2, String input3) {
        String[] ar1 = input1.split("\n");
        String[] ar2 = input2.split("\n");
        String[] ar3 = input3.split("\n");

        Collections.addAll(al1, ar1);
        Collections.addAll(al2, ar2);
        Collections.addAll(al3, ar3);

    }

    public void compare(int index) {
        if (index == 0) {
            al1.retainAll(al2);
            alRet = al1;
        } else if (index == 1) {
            al1.retainAll(al3);
            alRet = al1;
        } else if (index == 2) {
            al2.retainAll(al3);
            alRet = al2;
        } else {
            alRet.add("No elements found to compare");
        }
    }
}
