package Models;

public class Quoterev {

    private int id;
    private String quote;

    //constructors


    public Quoterev(int id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public Quoterev(String quote) {
        this.quote = quote;
    }

    public Quoterev() {
    }

    // to string


    @Override
    public String toString() {
        return "Quoterev{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
