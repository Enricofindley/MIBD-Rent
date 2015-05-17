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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controller {

    ArrayList<Pegawai> pegawe;
    ArrayList<Kendaraan> kendaraan;
    ArrayList<Penyewa> pesewa;
    String connectionString;
    Connection conn;

    public Controller() throws IOException, ClassNotFoundException {
        try {
            String url = "jdbc:sqlserver://10.100.70.70;user=i13069;password=ryukishin;database=i13069";
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void updatePegawai() {
        try {
            ArrayList<Pegawai> result = null;
            Statement sta = conn.createStatement();
            String query = "select IdPegawai,Nama,IsManager from Pegawai";
            ResultSet rs = sta.executeQuery(query);
            result = new ArrayList<Pegawai>();
            while (rs.next()) {
                String id = rs.getString("IdPegawai");
                String nama = rs.getString("Nama");
                boolean manager = rs.getBoolean("IsManager");
                result.add(new Pegawai(id, nama, manager));
            }
            rs.close();
            conn.close();
            this.pegawe=result;

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void updateKendaraan() {
        ArrayList<Kendaraan> result = null;
        try {

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
            Connection conn = DriverManager.getConnection(this.connectionString);
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
            Connection conn = DriverManager.getConnection(this.connectionString);
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
            Connection conn = DriverManager.getConnection(this.connectionString);
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

    public JTable tabelPegawai() {
        this.updatePegawai();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        String[] header = new String[3];
        header[0] = "ID";
        header[1] = "Nama";
        header[2] = "IsManager";
        model.addRow(new Object[]{header[0], header[1],header[2]});
        int i = 0;
        while (i < this.pegawe.size()) {
            Object[] orang = (new Object[]{this.pegawe.get(i).getID(), this.pegawe.get(i).getNama(),this.pegawe.get(i).isManager()});
            model.addRow(orang);
        }
        table.setModel(model);
        return table;
    }

}
