/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RickyWahyudi
 */
public class TabelPegawai extends AbstractTableModel {

    private ArrayList<Pegawai> pegawe;

    public TabelPegawai(ArrayList<Pegawai> pegawai) {
        this.pegawe = pegawai;
    }

    public void add(Pegawai pegawai) {

    }

    @Override
    public int getRowCount() {
        return pegawe.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return pegawe.get(rowIndex).getID();
            case 1:
                return pegawe.get(rowIndex).getNama();
            case 2:
                return pegawe.get(rowIndex).isManager();
            default:
                return null;
        }
    }
    
     @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "Nama";
            case 2: return "Manager";
                default:return null;
        }
    }  

}
