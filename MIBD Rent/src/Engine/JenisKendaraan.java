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
    public JenisKendaraan(int idJ,String namaJ){
        this.id=idJ;
        this.nama=namaJ;
    }
    
    public int getId(){
        return this.id;
    }
    public String getNama(){
        return this.nama;
    }
}
