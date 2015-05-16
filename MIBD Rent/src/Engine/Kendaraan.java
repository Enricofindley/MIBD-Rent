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
    
    public Kendaraan(String id,String noPol,int kapas){
        this.idKendaraan=id;
        this.noPolisi=noPol;
        this.kapasitas=kapas;
    }
}
