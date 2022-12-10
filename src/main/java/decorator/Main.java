package decorator;

public class Main {
    public static void main(String[] args) {
        Document document = new CachedDocument("gs://oop-course/Geico-2021.png");
        Document document1 = new TimedDocument("gs://oop-course/Geico-2021.png");
//        document = TimedDocument(document);
//        document = CashedDocument(document);
        String ans = document.parse();
        System.out.println(ans);
        String ans1 = document1.parse();
        System.out.println(ans1);
    }
}
