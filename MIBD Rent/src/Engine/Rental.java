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
    private String idKenradaraan;
    private String idpegawai;
    private Kendaraan car;
    private String noKTP;
    private Penyewa pesewa;
    private String namaPenyewa;
    private String jenisK;
    public Rental(String id,int biaya,String tanggal){
        this.idtrans=id;
        this.biaya=biaya;
        this.tanggal=tanggal;
    }
    public Rental(Penyewa sewa,Kendaraan mobil ){
        this.pesewa=sewa;
        this.noKTP=sewa.getNoKTP();
        this.idKenradaraan=car.getId();
        this.car=mobil;
        this.idKenradaraan=mobil.getId();
        this.namaPenyewa=sewa.getNama();
        this.jenisK=car.getjenisMerek();
        
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
        return this.noKTP;
    }
    public int getidKendaraan(){
        return this.biaya;
    }
    public String getnoPol(){
        return this.idpegawai;
    }
    public String getNamaPenyewa(){
        
        return this.namaPenyewa;
    }
    public String getMrek(){
     return this.jenisK;   
    }
}
