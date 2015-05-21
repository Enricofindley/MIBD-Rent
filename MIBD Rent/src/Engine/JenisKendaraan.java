/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

/**
 *
 * @author RickyWahyudi
 */
public class JenisKendaraan {
    private int id;
    private String nama;
    private int kapasitas;
    private int harga;
    public JenisKendaraan(int idJ,String namaJ, int jkapasitas,int harga){
        this.id=idJ;
        this.nama=namaJ;
        this.kapasitas=jkapasitas;
        this.harga=harga;
    }
    
    public int getId(){
        return this.id;
    }
    public String getNama(){
        return this.nama;
    }
    public int kapastitas(){
        return this.kapasitas;
    }
    public int getharga(){
        return this.harga;
    }
}
