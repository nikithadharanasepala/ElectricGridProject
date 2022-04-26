package com.feedback.resource;

import com.feedback.model.feedbackModel;
import com.feedback.service.feedbackService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/feedback")
public class feedbackResource {

    feedbackService service = new feedbackService();

    @Path("/insertion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public feedbackModel addFeedback(feedbackModel feedback){

        return service.insertFeedback(feedback);
    }

    @Path("/retrieve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<feedbackModel> getFeedback() throws SQLException {

        return service.getFeedback();
    }

    @Path("/retrieveById/{feedbackid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<feedbackModel> getFeedback(@PathParam("feedbackid") int feedbackid) throws SQLException{

        return service.getFeedbackById(feedbackid);
    }

    @Path("/updatefeedback")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public feedbackModel updateFeedback(feedbackModel feedback){

        return service.updateFeedback(feedback);
    }

    @Path("/deletefeedbackById/{feedbackid}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public int  deleteFeedback(@PathParam("feedbackid")int feedbackid){

        return service.deleteFeedback(feedbackid);
    }
}
