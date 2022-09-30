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

    @Path("{id}")
    @GET
    @Produces("text/plain")
    public Response getPersonById(@PathParam("id") int id) {

        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updatePerson(@PathParam("id") int id, String jsonInput) {
        Person person = GSON.fromJson(jsonInput, Person.class);
        person.setId(id);
        PersonDto personDto = new PersonDto(person);
        return Response.ok().entity(GSON.toJson(FACADE.update(personDto))).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPerson(String jsonInput) {
        PersonDto personDto = GSON.fromJson(jsonInput, PersonDto.class);

        return Response.ok().entity(GSON.toJson(FACADE.create(personDto))).build();
    }
}
