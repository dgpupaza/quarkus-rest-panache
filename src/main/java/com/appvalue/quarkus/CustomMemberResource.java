package ro.appvalue.quarkus;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;

@Path("/members")
public class CustomMemberResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> list() {
        return Member.listAll(Sort.ascending("name"));
    }

}