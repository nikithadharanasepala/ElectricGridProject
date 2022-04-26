package com.billManagement.resource;

import com.billManagement.model.billModel;
import com.billManagement.service.billService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/bill")
public class billResource {

        billService service = new billService();

        @Path("/insertion")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public billModel addBill(billModel bill){

            return service.insertBill(bill);
        }

        @Path("/retrieve")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public ArrayList<billModel> getBill() throws SQLException {

            return service.getbill();
        }

        @Path("/retrieveById/{billId}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public ArrayList<billModel> getBill(@PathParam("billId") int billId) throws SQLException{

            return service.getbillById(billId);
        }

        @Path("/updateBill")
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        public billModel updateBill(billModel bill){

            return service.updatebill(bill);
        }

        @Path("/deletebillById/{billId}")
        @DELETE
        @Consumes(MediaType.APPLICATION_JSON)
        public int  deleteBill(@PathParam("billId")int billId){

            return service.deletebill(billId);
        }






}
