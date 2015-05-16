package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author RickyWahyudi
 */
import Engine.Kendaraan;
import Engine.Pegawai;
import Engine.Penyewa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Controller {
    ArrayList<Pegawai> pegawe;
    ArrayList<Kendaraan> kendara;
    ArrayList<Penyewa> pesewa;
    String connectionString;
    
    public Controller() {
        try
        {
            this.connectionString="jdbc:sqlserver://10.100.70.70;user=i13069;password=ryukishin;database=global";
            Connection conn = DriverManager.getConnection(this.connectionString);      
            Statement sta = conn.createStatement();
            String query = "select * from penduduk";
            ResultSet rs = sta.executeQuery(query);
            rs.close();
            conn.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
        } 
    }
    
    public void updatePegawai()
    {
        ArrayList<Pegawai> result=null;
        try
        {
            Connection conn = DriverManager.getConnection(this.connectionString);      
            Statement sta = conn.createStatement();
            String query = "select IdPegawai,Nama,IsManager from Pegawai";
            ResultSet rs = sta.executeQuery(query);
            result=new ArrayList<Pegawai>();
            while (rs.next()) 
            {
                String id=rs.getString("IdPegawai");
                String nama=rs.getString("Nama");
                boolean manager=rs.getBoolean("IsManager");
                result.add(new Pegawai(id,nama,manager));
            }
            rs.close();
            conn.close();
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        } 
       
    }
    public void updateKendaraan()
    {
        ArrayList<Kendaraan> result=null;
        try
        {
            Connection conn = DriverManager.getConnection(this.connectionString);      
            Statement sta = conn.createStatement();
            String query = "select IdKendaraan,noPolisi,kapasitas from Penyewa";
            ResultSet rs = sta.executeQuery(query);
            result=new ArrayList<Kendaraan>();
            while (rs.next()) 
            {
                String id=rs.getString("Idkendaraan");
                String nopol=rs.getString("noPolisi");
                int kapas=Integer.getInteger(rs.getString("kapasitas"));
                result.add(new Kendaraan(id,nopol,kapas));
            }
            rs.close();
            conn.close();
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        } 
       
    }
    
    public void updatePenyewa()
    {
        ArrayList<Penyewa> result=null;
        try
        {
            Connection conn = DriverManager.getConnection(this.connectionString);      
            Statement sta = conn.createStatement();
            String query = "select noKTP,nama,kommentar,alamat from Penyewa";
            ResultSet rs = sta.executeQuery(query);
            result=new ArrayList<Penyewa>();
            while (rs.next()) 
            {
                String id=rs.getString("noKTP");
                String nama=rs.getString("Nama");
                String alamat=rs.getString("alamat");
                String komentar="?";
                 komentar=rs.getString("komentar");
                result.add(new Penyewa(komentar,id,alamat,nama));
            }
            rs.close();
            conn.close();
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        } 
       
    }
    
    public void insetPegawai(String IdPegawai,String nama){
        try
        {
            Connection conn = DriverManager.getConnection(this.connectionString);      
            Statement sta = conn.createStatement();
            String query = "INSERTINTO [Pegawai] ([IdPegawai], [nama], [isManager]) VALUES ("+IdPegawai+","+nama+",false)";
            ResultSet rs = sta.executeQuery(query);
            
            
            rs.close();
            conn.close();
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        } 
    }
    
    public void insetKendaraan(String idKendaraan,String noPol,int kapas){
        try
        {
            Connection conn = DriverManager.getConnection(this.connectionString);      
            Statement sta = conn.createStatement();
            String query = "INSERTINTO [Kendaraan] ([idKendaraan], [noPol]) VALUES ("+idKendaraan+","+noPol+","+kapas+")";
            ResultSet rs = sta.executeQuery(query);
            
            
            rs.close();
            conn.close();
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        } 
    }
    
    public void insertPenyewa(String noKTP,String nama,String alamat,String komentar){
        try
        {
            Connection conn = DriverManager.getConnection(this.connectionString);      
            Statement sta = conn.createStatement();
            String query = "INSERTINTO [Penyewa] ([noKTP], [nama],[alamat],[komentar]) VALUES ("+noKTP+","+nama+","+alamat+","+komentar+")";
            ResultSet rs = sta.executeQuery(query);
            
            
            rs.close();
            conn.close();
            
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        } 
    }
    
}
