import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class shellsort {

    static void printArray(ArrayList<Integer> array) {
        for (int j : array) {
            System.out.println(j + "");
        }
    }

    void sort(ArrayList<Integer> array) {
        int n = array.size();

        for (int gap = n/2; gap > 0; gap /= 2)
        {

            for (int i = gap; i < n; i += 1)
            {

                int temp = array.get(i);

                int j;
                for (j = i; j >= gap && array.get(j - gap) > temp; j -= gap)
                    array.set(j, (array.get(j - gap)));

                    array.set(j, temp);

            }
        }

    }

    ArrayList<Integer> randomArray(int aantal_getallen) {
        Random rand = new Random();
        ArrayList<Integer> randomList = new ArrayList<>();

        for (int i=0; i<aantal_getallen; i++) {
            randomList.add((int) (Math.random() * 1000000000));
        }

        return randomList;
    }

    public static void main(String[] args) {

        for (int t = 0; t < 8; t++) {
            int aantal_getallen = (int) (Math.random() * 9000000) + 1000000;
            System.out.println(aantal_getallen);
            Long t1 = System.currentTimeMillis();
            shellsort ob = new shellsort();
            ArrayList list = ob.randomArray(aantal_getallen);
            //printArray(list);
            ob.sort(list);

            //System.out.println("Na het sorteren");
            //printArray(list);
            Long t2 = System.currentTimeMillis();
            System.out.println("Duur van het programma: " + (t2 - t1) + " miliseconden");
        }
    }
}
