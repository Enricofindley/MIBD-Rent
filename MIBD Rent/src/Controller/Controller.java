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
import Engine.TabelPegawai;

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
    String url;

    public Controller() {
        url = "jdbc:sqlserver://10.100.70.70;user=i13069;password=ryukishin;database=i13069";
        pegawe=new ArrayList<Pegawai>();
    }

    

    public void updateKendaraan() {
        ArrayList<Kendaraan> result = null;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select IdKendaraan,noPolisi,kapasitas from Penyewa";
            ResultSet rs = sta.executeQuery(query);
            kendaraan = new ArrayList<Kendaraan>();
            while (rs.next()) {
                String id = rs.getString("Idkendaraan");
                String nopol = rs.getString("noPolisi");
                int kapas = Integer.getInteger(rs.getString("kapasitas"));
                kendaraan.add(new Kendaraan(id, nopol, kapas));
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
            String query = "select noKTP,nama,kommentar,alamat from Penyewa";
            ResultSet rs = sta.executeQuery(query);
            pesewa = new ArrayList<Penyewa>();
            while (rs.next()) {
                String id = rs.getString("noKTP");
                String nama = rs.getString("Nama");
                String alamat = rs.getString("alamat");
                String komentar = "?";
                komentar = rs.getString("komentar");
                pesewa.add(new Penyewa(komentar, id, alamat, nama));
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

    public TableModel tabelKomentar() {
        this.updatePenyewa();
        DefaultTableModel model = new DefaultTableModel();
        String[] header = new String[2];
        header[0] = "noKTP";
        Object[] o = {header[0], header[1]};
        model.addRow(o);
        int i = 0;
        while (i < this.pesewa.size()) {
            String[] orang = {this.pesewa.get(i).getNoKTP(), this.pesewa.get(i).getComment()};
        }
        return model;
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
        this.updatePegawai();
        TabelPegawai table=new TabelPegawai(this.pegawe);
        
        return table;
        
    }

}
