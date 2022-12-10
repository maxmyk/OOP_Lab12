package decorator;

public class TimedDocument extends CachedDocument{

    public TimedDocument(String gcsPath) {
        super(gcsPath);
    }

    @Override
    public String parse() {
        long start = System.nanoTime();
        String ans = super.parse();
        long end = System.nanoTime();
        long duration = (end - start);
        return ans + "\nDuration (nanoseconds): "+duration;
    }
}
