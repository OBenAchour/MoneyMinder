//package Serveses;
//
//
//import Interfaces.InterfaceMoneyMinder;
//import Models.Assets;
//import MoneyMinder.Main;
//
//
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Serviceassets implements InterfaceMoneyMinder<Assets> {
//   private Connection connection;
//   public Serviceassets() {
//       connection = MyD
//   }
//    @Override
//    public void add(Assets assets) {
//
//
//        }
//
//
//    @Override
//    public void delete(Assets assets) {
//     this.assetsList.remove(assets);
//     System.out.println("asset deleted"+assets.getTitre());
//    }
//
//    @Override
//    public void update(Assets assets) {
//        for (Assets a : assetsList) {
//            if (a.getId_assets() == assets.getId_assets()) {
//                a.setTitre(assets.getTitre());
//                a.setPrix(assets.getPrix());
//
//                System.out.println("Asset updated: " + assets.getTitre());
//                return;
//            }
//        }
//    }
//
//    @Override
//    public List<Assets> getAll() {
//        return new ArrayList<>(assetsList);
//    }
//
//    @Override
//    public List<Assets> getbyfilter() {
//        return List.of();
//    }
//
//
//    @Override
//    public List<Assets> getbyid() {
//        return List.of();
//    }
//}
