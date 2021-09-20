
/**
 * KingsPalindrome
 * @author Žilvinas Aleksa
 * @ID 1703749
 */

import java.util.*;

public class KingsPalindrome {
    static long[] reverse(long a[], int n) {
        long[] numbers = new long[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            numbers[j - 1] = a[i];
            j--;
        }
        return numbers;
    }

    public long countDigits(long n) {
        n = (long) (Math.log10(n) + 1);
        return n;
    }

    public void outputMagicSet(int n) {
        // initializing the lists of polyndromized numbers, additional helpful
        // variables, as general counter and magic set length counter
        long[] numbers = new long[n];
        long[] maxMagic = new long[n];
        long[] magicSet = new long[n];
        int maxLength = 1, count;

        // polyndromizing initial list
        numbers = palyndromize(n);

        // sorting and reversing the initial list
        Arrays.sort(numbers);
        numbers = reverse(numbers, n);

        for (int i = 0; i < n; i++) {

            // duplicating a number, which will be compared to others for the formation of
            // MagicSet
            long temporary = numbers[i];

            // filling the magic set with the first number and making the counter to 1
            count = 1;
            magicSet[count - 1] = numbers[i];

            for (int j = i + 1; j < n; j++) {

                // if needed removing the same number of figures on both ends from the greatest
                // number for comparison
                while (countDigits(temporary) > countDigits(numbers[j])) {
                    long temp2 = temporary % 10;
                    temp2 = temp2 * (long) Math.pow(10, countDigits(temporary) - 1);
                    temporary = temporary - temp2;
                    temporary /= 10;
                }

                // checking if there is a smaller palyndrome inside of greater palyndrome
                if (temporary == numbers[j]) {
                    count++;
                    magicSet[count - 1] = numbers[j];
                }
            }

            // checking if the newly formed magic set is greater than the previous one
            if (count > maxLength) {
                maxLength = count;
                maxMagic = Arrays.copyOf(magicSet, n);
            }
        }

        // checking if the max magic set only consists of 1 digit, if yes -> assigning
        // the greatest number X
        if (maxLength == 1) {
            maxMagic[0] = numbers[0];
        }

        // outputing the greatest magic set
        maxMagic = reverse(maxMagic, maxLength);
        for (long number : maxMagic) {
            if (maxMagic[maxMagic.length - 1] == number) {
                System.out.print(number);
            } else
                System.out.print(number + " ");
        }
    }

    public void searchLengthMagicSet(int n) {

        // initializing the lists of polyndromized numbers, additional helpful
        // variables, as general counter and magic set length counter
        long[] numbers = new long[n];
        int maxLength = 1, count;

        // polyndromizing initial list
        numbers = palyndromize(n);

        // sorting and reversing the initial list
        Arrays.sort(numbers);
        numbers = reverse(numbers, n);

        for (int i = 0; i < n; i++) {

            // duplicating a number, which will be compared to others for the formation of
            // MagicSet
            long temporary = numbers[i];

            // making the counter equal to one, because of 1 number is always in the
            // magicSet
            count = 1;
            for (int j = i + 1; j < n; j++) {

                // if needed removing the same number of figures on both ends from the greatest
                // number for comparison
                while (countDigits(temporary) > countDigits(numbers[j])) {
                    long temp2 = temporary % 10;
                    temp2 = temp2 * (long) Math.pow(10, countDigits(temporary) - 1);
                    temporary = temporary - temp2;
                    temporary /= 10;

                    // checking if there is a smaller palyndrome inside of greater palyndrome
                }
                if (temporary == numbers[j]) {
                    count++;
                }
            }

            // checking if the newly formed magic set is greater than the previous one
            if (count > maxLength) {
                maxLength = count;
            }
        }

        // outputting the result
        System.out.println(maxLength);
    }

    public long[] palyndromize(int n) {
        // initializing variables
        Scanner input = new Scanner(System.in);
        long numbers[] = new long[n];

        for (int i = 0; i < n; i++) {

            long digits[] = new long[17];
            int digcount = 0;
            boolean isHigher = false;
            boolean is9 = true;
            long finalNumber = 0;

            // input
            numbers[i] = input.nextLong();

            // checking if the number is one digit or not
            if (numbers[i] < 10) {
                digits[0] = numbers[i];
            }

            // decompozing a number to single digits
            while (numbers[i] > 0) {
                digits[digcount] = numbers[i] % 10;
                numbers[i] /= 10;
                digcount++;
            }
            int digcount2 = digcount;
            digcount2--;

            // checking if the last digit of the input number is larger than the first one,
            // the second is larger than the penultimate and so on
            for (int j = 0; j < digcount / 2; j++) {
                if (isHigher && digits[j] >= digits[digcount2]) {
                    isHigher = true;
                } else if (digits[j] > digits[digcount2]) {
                    isHigher = true;
                } else {
                    isHigher = false;
                }

                digits[j] = digits[digcount2];
                digcount2--;
            }

            // arranging the middle digit in the number, checking if it is 9 or not
            if (isHigher && 9 > digits[digcount / 2]) {
                finalNumber = finalNumber + (digits[digcount / 2] + 1) * (long) Math.pow(10, (digcount / 2));
                is9 = false;
            } else if (isHigher == false) {
                finalNumber = finalNumber + (digits[digcount / 2]) * (long) Math.pow(10, (digcount / 2));
                is9 = false;
            } else
                is9 = true;

            // giving a specific value to a counter
            digcount2 = digcount / 2;
            digcount2++;

            // Contructing a final polyndrome from the single digits
            for (int j = digcount / 2 - 1; j > -1; j--) {
                if (digits[j] < 9 && is9) {
                    digits[j]++;
                    finalNumber = finalNumber + digits[j] * (long) Math.pow(10, j);
                    finalNumber = finalNumber + digits[j] * (long) Math.pow(10, digcount2);
                    digcount2++;
                    is9 = false;
                } else if (is9 == false) {
                    finalNumber = finalNumber + digits[j] * (long) Math.pow(10, j);
                    finalNumber = finalNumber + digits[j] * (long) Math.pow(10, digcount2);
                    is9 = false;
                    digcount2++;
                } else
                    digcount2++;
            }

            numbers[i] = finalNumber;
        }

        // returning the value
        return numbers;
    }

    public static void main(String[] args) {

        //input
        Scanner input = new Scanner(System.in);
        int task = input.nextInt();
        int n = input.nextInt();

        //task 1
        if (task == 1) {
            long numbers[] = new long[n];
            numbers = (new KingsPalindrome()).palyndromize(n);
            for (long number : numbers) {
                if (numbers[numbers.length - 1] == number) {
                    System.out.print(number);
                } else
                    System.out.print(number + " ");
            }
        }

        //task 2
        if (task == 2) {
            (new KingsPalindrome()).searchLengthMagicSet(n);
        }

        //task 3
        if (task == 3) {
            (new KingsPalindrome()).outputMagicSet(n);
        }

    }
}
