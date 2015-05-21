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
public class TabelPengembalian extends AbstractTableModel {
    ArrayList<Pengembalian> pengembalian;
    public TabelPengembalian(ArrayList<Pengembalian> pengembalian){
        this.pengembalian=pengembalian;
    }

    @Override
    public int getRowCount() {
        return this.pengembalian.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return pengembalian.get(rowIndex).getID();
            case 1:
                return pengembalian.get(rowIndex).getTrans();
            case 2:
                return pengembalian.get(rowIndex).getTanggal();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id pengembalian";
            case 1:
                return "id kendaraan";
            case 2:
                return "tanggal";

            default:
                return null;
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
}
