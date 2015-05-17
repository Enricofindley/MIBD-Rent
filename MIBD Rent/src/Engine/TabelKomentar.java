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
public class TabelKomentar extends AbstractTableModel{
    private ArrayList<Penyewa> penyewa;
    public TabelKomentar(ArrayList<Penyewa> pensewa){
        this.penyewa=pensewa;
    }
    @Override
    public int getRowCount() {
        return this.penyewa.size();
    }

    @Override
    public int getColumnCount() {
        
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return penyewa.get(rowIndex).getNoKTP();
            case 1:
                return penyewa.get(rowIndex).getComment();
            
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "no ktp";
            case 1:
                return "komentar";
            
            default:
                return null;
        }
    }
    
}
