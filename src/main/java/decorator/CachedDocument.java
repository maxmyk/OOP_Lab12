package decorator;

public class CachedDocument extends SmartDocument{
    public String gcsPath = super.gcsPath;
    public CachedDocument(String gcsPath) {
        super(gcsPath);
    }
    private final DatabaseHandler db = new DatabaseHandler();
    @Override
    public String parse() {
        if (db.checkData(gcsPath)){
            System.out.println("I've seen that before! -_-");
            return new String(db.getData(gcsPath));
        }
        else{
            System.out.println(gcsPath);
            System.out.println(super.gcsPath);
            String result = super.parse();
            db.insertData(gcsPath, result);
            return result;
        }
    }
}
