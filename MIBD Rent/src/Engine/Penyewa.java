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
public class Penyewa {
    
    private String comment;
    private String nama;
    private String alamat;
    private String noKTP;
    public Penyewa(String comment, String nama, String alamat, String noKTP) {
        this.comment = comment;
        this.nama = nama;
        this.alamat = alamat;
        this.noKTP = noKTP;
    }
    
   

    public String getComment() {
        return comment;
    }

    public String getNama() {
        return nama;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoKTP() {
        return noKTP;
    }
    

    
    
    
}
