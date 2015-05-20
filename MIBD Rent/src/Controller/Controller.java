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
import Engine.JenisKendaraan;
import Engine.Kendaraan;
import Engine.Pegawai;
import Engine.Penyewa;
import Engine.Rental;
import Engine.TabelKendaraan;
import Engine.TabelKomentar;
import Engine.TabelPegawai;
import Engine.TabelRental;
import Engine.TabelSewa;

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
    ArrayList<Rental> rental;
    ArrayList<JenisKendaraan> jk;

    String url;

    public Controller() {
        url = "jdbc:sqlserver://10.100.70.70;user=i13069;password=ryukishin;database=i13069";
        pegawe = new ArrayList<Pegawai>();
        kendaraan = new ArrayList<Kendaraan>();
        pesewa = new ArrayList<Penyewa>();
        rent = new ArrayList<Rental>();
        rental = new ArrayList<Rental>();
        jk=new ArrayList<JenisKendaraan>();
        this.updateRental();
        this.updatePenyewa();
        this.updatePegawai();
        this.updateKendaraan();
        this.updateRentalSewa();

    }
    
    public void updateJenisKendaraan() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select id_jenis,nama_jenis,kapasitas from jenis_kendaraan";
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id_jenis");
                String nama = rs.getString("nama_jenis");
                int kapas = rs.getInt("kapasitas");
                JenisKendaraan k = new JenisKendaraan(Integer.parseInt(id),nama,kapas);
                this.jk.add(k);
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void updateKendaraan() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select Kendaraan.ID_Kendaraan,Kendaraan.noPol,Jenis_Kendaraan.Kapasitas,Kendaraan.biaya_sewa,Jenis_Kendaraan.id_Jenis,Jenis_Kendaraan.Nama_Jenis from Kendaraan join Jenis_Kendaraan on Kendaraan.id_jenis=Jenis_Kendaraan.ID_Jenis";
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("ID_Kendaraan");
                String nopol = rs.getString("noPol");
                int kapas = rs.getInt("Kapasitas");
                int harga = rs.getInt("biaya_sewa");
                String jenis = rs.getString("Nama_Jenis");
                Kendaraan k = new Kendaraan(id, nopol, kapas, harga, jenis);
                this.kendaraan.add(k);
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
                Penyewa sewa = new Penyewa(id, nama, alamat, komentar);
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
            String query = "select id_transaksi,Kendaraan.biaya_sewa,tanggal_sewa from Rental join Kendaraan on Rental.id_kendaraan=Kendaraan.id_kendaraan";
            ResultSet rs = sta.executeQuery(query);

            while (rs.next()) {
                String id = rs.getString("id_transaksi");
                int biaya = rs.getInt("biaya_sewa");
                String tanggal = rs.getString("tanggal_sewa");
                Rental sewa = new Rental(id, biaya, tanggal);
                rent.add(sewa);
            }
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
                Pegawai p = new Pegawai(id, nama, manager);
                pegawe.add(p);

            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void updateRentalSewa() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select Rental.id_transaksi,Penyewa.no_ktp,Penyewa.nama,Kendaraan.ID_Kendaraan,jenis_kendaraan.id_jenis,Jenis_Kendaraan.Nama_Jenis,Kendaraan.nopol from rental left join Penyewa on rental.no_ktp=penyewa.no_ktp left join Kendaraan on rental.ID_Kendaraan=Kendaraan.ID_Kendaraan left join Jenis_kendaraan on Kendaraan.id_jenis=Jenis_kendaraan.id_jenis";
            ResultSet rs = sta.executeQuery(query);

            while (rs.next()) {
                String idtrans = rs.getString("id_transaksi");
                String id = rs.getString("no_ktp");
                String nama = rs.getString("nama");
                String idK = rs.getString("ID_kendaraan");
                String jenis = rs.getString("nama_jenis");
                String nopol = rs.getString("noPol");
                String namajenis = rs.getString("Nama_Jenis");
                Penyewa pesewa = new Penyewa(id, nama, "", "");
                Kendaraan car = new Kendaraan(idK, nopol, 0, 0, jenis);
                Rental sewa = new Rental(idtrans, pesewa, car);
                rental.add(sewa);
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void insertPegawai(String nama) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int banyak = this.pegawe.size();
            String id = "" + banyak;
            for (int i = id.length(); i < 8; i++) {
                id = "0" + id;
            }
            int i = 1000;
            int index = 0;
            while (index < this.pesewa.size()) {
                if (nama.equalsIgnoreCase(this.pesewa.get(index).getNama())) {
                    i = index;
                }
                index++;
            }
            if (i == 1000) {

            } else {
                
                String query = "INSERTINTO [Pegawai] VALUES ('" + id + "','" + nama + "','false')";
                sta.execute(query);
                Pegawai pegawai=new Pegawai(id,nama,"false");
                this.pegawe.add(pegawai);
            }
                
            
           
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertKendaraan(String idKendaraan, String noPol, int sewa, int kapas, String idJenis) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "INSERTINTO [Kendaraan]  VALUES (" + idKendaraan + "," + noPol + "," + kapas + "," + sewa + "," + idJenis + ")";
            ResultSet rs = sta.executeQuery(query);
            Kendaraan mobil = new Kendaraan(idKendaraan, noPol, sewa, kapas, idJenis);
            this.kendaraan.add(mobil);
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertKomentar(String noKTP, String komentar) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int i = 1000;
            int index = 0;
            while (index < this.pesewa.size()) {
                if (noKTP.equalsIgnoreCase(this.pesewa.get(index).getNoKTP())) {
                    i = index;
                }
                index++;
            }
            if (i == 1000) {

            } else {

                String alamat = this.pesewa.get(i).getAlamat();
                String nama = this.pesewa.get(i).getNama();
                String query = "INSERTINTO [Penyewa]  VALUES ('" + komentar + "','" + nama + "','" + alamat + "','" + noKTP + "')";
                sta.execute(query);
                Penyewa sewa = new Penyewa(noKTP, nama, alamat, komentar);
                this.pesewa.remove(i);
                this.pesewa.add(sewa);
                
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertPenyewa(String noKTP, String nama, String alamat, String komentar) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int i = 1000;
            int index = 0;
            while (index < this.pesewa.size()) {
                if (noKTP.equalsIgnoreCase(this.pesewa.get(index).getNoKTP())) {
                    i = index;
                }
                index++;
            }
            if (i == 1000) {
                ResultSet rs;
                String query = "INSERTINTO [Penyewa]  VALUES ('" + komentar + "','" + nama + "','" + alamat + "','" + noKTP + "')";
                rs = sta.executeQuery(query);
                Penyewa sewa = new Penyewa(noKTP, nama, alamat, komentar);
                this.pesewa.remove(i);
                this.pesewa.add(sewa);
                rs.close();
            } else {

            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public TableModel tabelPegawai() {

        TabelPegawai table = new TabelPegawai(this.pegawe);

        return table;

    }

    public TableModel tabelKendaraan() {

        TabelKendaraan table = new TabelKendaraan(this.kendaraan);

        return table;

    }

    public TableModel tabelKomentar() {

        TabelKomentar table = new TabelKomentar(this.pesewa);
        return table;
    }

    public TableModel tabelKeuangan() {

        TabelRental table = new TabelRental(this.rent);
        return table;
    }

    public TableModel tabelsewa() {

        TabelSewa table = new TabelSewa(this.rental);

        return table;
    }

}
