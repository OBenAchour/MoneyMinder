import Models.Commentaire;
import Models.Sujet;
import Models.User;
import Servicesujet.CmtManager;
import Servicesujet.SujetManager;
import Utils.Myconnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection cx = Myconnection.getInstance().getCnx();
        if (cx != null) {
            System.out.println("Successfully connected to the database.");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        // Création de l'utilisateur
        User user = new User(1);

        // Instanciation du SujetManager
        SujetManager sujetManager = new SujetManager(cx);
        CmtManager cmtManager = new CmtManager(cx);


        // Création des sujets

        Sujet sujet1 = new Sujet(1, "important", "Contenu du sujet 1", user);
        Sujet sujet2 = new Sujet(2, "Titre du sujet 2", "Contenu du sujet 2", user);
        Sujet sujet3 = new Sujet(3, "Titre du sujet 3", "Contenu du sujet 3", user);

        // Test de la méthode add pour ajouter un sujet
        System.out.println("test de la methode add ");

        sujetManager.add(sujet1);
        sujetManager.add(sujet2);
        sujetManager.add(sujet3);

        // Test de la méthode update pour mettre à jour un sujet
        System.out.println("test de la methode update ");

        sujet1.setTitre("new title");
        sujet1.setSujet("new subject");
        sujetManager.update(sujet1);

        // Test de la méthode delete pour supprimer un sujet
        System.out.println("test de la methode delete");
        sujetManager.delete(sujet2);

        // Test de la méthode getAll pour afficher tous les sujets
        System.out.println("test de la methode get all ");

        List<Sujet> sujets = sujetManager.getAll();
        for (Sujet sujet : sujets) {
            System.out.println("ID: " + sujet.getId_suj() + ", Titre: " + sujet.getTitre() + ", Contenu: " + sujet.getSujet() + ", User ID: " + sujet.getId_user().getId());
        }

        // Test de la méthode getbyfilter pour afficher les sujets filtrés
        System.out.println("test de la methode getbyfilter ");

        List<Sujet> filteredSujets = sujetManager.getbyfilter();
        System.out.println("Sujets filtrés par ordre alphabétique");
        for (Sujet s : filteredSujets) {
            System.out.println(s);
        }

        // Test de la méthode getbyid pour afficher les sujets par ID

        System.out.println("test getbyid");
        List<Sujet> sujetsParId = sujetManager.getbyid();
        for (Sujet sujet : sujetsParId) {
            System.out.println("ID: " + sujet.getId_suj() + ", Titre: " + sujet.getTitre() + ", Contenu: " + sujet.getSujet() + ", User ID: " + sujet.getId_user().getId());
        }
        //////////////////////////////////////////////////////////////////////////////////

        Sujet sujet5 = new Sujet(170, "important", "Contenu du sujet 1", user);
        Sujet sujet6 = new Sujet(171, "Titre du sujet 2", "Contenu du sujet 2", user);
        // Test des méthodes de CmtManager
        System.out.println("Test des méthodes de CmtManager");

        // Créer des commentaires avec les sujets correspondants
        Commentaire commentaire1 = new Commentaire(1,"nouveau comm",sujet5);
        Commentaire commentaire2 = new Commentaire(2,"important",sujet6);


        // Ajouter des commentaires
        cmtManager.add(commentaire1);

        // Mettre à jour un commentaire
        commentaire1.setCmt("Nouveau commentaire sur le sujet 1");
        cmtManager.update(commentaire1);

        // Supprimer un commentaire
        cmtManager.delete(commentaire1);

        // Afficher tous les commentaires
        System.out.println("Tous les commentaires:");
        List<Commentaire> commentaires = cmtManager.getAll();
        for (Commentaire commentaire : commentaires) {
            System.out.println("ID: " + commentaire.getId_comm() + ", Commentaire: " + commentaire.getCmt() + ", Sujet ID: " + commentaire.getId_sujet().getId_suj());
        }
        // Test de la méthode getbyfilter pour afficher les commentaires filtrés
        System.out.println("test de la methode getbyfilter ");

        List<Commentaire> filteredCommentaires = cmtManager.getbyfilter();
        System.out.println("commentaires filtrés (commentaires contenant 'Important'):");
        for (Commentaire c : filteredCommentaires) {
            System.out.println(c);
        }
        System.out.println("test getbyid");
        List<Commentaire> commentairesParId = cmtManager.getbyid();
        for (Commentaire commentaire : commentairesParId) {
            System.out.println("ID: " + commentaire.getId_comm() + ", Commentaire: " + commentaire.getCmt() + ", Sujet ID: " + commentaire.getId_sujet().getId_suj());
        }







    }






}








