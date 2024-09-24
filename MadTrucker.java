
/**
 * MadTrucker
 * @author Žilvinas Aleksa
 * @ID 1703749
 */

import java.util.*;

public class MadTrucker {
    public static Scanner input = new Scanner(System.in);
    public static TreeMap<Integer, Integer> noStop = new TreeMap<Integer, Integer>();
    public static LinkedHashMap<Integer, Integer> cansUsed = new LinkedHashMap<Integer, Integer>();

    //initializing public variable size to determine the size of cans (line 57 reads the number)
    public static int size;

    public void fillTruck(TreeMap<Integer, Integer> cansRange, int distance, LinkedHashMap<Integer, Integer> output) {
        if (!cansRange.isEmpty() && output.size() != size){
        for (int i : cansRange.keySet()) {
            //breaking the loop to save time if one answer has already been found
            if (size == cansUsed.size()) {
                break;
            }

            //creating new temporary variables to avoid getting oncurrentmodificationexception
            LinkedHashMap<Integer, Integer> out = new LinkedHashMap<Integer, Integer>();
            TreeMap<Integer, Integer> cansRange2 = new TreeMap<Integer, Integer>();

            //checking wether the can can be used
            if (!noStop.containsValue(distance + i)) {
                //copying all of the cans used at that moment
                out.putAll(output);

                //putting can that has just been found into temporary output
                out.put(cansRange.get(i), i);

                //copying the cans that still can be used
                cansRange2.putAll(cansRange);

                //removing can that has just been found
                cansRange2.remove(i);

                //repeating the proccess
                fillTruck(cansRange2, distance + i, out);
            }
        }
    }
        //if the answer is found, putting it in the public variable
        else {
            cansUsed.putAll(output);
        }
    }

    //function that reads the input
    public TreeMap<Integer, Integer> readInput(TreeMap<Integer, Integer> cansRange) {
        size = input.nextInt();
        for (int i = 0; i < size; i++) {
            cansRange.put(input.nextInt(), i);
        }

        for (int i = 0; i < size - 1; i++) {
            noStop.put(i, input.nextInt());
        }
        return cansRange;
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> cansRange = new TreeMap<Integer, Integer>();
        LinkedHashMap<Integer, Integer> out = new LinkedHashMap<Integer, Integer>();
        MadTrucker truck = new MadTrucker();

        //reading input
        cansRange = truck.readInput(cansRange);

        //filling up a truck
        truck.fillTruck(cansRange, 0, out);

        /*
        when the program has found one answer, the amount of initial cans will be the same as amount of used cans
        Therefore, size is being diminished to found out the last element of the Cans
        This is crucial, because the last element of the cans has to be printed without white space and with the endline
        */
        for (int i : cansUsed.keySet()) {
            size--;
            if (size > 0) {
                System.out.print(i + " ");
            } else
                System.out.println(i);
        }
        input.close();
    }
}

/*
1st task:
40 42 6 66 96 86 59 8 83 12 18 90 26 98 9 48 55 84 24 49 70 77 27 85 41 63 57
21 38 22 14 11 94 73 47 7 82 93 64 89 54 2062 1994 1967 1963 1897 1872 1840
1821 1806 1800 1754 1598 1576 1399 1391 1263 1252 1229 1166 1098 1001 997 973
770 744 738 687 667 565 545 388 356 320 274 235 177 173 110 78 1071 1407 930
40 942 276 147 670 332 333 255 1263 916 1484 2062 1177 724 360 1669 1145 523
278

2nd task:
3 2 4 6 4 8
*/