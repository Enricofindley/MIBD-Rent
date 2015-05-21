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
public class Pengembalian {
    private String id;
    private String idken;
    private String tanggal;
    public Pengembalian(String id,String trans,String tanggal){
        this.id=id;
        this.idken=trans;
        this.tanggal=tanggal;
        
    }
    public String getID(){
        return this.id;
    }
    public String getTrans(){
        return this.idken;
    }
    public String getTanggal(){
        return this.tanggal;
    }
    
}
