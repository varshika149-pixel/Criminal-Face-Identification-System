package dao;

import database.DBConnection;
import model.Criminal;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
public class CriminalDAO {
    public Criminal searchCriminal(int id) {

        String sql = "SELECT * FROM criminals WHERE criminal_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Criminal criminal = new Criminal();

                criminal.setCriminalId(rs.getInt("criminal_id"));
                criminal.setName(rs.getString("name"));
                criminal.setAge(rs.getInt("age"));
                criminal.setGender(rs.getString("gender"));
                criminal.setCrime(rs.getString("crime"));
                criminal.setAddress(rs.getString("address"));
                criminal.setImagePath(rs.getString("image_path"));

                con.close();

                return criminal;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Criminal> getAllCriminals() {

        List<Criminal> list = new ArrayList<>();

        String sql = "SELECT * FROM criminals";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Criminal criminal = new Criminal();

                criminal.setCriminalId(rs.getInt("criminal_id"));
                criminal.setName(rs.getString("name"));
                criminal.setAge(rs.getInt("age"));
                criminal.setGender(rs.getString("gender"));
                criminal.setCrime(rs.getString("crime"));
                criminal.setAddress(rs.getString("address"));
                criminal.setImagePath(rs.getString("image_path"));

                list.add(criminal);
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean addCriminal(Criminal criminal) {

        String sql = "INSERT INTO criminals VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, criminal.getCriminalId());
            ps.setString(2, criminal.getName());
            ps.setInt(3, criminal.getAge());
            ps.setString(4, criminal.getGender());
            ps.setString(5, criminal.getCrime());
            ps.setString(6, criminal.getAddress());
            ps.setString(7, criminal.getImagePath());

            int rows = ps.executeUpdate();

            con.close();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCriminal(Criminal criminal) {

        String sql = "UPDATE criminals SET "
                + "name=?, age=?, gender=?, crime=?, address=?, image_path=? "
                + "WHERE criminal_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, criminal.getName());
            ps.setInt(2, criminal.getAge());
            ps.setString(3, criminal.getGender());
            ps.setString(4, criminal.getCrime());
            ps.setString(5, criminal.getAddress());
            ps.setString(6, criminal.getImagePath());
            ps.setInt(7, criminal.getCriminalId());

            int rows = ps.executeUpdate();

            con.close();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCriminal(int id) {

        String sql = "DELETE FROM criminals WHERE criminal_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            con.close();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}