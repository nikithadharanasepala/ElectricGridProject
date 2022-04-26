package com.payment.service;

import com.payment.model.paymentModel;

import java.sql.*;
import java.util.ArrayList;

public class paymentService {

    Connection con;

    public paymentService() {
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

    public paymentModel insertPayment(paymentModel payment){
        String insert="insert into payment(paymentid,amount,paymentType,cardNo) value(?,?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(insert);
            ps.setLong(1,payment.getPaymentid());
            ps.setLong(2,payment.getAmount());
            ps.setString(3,payment.getPaymentType());
            ps.setLong(4,payment.getCardNo());

            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
        return payment;
    }

    public ArrayList<paymentModel> getPayment() throws SQLException{

        ArrayList<paymentModel> data = new ArrayList<>();
        String select ="select * from payment";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            paymentModel model = new paymentModel();

            model.setPaymentid(rs.getInt("paymentid"));
            model.setAmount(rs.getInt("amount"));
            model.setPaymentType(rs.getString("paymentType"));
            model.setCardNo(rs.getInt("cardNo"));

            data.add(model);
        }


        return data;
    }

    public ArrayList<paymentModel> getPaymentById(int paymentid) throws SQLException{
        ArrayList<paymentModel> data = new ArrayList<paymentModel>();
        String select="select * from payment where paymentid=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,paymentid);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            paymentModel model = new paymentModel();

            model.setPaymentid(rs.getInt("paymentid"));
            model.setAmount(rs.getInt("amount"));
            model.setPaymentType(rs.getString("paymentType"));
            model.setCardNo(rs.getInt("cardNo"));

            data.add(model);
        }
        return data;
    }

    public paymentModel updatePayment(paymentModel payment){

        String insert = "update payment set amount=?, paymentType=?, cardNo=? where paymentid=?";

        try {
            PreparedStatement ps =con.prepareStatement(insert);
            ps.setLong(1,payment.getAmount());
            ps.setString(2,payment.getPaymentType());
            ps.setLong(3,payment.getCardNo());
            ps.setLong(4,payment.getPaymentid());


            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Update Unsucessfull");
        }


        return payment;
    }

    public int deletePayment(int paymentid){

        String insert="delete from payment where paymentid=?";

        try{
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1,paymentid);
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e +"data delete unsuccessfull");
        }
        return paymentid;
    }
}