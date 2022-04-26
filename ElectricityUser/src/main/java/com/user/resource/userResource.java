package com.user.resource;

import com.user.model.userModel;
import com.user.service.userService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/user")
public class userResource {

    userService service = new userService();

    @Path("/insertion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public userModel addUser(userModel user){

        return service.insertUser(user);
    }

    @Path("/retrieve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<userModel> getUser() throws SQLException {

        return service.getUser();
    }

    @Path("/retrieveById/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<userModel> getUser(@PathParam("userid") int userid) throws SQLException{

        return service.getUserById(userid);
    }

    @Path("/updateuser")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public userModel updateuser(userModel user){

        return service.updateUser(user);
    }

    @Path("/deleteUserById/{userid}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public int  deleteUser(@PathParam("userid")int userid){

        return service.deleteUser(userid);
    }
}
