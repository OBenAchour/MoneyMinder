package Models;

public class Quotedep {
    private int id;
    private String quote;

    //contructors


    public Quotedep(int id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public Quotedep(String quote) {
        this.quote = quote;
    }

    public Quotedep() {
    }

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    //to string


    @Override
    public String toString() {
        return "Quotedep{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
