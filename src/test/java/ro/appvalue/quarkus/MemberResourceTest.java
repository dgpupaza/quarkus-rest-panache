package ro.appvalue.quarkus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberResourceTest {
    
    @Test
    @Order(1)
    public void listTest() {
        given()
            .when()
                .get("/members")
            .then()
                .statusCode(200)
                .body("$.size()", is(4));
    }

    @Test
    @Order(2)
    public void getOKTest() {
        final Member member = given()
                                .when()
                                    .get("/members/1")   
                                .then()
                                    .statusCode(200)
                                    .extract()
                                        .as(Member.class);

        assertEquals("11111111", member.phone);                                 
    } 
    
    @Test
    @Order(3)
    public void getKOTest() {
        given()
            .when()
                .get("/members/10")   
            .then()
                .statusCode(404);

    }    

    @Test
    @Order(4)
    @Transactional
    public void addTest() {
        final Member member = given()
                                .when()
                                    .contentType(ContentType.JSON)
                                    .body("{\"email\":\"Silvia.Maddox@test.io\",\"name\":\"Silvia Maddox\",\"phone\":\"123\"}")
                                    .post("/members")
                                .then()
                                    .statusCode(201)
                                    .extract().as(Member.class);

        System.out.println(member);
                                    
        assertEquals(5L, member.id);
        assertEquals(5, Member.count());
    }

    @Test
    @Order(5)
    @Transactional
    public void updateTest() {
        given()
            .when()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"Silvia.Maddox@test.io\",\"name\":\"Silvia Maddox\",\"phone\":\"321\"}")
                .put("/members/2")
            .then()
                .statusCode(204);
    }  
    
    @Test
    @Order(6)
    @Transactional
    public void updateWithCreateTest() {
        given()
            .when()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"Silvia.Maddox@test.io\",\"name\":\"Silvia Maddox\",\"phone\":\"1230\"}")
                .put("/members/10")
            .then()
                .statusCode(201);
    } 

    @Test
    @Order(7)
    @Transactional
    public void deleteOKTest() {
        given()
            .when()
                .delete("/members/1")
            .then()
                .statusCode(204);
    }

    @Test
    @Order(8)
    @Transactional
    public void deleteKOTest() {
        given()
            .when()
                .delete("/members/1")
            .then()
                .statusCode(404);
    }        
}
