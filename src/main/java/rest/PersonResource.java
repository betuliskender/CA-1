package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDto;
import entities.Person;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("person")
public class PersonResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"Hej" +
                "\":\"Her kan du finde alle personerne gennem api\"}";
    }

    /*@Path("{phone}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("phone") int phone) {
        return Response.ok().entity(GSON.toJson(FACADE.getByPhone(phone))).build();
    }*/

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})

    public Response getAll() {
        List<PersonDto>personList = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }
}
