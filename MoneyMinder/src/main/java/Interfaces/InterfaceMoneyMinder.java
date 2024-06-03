package Interfaces;

import java.util.List;

public interface InterfaceMoneyMinder <T>{

    //CRUD
    public void add(T t);
    public void delete(T t);
    public void update(T t);
    public List<T> getAll();
    public List<T> getbyfilter(String t);
    public List<T> getbyid(int t);
}
