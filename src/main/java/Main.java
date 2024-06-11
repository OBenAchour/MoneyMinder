import Interfaces.InterfaceMoneyMinder;
import Models.Catobj;
import Models.Objectif;
import Services.ObjectifService;
import Utils.Myconnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection cnx = Myconnection.getInstance().getCnx();
        Catobj catobj = new Catobj(2, "Voyage");
       // Objectif obj1 = new Objectif(9, 2000, "Djerba", "haha", 5000.0, 5.0, catobj, 5);
        Objectif obj = new Objectif( 2, "Djerba", "haha", 2650.0, 5.0, catobj, 5);
        Objectif o1 = new Objectif(19,6, "n", "haha", 5000.0, 5.0, catobj, 5);
        InterfaceMoneyMinder<Objectif> os = new ObjectifService();

        ObjectifService objs = new ObjectifService();
       //os.add(obj);
        //os.delete(o1);
       // os.update(o1);
        //System.out.println(os.getAll());
        //System.out.println(os.getbyfilter("titre","Djerba ")); ///kadhe w kadhe
        //System.out.println(os.getbyid(10));
        //objs.calculerEcheance();

    }
}
