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
                return rent.get(rowIndex).getKTP();
            case 2:
                return rent.get(rowIndex).getNamaPenyewa();
            case 3:
                return rent.get(rowIndex).getidKendaraan();
            case 4:
                return rent.get(rowIndex).getJenisKendaraan();
            case 5:
                return rent.get(rowIndex).getnoPol();

            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID transaksi";
            case 1:
                return "ID penyewa";
            case 2:
                return "Nama penyewa";
            case 3:
                return "id kendaraan";
            case 4:
                return "jenis kendaraan";
            case 5:
                return "nopol";

            default:
                return null;
        }
    }
}
