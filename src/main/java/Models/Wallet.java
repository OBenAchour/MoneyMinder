package Models;

public class Wallet {
    int id_wallet;
    int montant;
    Transaction id_trans;

    public Wallet(int id_wallet, int montant, Transaction id_trans) {
        this.id_wallet = id_wallet;
        this.montant = montant;
        this.id_trans = id_trans;
    }

    public Wallet(int montant, Transaction id_trans) {
        this.montant = montant;
        this.id_trans = id_trans;
    }

    public Wallet() {
    }

    public int getId_wallet() {
        return id_wallet;
    }

    public void setId_wallet(int id_wallet) {
        this.id_wallet = id_wallet;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Transaction getId_trans() {
        return id_trans;
    }

    public void setId_trans(Transaction id_trans) {
        this.id_trans = id_trans;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id_wallet=" + id_wallet +
                ", montant=" + montant +
                ", id_trans=" + id_trans +
                '}';
    }
}
