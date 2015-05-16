package Engine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RickyWahyudi
 */
public class Pegawai {
    private String IdPegawai;
    private String nama;
    private boolean isManager;
    
    public Pegawai(String id,String name,boolean manager){
        this.IdPegawai=id;
        this.nama=name;
        this.isManager=manager;
    }
}
