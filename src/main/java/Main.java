import Interfaces.InterfaceMoneyMinder;
import Models.Catobj;
import Models.Objectif;
import Services.ObjectifService;
import Utils.Myconnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection cnx = Myconnection.getInstance().getCnx();
        Catobj catobj = new Catobj(1, "Voyage"); // Assurez-vous que cet ID (1) existe dans la table `categorieobj`
       // Objectif obj1 = new Objectif(9, 2000, "Djerba", "haha", 5000.0, 5.0, catobj, 5); // Utilisation du bon constructeur
        Objectif obj = new Objectif(9, 2000, "Espagne", "haha", 5000.0, 5.0, catobj, 5); // Utilisation du bon constructeur
        Objectif o2 = new Objectif(10,9, 2000, "Djerba", "haha", 5000.0, 5.0, catobj, 5);
        InterfaceMoneyMinder<Objectif> os = new ObjectifService();
        //os.add(obj);
        //os.delete(o1);
        //os.update(o2);
        //System.out.println(os.getAll());
        System.out.println(os.getbyfilter());
    }
}
