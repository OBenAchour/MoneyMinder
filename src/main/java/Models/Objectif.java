package Models;

public class Objectif {
    int id_obj, mois;
    String titre, commentaire;
    Double montant_globale, echeance;
    Catobj catobj;
    int id_wallet;

    public Objectif() {}

    public Objectif(String titre, Double montantGlobale, int mois, String commentaire)
    {
        this.titre = titre;
        this.montant_globale = montantGlobale;
        this.mois = mois;
        this.commentaire = commentaire;
    }

    public Objectif(int id_obj, int mois, String titre, String commentaire, Double montant_globale, Double echeance, Catobj catobj, int id_wallet) {
        this.id_obj = id_obj;
        this.mois = mois;
        this.titre = titre;
        this.commentaire = commentaire;
        this.montant_globale = montant_globale;
        this.echeance = echeance;
        this.catobj = catobj;
        this.id_wallet = id_wallet;
    }

    public Objectif( int mois, String titre, String commentaire, Double montant_globale, Double echeance) {
        this.id_obj = id_obj;
        this.mois = mois;
        this.titre = titre;
        this.commentaire = commentaire;
        this.montant_globale = montant_globale;
        this.echeance = echeance;
        this.catobj = catobj;
        this.id_wallet = id_wallet;
    }

    public int getId_obj() {
        return id_obj;
    }

    public void setId_obj(int id_obj) {
        this.id_obj = id_obj;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Double getMontant_globale() {
        return montant_globale;
    }

    public void setMontant_globale(Double montant_globale) {
        this.montant_globale = montant_globale;
    }

    public Double getEcheance() {
        return echeance;
    }

    public void setEcheance(Double echeance) {
        this.echeance = echeance;
    }

    public Catobj getCatobj() {
        return catobj;
    }

    public void setCatobj(Catobj catobj) {
        this.catobj = catobj;
    }

    public int getId_wallet() {
        return id_wallet;
    }

    public void setId_wallet(int id_wallet) {
        this.id_wallet = id_wallet;
    }

    @Override
    public String toString() {
        return "Objectif{" +
                "id_obj=" + id_obj +
                ", mois=" + mois +
                ", titre='" + titre + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", montant_globale=" + montant_globale +
                ", echeance=" + echeance +
                ", catobj=" + catobj +
                ", id_wallet=" + id_wallet +
                '}';
    }
}
