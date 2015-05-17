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
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return mobil.get(rowIndex).getId();
            case 1:
                return mobil.get(rowIndex).getNoPol();
            case 2:
                return mobil.get(rowIndex).getNoPol();
            case 3:
                return mobil.get(rowIndex).getNoPol();
            case 4:
                return mobil.get(rowIndex).getKap();
            case 5:
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
                return "Jenis";
            case 2:
                return "Merek";

            case 3:
                return "NoPol";
            case 4:
                return "Kapasitas";
            case 5:
                return "HargaSewa";
            default:
                return null;
        }
    }

}
