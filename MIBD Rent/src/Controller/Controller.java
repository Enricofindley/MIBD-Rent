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
import Engine.Rental;
import Engine.TabelKomentar;
import Engine.TabelPegawai;
import Engine.TabelRental;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controller {

    ArrayList<Pegawai> pegawe;
    ArrayList<Kendaraan> kendaraan;
    ArrayList<Penyewa> pesewa;
     ArrayList<Rental> rent;
    
    String url;

    public Controller() {
        url = "jdbc:sqlserver://10.100.70.70;user=i13069;password=ryukishin;database=i13069";
        pegawe=new ArrayList<Pegawai>();
        kendaraan = new ArrayList<Kendaraan>();
        pesewa = new ArrayList<Penyewa>();
        rent=new ArrayList<Rental>();
        this.updateRental();
        this.updatePenyewa();
        this.updatePegawai();
        
    }

    

    public void updateKendaraan() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select Kendaraan.ID_Kendaraan,Kendaraan.noPol,Kendaraan.Kapasitas,Kendaraan.Harga,Jenis_Kendaraan.id_Jenis,Jenis_Kendaraan.Nama_Jenis from Kendaraan join Jenis_Kendaraan on Kendaraan.id_jenis=Jenis_Kendaraan.ID_Jenis";
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("ID_Kendaraan");
                String nopol = rs.getString("noPol");
                int kapas = Integer.getInteger(rs.getString("Kapasitas"));
                int harga=Integer.getInteger(rs.getString("Harga"));
                String jenis= rs.getString("Nama_Jenis");
                Kendaraan k=new Kendaraan(id,nopol,kapas,harga,jenis);
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void updatePenyewa() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select no_KTP,nama,komentar,alamat from Penyewa";
            ResultSet rs = sta.executeQuery(query);
            
            while (rs.next()) {
                String id = rs.getString("no_KTP");
                String nama = rs.getString("Nama");
                String alamat = rs.getString("alamat");
                String komentar = rs.getString("komentar");
                komentar = rs.getString("komentar");
                Penyewa sewa=new Penyewa(id,nama,alamat,komentar);
                pesewa.add(sewa);
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }
    
    public void updateRental() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select id_transaksi,biaya,tanggal from Penyewa";
            ResultSet rs = sta.executeQuery(query);
            
            while (rs.next()) {
                String id = rs.getString("no_KTP");
                int biaya = rs.getInt("biaya");
                String tanggal = rs.getString("tanggal");
                Rental sewa=new Rental(id,biaya,tanggal);
                rent.add(sewa);
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void insertPegawai(String IdPegawai, String nama) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "INSERTINTO [Pegawai] ([IdPegawai], [nama], [isManager]) VALUES (" + IdPegawai + "," + nama + ",false)";
            ResultSet rs = sta.executeQuery(query);

            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertKendaraan(String idKendaraan, String noPol, int kapas) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "INSERTINTO [Kendaraan] ([idKendaraan], [noPol]) VALUES (" + idKendaraan + "," + noPol + "," + kapas + ")";
            ResultSet rs = sta.executeQuery(query);

            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertPenyewa(String noKTP, String nama, String alamat, String komentar) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "INSERTINTO [Penyewa] ([noKTP], [nama],[alamat],[komentar]) VALUES (" + noKTP + "," + nama + "," + alamat + "," + komentar + ")";
            ResultSet rs = sta.executeQuery(query);

            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    
    public void updatePegawai() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select EmployeeId,nama,Status_Jabatan from Pegawai";
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("EmployeeId");
                String nama = rs.getString("nama");
                String manager = rs.getString("Status_Jabatan");
                Pegawai p=new Pegawai(id,nama,manager);
                pegawe.add(p);
               
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public TableModel tabelPegawai() {
        
        TabelPegawai table=new TabelPegawai(this.pegawe);
        
        return table;
        
    }
    
    public TableModel tabelKomentar() {
        
        TabelKomentar table=new TabelKomentar(this.pesewa);
        return table;
    }
    
    public TableModel tabelKeungan() {
        
        TabelRental table=new TabelRental(this.rent);
        return table;
    }

}
