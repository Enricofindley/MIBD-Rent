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
public class TabelSewa extends AbstractTableModel {
    ArrayList<Rental> rent;

    public TabelSewa(ArrayList<Rental> rental) {
        this.rent = rental;
    }

    @Override
    public int getRowCount() {
        return this.rent.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rent.get(rowIndex).getId();
            case 1:
                return rent.get(rowIndex).getBiaya();
            case 2:
                return rent.get(rowIndex).getTanggal();

            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id transaksi";
            case 1:
                return "Pemasukan";
            case 2:
                return "tanggal";

            default:
                return null;
        }
    }
}
