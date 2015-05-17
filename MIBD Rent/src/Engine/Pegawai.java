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
    private String isManager;

    public Pegawai(String id, String name, String manager) {
        this.IdPegawai = id;
        this.nama = name;
        this.isManager = manager;
    }

    public String getID() {
        return IdPegawai;
    }
    public String getNama() {
        return nama;
    }
    public String isManager() {
        String result=String.valueOf(isManager);
        return result;
    }
}
