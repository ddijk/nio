package nl.dijkrosoft.heinz;
public class Util {


    public static int transmogrify(final int i) {
        if (Character.isLetter(i)) {
            int x = i ^ ' ';
            System.out.println(Character.valueOf((char) x));
            return x;
        } else {

            System.out.println("input is not alphabetic: " + i);
            return i;
        }
    }
}
