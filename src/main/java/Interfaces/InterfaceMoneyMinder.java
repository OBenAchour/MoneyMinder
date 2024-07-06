package Interfaces;

import Models.Objectif;

import java.util.List;

public interface InterfaceMoneyMinder<T> {
    void add(T t);
    void delete(T t);
    void update(T t);
    List<T> getAll();
    List<T> getbyfilter(String column, String value);
    List<T> getbyid(int id);


}
