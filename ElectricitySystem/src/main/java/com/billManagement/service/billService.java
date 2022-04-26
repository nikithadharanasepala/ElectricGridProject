package com.billManagement.service;

import com.billManagement.model.billModel;

import java.sql.*;
import java.util.ArrayList;

public class billService {
    Connection con;


    public billService() {
        try {
            String url = "jdbc:mysql://localhost:3306/electricitydb";
            String uname = "root";
            String pwd = "";


            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, pwd);
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
    }

    public billModel insertBill(billModel bill){
        String insert="insert into bill(billId,accountNum,customerName,unit) value(?,?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(insert);
            ps.setLong(1,bill.getBillId());
            ps.setLong(2,bill.getAccountNum());
            ps.setString(3,bill.getCustomerName());
            ps.setLong(4,bill.getUnit());

            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
        return bill;
    }

    public ArrayList<billModel> getbill() throws SQLException {

        ArrayList<billModel> data = new ArrayList<>();
        String select ="select * from bill";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            billModel model = new billModel();

            model.setBillId(rs.getInt("billId"));
            model.setAccountNum(rs.getInt("accountNum"));
            model.setCustomerName(rs.getString("customerName"));
            model.setUnit(rs.getInt("unit"));

            data.add(model);
        }


        return data;
    }

    public ArrayList<billModel> getbillById(int billid) throws SQLException{
        ArrayList<billModel> data = new ArrayList<billModel>();
        String select="select * from bill where billId=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,billid);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            billModel model = new billModel();


            model.setBillId(rs.getInt("billId"));
            model.setAccountNum(rs.getInt("accountNum"));
            model.setCustomerName(rs.getString("customerName"));
            model.setUnit(rs.getInt("unit"));

            data.add(model);
        }
        return data;
    }

    public billModel updatebill(billModel bill){

        String insert = "update bill set accountNum=?, customerName=?, unit=? where billId=?";

        try {
            PreparedStatement ps =con.prepareStatement(insert);
            ps.setLong(1,bill.getAccountNum());
            ps.setString(2,bill.getCustomerName());
            ps.setLong(3,bill.getUnit());
            ps.setLong(4,bill.getBillId());


            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Update Unsucessfull");
        }


        return bill;
    }

    public int deletebill(int billId){

        String insert="delete from bill where billId=?";

        try{
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1,billId);
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e +"data delete unsuccessfull");
        }
        return billId;
    }

}
