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
public class Rental {
    private String idtrans;
    private int biaya;
    private String tanggal;
    private Kendaraan car;
    private Penyewa pesewa;
    private String namaPenyewa;
    public Rental(String id,int biaya,String tanggal){
        this.idtrans=id;
        this.biaya=biaya;
        this.tanggal=tanggal;
    }
    public Rental(String id,Penyewa sewa,Kendaraan mobil ){
        this.idtrans=id;
        this.pesewa=sewa;
        this.car=mobil;
    }
    public String getId(){
        return this.idtrans;
    }
    public int getBiaya(){
        return this.biaya;
    }
    public String getTanggal(){
        return this.tanggal;
    }
    public String getKTP(){
        return this.pesewa.getNoKTP();
    }
    public String getidKendaraan(){
        return this.car.getId();
    }
    public String getJenisKendaraan(){
        return this.car.getjenisMerek();
    }
    public String getnoPol(){
        return this.car.getNoPol();
    }
    public String getNamaPenyewa(){
        
        return this.pesewa.getNama();
    }
    
}
