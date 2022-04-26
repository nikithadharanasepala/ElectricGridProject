package com.payment.resource;


import com.payment.model.paymentModel;
import com.payment.service.paymentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/payment")
public class paymentResource {

    paymentService service = new paymentService();

    @Path("/insertion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public paymentModel addPayment(paymentModel payment){

        return service.insertPayment(payment);
    }

    @Path("/retrieve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<paymentModel> getPayment() throws SQLException{

        return service.getPayment();
    }

    @Path("/retrieveById/{paymentid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<paymentModel> getPayment(@PathParam("paymentid") int paymentid) throws SQLException{

        return service.getPaymentById(paymentid);
    }

    @Path("/updatePayment")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public paymentModel updatePayment(paymentModel payment){

        return service.updatePayment(payment);
    }

    @Path("/deletePaymentById/{paymentid}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public int  deletePayment(@PathParam("paymentid")int paymentid){

        return service.deletePayment(paymentid);
    }
}
