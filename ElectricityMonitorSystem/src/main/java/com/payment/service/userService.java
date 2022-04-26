package com.payment.service;

import com.payment.model.paymentModel;
import com.payment.model.userModel;

import java.sql.*;
import java.util.ArrayList;

public class userService {

    Connection con;

    public userService() {
        try {
            String url ="jdbc:mysql://localhost:3306/electricitydb";
            String uname ="root";
            String pwd = "";



            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uname,pwd);
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
    }

    public userModel insertUser(userModel user){
        String insert="insert into user(userid,userName,address,province,telno) value(?,?,?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(insert);
            ps.setLong(1,user.getUserid());
            ps.setString(2,user.getUserName());
            ps.setString(3,user.getAddress());
            ps.setString(4,user.getProvince());
            ps.setLong(1,user.getTelno());

            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
        return user;
    }

    public ArrayList<userModel> getUser() throws SQLException {

        ArrayList<userModel> data = new ArrayList<>();
        String select ="select * from user";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            userModel model = new userModel();

            model.setUserid(rs.getInt("userid"));
            model.setUserName(rs.getString("userName"));
            model.setAddress(rs.getString("address"));
            model.setProvince(rs.getString("province"));
            model.setTelno(rs.getInt("telno"));


            data.add(model);
        }


        return data;
    }

    public ArrayList<userModel> getUserById(int userid) throws SQLException{
        ArrayList<userModel> data = new ArrayList<userModel>();
        String select="select * from user where userid=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,userid);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            userModel model = new userModel();

            model.setUserid(rs.getInt("userid"));
            model.setUserName(rs.getString("userName"));
            model.setAddress(rs.getString("address"));
            model.setProvince(rs.getString("province"));
            model.setTelno(rs.getInt("telno"));


            data.add(model);
        }
        return data;
    }

    public userModel updateUser(userModel user){

        String insert = "update user set userName=?, address=?, province=?, telno=?  where userid=?";

        try {
            PreparedStatement ps =con.prepareStatement(insert);

            ps.setString(1,user.getUserName());
            ps.setString(2,user.getAddress());
            ps.setString(3,user.getProvince());
            ps.setLong(4,user.getTelno());
            ps.setLong(5,user.getUserid());

            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Update Unsucessfull");
        }


        return user;
    }

    public int deleteUser(int userid){

        String insert="delete from user where usertid=?";

        try{
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1,userid);
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e +"data delete unsuccessfull");
        }
        return userid;
    }
}
