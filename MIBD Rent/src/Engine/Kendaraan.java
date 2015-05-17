package Engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author i13008
 */
public class Kendaraan {
    private String idKendaraan;
    private String noPolisi;
    private int kapasitas;
    private int hargaSewa;
    private String idJenis;
    
    public Kendaraan(String id,String noPol,int kapas,int harga,String jenis){
        this.idKendaraan=id;
        this.noPolisi=noPol;
        this.kapasitas=kapas;
        this.hargaSewa=harga;
        this.idJenis=jenis;
    }
    
    public int getHarga(){
        return this.getHarga();
    }
    
    public int getKap(){
        return this.kapasitas;
    }
    
    public String getId(){
        return this.idKendaraan;
    }
    public String getNoPol(){
        return this.noPolisi;
    }
    public String getjenisMerek(){
        return this.idJenis;
    }
    
    
}
