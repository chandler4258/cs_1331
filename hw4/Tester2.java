import java.util.Collection;
import java.util.Iterator;
import java.util.HashSet;

public class Tester2 {
    public static void main(String[] args) {

        HashSet s2 = new HashSet();
        s2.add(new Square("a1"));
        s2.add(new Square("b2"));
        s2.add(new Square("c3"));
        s2.add(new Square("d9"));
        s2.add(null);
        SquareSet s = new SquareSet(s2);

        for (Square square : s) {
            System.out.println(square);
        }
    }
}