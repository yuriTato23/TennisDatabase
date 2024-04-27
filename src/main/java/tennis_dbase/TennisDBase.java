/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tennis_dbase;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Lisset Ripoll
 * @param <T>
 */
public interface TennisDBase<T> extends Serializable{
    List<T> listAll() throws SQLException;
    int update(T t) throws SQLException;
    int delete(T t) throws SQLException;
    int insert(T t) throws SQLException;
}
