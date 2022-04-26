package com.feedback.service;

import com.feedback.model.feedbackModel;

import java.sql.*;
import java.util.ArrayList;

public class feedbackService {

    Connection con;

    public feedbackService() {
        try {
            String url ="jdbc:mysql://localhost:3306/electricitydatabase";
            String uname ="root";
            String pwd = "";



            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,uname,pwd);
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
    }

    public feedbackModel insertFeedback(feedbackModel feedback){
        String insert="insert into feedback(feedbackid,descs,cname,date ) value(?,?,?,?)";
        try {
            PreparedStatement ps =con.prepareStatement(insert);
            ps.setLong(1,feedback.getFeedbackid());
            ps.setString(2,feedback.getDescs());
            ps.setString(3,feedback.getCname());
            ps.setLong(4,feedback.getDate());


            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Insert Unsucessfull");
        }
        return feedback;
    }

    public ArrayList<feedbackModel> getFeedback() throws SQLException {

        ArrayList<feedbackModel> data = new ArrayList<>();
        String select ="select * from feedback";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            feedbackModel model = new feedbackModel();

            model.setFeedbackid(rs.getInt("feedbackid"));
            model.setDescs(rs.getString("descs"));
            model.setCname(rs.getString("cname"));
            model.setDate(rs.getInt("date"));



            data.add(model);
        }


        return data;
    }

    public ArrayList<feedbackModel> getFeedbackById(int feedbackid) throws SQLException{
        ArrayList<feedbackModel> data = new ArrayList<feedbackModel>();
        String select="select * from feedback where feedbackid=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1,feedbackid);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            feedbackModel model = new feedbackModel();

            model.setFeedbackid(rs.getInt("feedbackid"));
            model.setDescs(rs.getString("descs"));
            model.setCname(rs.getString("cname"));
            model.setDate(rs.getInt("date"));


            data.add(model);
        }
        return data;
    }

    public feedbackModel updateFeedback(feedbackModel feedback){

        String insert = "update feedback set descs=?, cname=?, date=?  where feedbackid=?";

        try {
            PreparedStatement ps =con.prepareStatement(insert);

            ps.setString(1,feedback.getDescs());
            ps.setString(2,feedback.getCname());
            ps.setLong(3,feedback.getDate());
            ps.setLong(4,feedback.getFeedbackid());

            ps.execute();
        } catch (Exception e) {
            System.out.println(e + "Data Update Unsucessfull");
        }


        return feedback;
    }

    public int deleteFeedback(int feedbackid){

        String insert="delete from feedback where feedbacktid=?";

        try{
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1,feedbackid);
            ps.executeUpdate();

        }catch (Exception e){
            System.out.println(e +"data delete unsuccessfull");
        }
        return feedbackid;
    }
}
