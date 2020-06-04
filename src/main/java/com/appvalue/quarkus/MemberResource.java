package ro.appvalue.quarkus;

import java.util.List;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.MethodProperties;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path = "/members")
public interface MemberResource extends PanacheEntityResource<Member, Long> {

    @MethodProperties(exposed = false)
    public List<Member> list();

}