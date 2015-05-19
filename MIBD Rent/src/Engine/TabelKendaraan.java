/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RickyWahyudi
 */
public class TabelKendaraan extends AbstractTableModel {

    ArrayList<Kendaraan> mobil;

    public TabelKendaraan(ArrayList<Kendaraan> kendaraan) {
        this.mobil = kendaraan;
    }

    @Override
    public int getRowCount() {
        return this.mobil.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return mobil.get(rowIndex).getId();
            case 1:
                return mobil.get(rowIndex).getjenisMerek();
      
            case 2:
                return mobil.get(rowIndex).getNoPol();
            case 3:
                return mobil.get(rowIndex).getKap();
            case 4:
                return mobil.get(rowIndex).getHarga();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
           
            case 1:
                return "Merek";

            case 2:
                return "NoPol";
            case 3:
                return "Kapasitas";
            case 4:
                return "HargaSewa";
            default:
                return null;
        }
    }

}
