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
import Engine.Pengembalian;
import Engine.Penyewa;
import Engine.Rental;
import Engine.TabelKendaraan;
import Engine.TabelKomentar;
import Engine.TabelPegawai;
import Engine.TabelPengembalian;
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

    public ArrayList<Pegawai> pegawe;
    public ArrayList<Kendaraan> kendaraan;
    public ArrayList<Penyewa> pesewa;
    public ArrayList<Rental> rent;
    public ArrayList<Rental> rental;
    public ArrayList<Pengembalian> pengembalian;
    public ArrayList<JenisKendaraan> jk;
    private int idk;
    private int idp;
    private int idr;
    private int idjk;

    String url;

    public Controller() {
        url = "jdbc:sqlserver://10.100.70.70;user=i13069;password=ryukishin;database=i13069";
        pegawe = new ArrayList<Pegawai>();
        kendaraan = new ArrayList<Kendaraan>();
        pesewa = new ArrayList<Penyewa>();
        rent = new ArrayList<Rental>();
        rental = new ArrayList<Rental>();
        jk = new ArrayList<JenisKendaraan>();
        pengembalian = new ArrayList<Pengembalian>();
        this.updateRental();
        this.updatePenyewa();
        this.updatePegawai();
        this.updateKendaraan();
        this.updateRentalSewa();
        this.updateJenisKendaraan();
        this.updatePengembalian();
       
    }

    public void updatePengembalian() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select id_pengembalian,tanggal_kembali,ID_kendaraan from Pengembalian";
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id_pengembalian");
                String tanggal = rs.getString("tanggal_kembali");
                String kendaraan = rs.getString("ID_kendaraan");
                Pengembalian k = new Pengembalian(id, kendaraan, tanggal);
                this.pengembalian.add(k);
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateJenisKendaraan() {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String query = "select jenis_kendaraan.id_jenis,nama_jenis,jenis_kendaraan.kapasitas,Kendaraan.biaya_sewa from jenis_kendaraan join Kendaraan on jenis_kendaraan.id_jenis=kendaraan.id_jenis";
            ResultSet rs = sta.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id_jenis");
                String nama = rs.getString("nama_jenis");
                int kapas = rs.getInt("kapasitas");
                int biaya = rs.getInt("biaya_sewa");
                JenisKendaraan k = new JenisKendaraan(Integer.parseInt(id), nama, kapas, biaya);
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
            int banyak = Integer.parseInt(this.pegawe.get(idk).getID());
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

                String query = "INSERT INTO [Pegawai] VALUES ('" + id + "','" + nama + "','false')";
                sta.execute(query);
                Pegawai pegawai = new Pegawai(id, nama, "false");
                this.pegawe.add(pegawai);
                this.idp++;
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertKendaraan(String noPol, int sewa, int kapas, String idJenis) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int i = 1000;
            int index = 0;
            while (index < this.pesewa.size()) {
                if (noPol.equalsIgnoreCase(this.kendaraan.get(index).getNoPol())) {
                    i = index;
                }
                index++;
            }
            if (i == 1000) {

                String idKendaraan = "" + (this.kendaraan.get(this.kendaraan.size() - 1).getId() + 1);
                for (int d = this.kendaraan.size(); i < 5; i++) {
                    idKendaraan = "0" + idKendaraan;
                }

                String query = "INSERTINTO [Kendaraan]  VALUES (" + idKendaraan + "," + noPol + "," + kapas + "," + sewa + "," + idJenis + ")";
                sta.execute(query);
                Kendaraan mobil = new Kendaraan(idKendaraan, noPol, sewa, kapas, idJenis);
                this.kendaraan.add(mobil);
                this.kendaraan.remove(i);

            } else {

            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertJenisKendaraan(String jenisKendaraan, int kapasitas, int harga) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int banyak = this.jk.get(this.jk.size() - 1).getId() + 1;
            String id = "" + banyak;
            for (int i = id.length(); i < 2; i++) {
                id = "0" + id;
            }
            String query = "INSERT INTO [jenis_kendaraan]  VALUES ('" + id + "','" + jenisKendaraan + "','" + kapasitas + "','" + harga + "')";
            sta.execute(query);
            JenisKendaraan jenis = new JenisKendaraan(Integer.parseInt(id), jenisKendaraan, kapasitas, harga);
            this.jk.add(jenis);
            this.idjk++;
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void editJenisKendaraan(String id, String jenisKendaraan, int kapasitas, int harga) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();

            String query = "INSERT INTO [jenis_kendaraan]  VALUES ('" + id + "','" + jenisKendaraan + "','" + kapasitas + "','" + harga + "')";
            sta.execute(query);
            JenisKendaraan jenis = new JenisKendaraan(Integer.parseInt(id), jenisKendaraan, kapasitas, harga);
            this.jk.add(jenis);
            this.idjk++;
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void deleteJenisKendaraan(String jenisKendaraan) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int i = 1000;
            int index = 0;
            while (index < this.jk.size()) {
                if (jenisKendaraan.equalsIgnoreCase(this.jk.get(index).getNama())) {
                    i = index;
                }
                index++;
            }
            ResultSet rs = null;
            String query = "select ID_kendaraan from Kendaraan join jenis_kendaraan on  Kendaraan.id_jenis=Jenis_kendaraan.id_jenis where Nama_Jenis='" + jenisKendaraan + "'";
            rs = sta.executeQuery(query);
            ArrayList<String> idjenis = new ArrayList<String>();
            int indexing = 0;
            while (rs.next()) {
                String jenis = rs.getString("ID_kendaraan");
                indexing++;
                idjenis.add(jenis);
            }
            for (int in = 0; in < indexing; in++) {
                String bquery = "delete from rental where ID_Kendaraan='" + idjenis.get(in) + "'";
                sta.execute(bquery);
                bquery = "delete from pengembalian where ID_Kendaraan='" + idjenis.get(in) + "'";
                sta.execute(bquery);
                bquery = "delete from Kendaraan where id_Kendaraan='" + idjenis.get(in) + "'";
                sta.execute(bquery);
            }

            String jquery = "delete from Jenis_kendaraan where id_jenis='" + jenisKendaraan + "'";
            sta.execute(jquery);
            this.jk.remove(i);
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void deleteKendaraan(String idK) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int i = 1000;
            int index = 0;
            while (index < this.kendaraan.size()) {
                if (idK.equalsIgnoreCase(this.kendaraan.get(index).getId())) {
                    i = index;
                }
                index++;
            }
            ResultSet rs = null;
            String query = "select ID_kendaraan from Pengembalian";
            rs = sta.executeQuery(query);
            int indexing = 0;
            while (rs.next()) {
                indexing++;
            }
            query = "select ID_kendaraan from Rental";
            rs = sta.executeQuery(query);
            int indexin = 0;
            while (rs.next()) {
                indexin++;
            }
            if (indexin > indexing) {
                indexing = indexin;
            }
            for (int in = 0; in < indexing; in++) {
                String bquery = "delete from rental where ID_Kendaraan='" + idK + "'";
                sta.execute(bquery);
                bquery = "delete from pengembalian where ID_Kendaraan='" + idK + "'";
                sta.execute(bquery);

            }

            String jquery = "delete from kendaraan where nama_jenis='" + idK + "'";
            sta.execute(jquery);
            this.kendaraan.remove(i);
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void deletepeminjaman(int idK) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();

            String bquery = "delete from pengembalian  where ID_Kendaraan='" + this.rental.get(idK) + "'";
            sta.execute(bquery);
            bquery = "delete from rental where ID_Kendaraan='" + this.rental.get(idK) + "'";
            sta.execute(bquery);
            this.rental.remove(idK);
            this.rent.remove(idK);

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void deletepesewa(int idK,String noktp) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();

            String bquery = "delete from pengembalian  where no_ktp'" + noktp + "'";
            sta.execute(bquery);
            bquery = "delete from rental where no_ktp='" + noktp + "'";
            sta.execute(bquery);
            this.pesewa.remove(idK);
            this.rental.remove(idK);
            this.rent.remove(idK);
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
                this.pesewa.remove(i);
                String querydelete = "delete from penyewa where no_ktp='" + noKTP + "'";
                sta.execute(querydelete);
                String query = "INSERT INTO [Penyewa]  VALUES ('" + komentar + "','" + alamat + "','" + nama + "','" + noKTP + "')";
                sta.execute(query);
                Penyewa sewa = new Penyewa(noKTP, nama, alamat, komentar);

                this.pesewa.add(sewa);

            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertPenyewa(String komentar, String alamat, String nama, String noKTP) {
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
                String query = "INSERT INTO [Penyewa]  VALUES ('" + komentar + "','" + nama + "','" + alamat + "','" + noKTP + "')";
                sta.execute(query);
                Penyewa sewa = new Penyewa(noKTP, nama, alamat, komentar);
                this.pesewa.add(sewa);

            } else {

            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertpengembalian(String idtrans, String tanggal) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String banyak = this.rent.get(this.rent.size() - 1).getId();
            int id = Integer.parseInt(banyak) + 1;
            banyak = "" + id;

            for (int i = banyak.length(); i < 2; i++) {
                banyak = "0" + banyak;
            }

            String query = "INSERT INTO [Pengembalian]  VALUES ('" + banyak + "','" + tanggal + "','" + " " + "','" + " " + "','" + " " + "')";
            sta.execute(query);
            Pengembalian sewa = new Pengembalian(banyak, tanggal, idtrans);

            this.pengembalian.add(sewa);

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void insertRental(Penyewa noktp, Kendaraan idken, String tanggal, Pegawai idpeg) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            String banyak = this.rent.get(this.rent.size() - 1).getId();
            int id = Integer.parseInt(banyak) + 1;
            banyak = "" + id;

            for (int i = banyak.length(); i < 2; i++) {
                banyak = "0" + banyak;
            }

            String query = "INSERT INTO [rental]  VALUES ('" + banyak + "','" + tanggal + "','" + idken.getId() + "','" + noktp.getNoKTP() + "','" + idpeg.getID() + "')";
            sta.execute(query);
            Rental sewa = new Rental(banyak, idken.getHarga(), tanggal);
            Rental sewo = new Rental(banyak, noktp, idken);
            this.rental.add(sewo);
            this.rent.add(sewa);

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void editRental(String banyak, Penyewa noktp, Kendaraan idken, String tanggal, Pegawai idpeg) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement sta = conn.createStatement();
            int i = 1000;
            int index = 0;
            while (index < this.pesewa.size()) {
                if (banyak.equalsIgnoreCase(this.rental.get(index).getId())) {
                    i = index;
                }
                index++;
            }
            if (i == 1000) {

            } else {
                this.rent.remove(i);
                this.rental.remove(i);
                String delete = "delete from rental where id='" + banyak + "'";
                sta.execute(delete);
                String query = "INSERT INTO [rental]  VALUES ('" + banyak + "','" + tanggal + "','" + idken.getId() + "','" + noktp.getNoKTP() + "','" + idpeg.getID() + "')";
                sta.execute(query);
                Rental sewa = new Rental(banyak, idken.getHarga(), tanggal);
                Rental sewo = new Rental(banyak, noktp, idken);
                this.rental.add(sewo);
                this.rent.add(sewa);
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

    public TableModel tabelpeng() {

        TabelPengembalian table = new TabelPengembalian(this.pengembalian);

        return table;
    }

    public ArrayList<String> getNamaManager() {
        ArrayList<String> nama = new ArrayList<String>();
        for (int i = 0; i < this.pegawe.size(); i++) {
            if (this.pegawe.get(i).isManager().equalsIgnoreCase("true")) {
                nama.add(this.pegawe.get(i).getNama());
            }
        }
        return nama;
    }

    public ArrayList<String> getNamaPegawe() {
        ArrayList<String> nama = new ArrayList<String>();
        for (int i = 0; i < this.pegawe.size(); i++) {
            if (this.pegawe.get(i).isManager().equalsIgnoreCase("false")) {
                nama.add(this.pegawe.get(i).getNama());
            }
        }
        return nama;
    }

}
