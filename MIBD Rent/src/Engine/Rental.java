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
    public Rental(String id,int biaya,String tanggal){
        this.idtrans=id;
        this.biaya=biaya;
        this.tanggal=tanggal;
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
}
