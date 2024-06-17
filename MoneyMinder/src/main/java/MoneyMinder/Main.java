package MoneyMinder;

import Models.Portefeuille_actions;
import Models.User;
import Services.PortefeuilleActionService;
import Utils.MyConnexion;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        PortefeuilleActionService service = new PortefeuilleActionService();

        // Créer un utilisateur
        User user = new User(1);

        // Créer un portefeuille
        Portefeuille_actions portefeuille1 = new Portefeuille_actions(7, new Date(), 2000f, user);
        Portefeuille_actions portefeuille = new Portefeuille_actions(1, new Date(), 1500f, user);
        // Ajouter le portefeuille
        System.out.println("Ajout d'un portefeuille:");
        service.add(portefeuille);

        // Mettre à jour le portefeuille
        System.out.println("\nMise à jour du portefeuille:");
        service.update(portefeuille1);

        // Obtenir tous les portefeuilles
        System.out.println("\nListe des portefeuilles:");
        List<Portefeuille_actions> portefeuilles = service.getAll();
     for (Portefeuille_actions pf : portefeuilles) {
            System.out.println(pf);
        }

//        // Supprimer le portefeuille
        System.out.println("\nSuppression du portefeuille:");
        service.delete(portefeuille);

//        // Afficher à nouveau tous les portefeuilles pour confirmer la suppression
        System.out.println("\nListe des portefeuilles après suppression:");
        portefeuilles = service.getAll();
        for (Portefeuille_actions pf : portefeuilles) {
            System.out.println(pf);
        }
 }
}

