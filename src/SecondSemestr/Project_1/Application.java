package SecondSemestr.Project_1;

public class Application {
    public static void main(String[] args) {
        PairSequence ps = new PairSequence();
        for (int i = -5; i <= 5; i++) {
            ps.add(i, i * i);
        }
        System.out.println("All added elements: " + ps);
        System.out.println("Search x = -3 : " + ps.search(-3.0));
        System.out.println("Remove elements from -5 to -2");
        for (int i = -5; i <= -2; i++) {
            ps.remove(i);
        }
        System.out.println("Remaining elements: " + ps);
        System.out.println("Search closed x = -3 : " + ps.searchClosed(-3.0));
        System.out.println("Interpolate value in x = -5: " + ps.interpolate(-5.0));
        System.out.println("Interpolate value in x = -10: " + ps.interpolate(-10.0));
    }
}
