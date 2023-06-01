import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;     // we need all of it so we change .given here to the .*
import static org.apache.commons.codec.digest.UnixCrypt.*;
import static org.hamcrest.Matchers.*;   //i want to use everying under this package!  // thats we import here on top for hampcrest !

public class ZippoTest {

    @Test
    public void test() {

        given()              // power over it !  its its red !     // given() is praparation! or if we need request body we put
                //it here as well request body!
                // and sometimes we need parameters we set it under given()
                //  we can send cookies ....

                .when()        // we put link API and request metod here if its get, put ....
                // so for URL and request metods(post,put,get,delete,patch)


                .then();         // response body, assertions or if you want extract data from the response!  here we do it here ! name extract example!

    }

    //we send from https://zippopotam.us/ this documentation here!

    @Test
    public void statusCodeTest() {


        given()    // in this test we dont have parameters , cookiest .. empty here for now

                .when()     // we going to use request metod post,put ,get, delete like at postman for it!
                .get("https://api.zippopotam.us/US/00210")       // we need a link url !
                .then()   // is response! if u dont print request u dont see it!
                .log().body()       //print response body!  thats how we going to print it! and run the test !
                .log().status()     // print status ! ITS LIKE ASSERTION! its show us code 200! wos Aslambek helps figur out! its prints the status!
                .statusCode(200);        // lets check if statusCode is 200!
        //.statusCode(400);        // lets check if statusCode is 400!  Expected status code <400> but was <200>.
//(it s give us at documentation! )
    }


    @Test
    public void contentTypeTestJason() {


        given()  //authorization, parameters. cookiest....
                .when().get("https://api.zippopotam.us/US/00210")     //use same url!
                .then().log().body().log().status()   // its not nessesary just play with it!
                .statusCode(200)    // u can do more than one test here!
                .contentType(ContentType.JSON);   // its check if response in Jason format!   // ContentType.JSON) make sure its from restAssure!

    }

    @Test
    public void checkCountyFromResponseBody() {

        given().when().get("https://api.zippopotam.us/US/00210")     //use same url!
                .then().log().body()
//                .log().status()    // its no need just play with it!
                //so we need the body...
                .body("post code", equalTo("00210"));   //we need to import static metod for equalTo() metod and than use hamcuest!   //so we need the .body(country, and here that why we have dependensy hamcrest! assertin! )
//import static org.hamcrest.Matchers.equalTo;    // thats we import here on top for hampcrest !
        //import static org.hamcrest.Matchers.*; we use * for it means everything under Matchers package!
        // we already have dependensy but its not enoth for it we shold change Matchers.* for it !!!
        //thats how we rich any value under the body! use the body method -specify what we want -country to check!  than write value of the test!
// if we make mistake in value our test will faile! why ???
        //

        //how we rich in
        //Postman??                                                              //RestAssured?
        //pm.response.jason().'post code'                                         //body("post code",....)
        //pm.response.jason().places[0].'place name'                               //body("places[1].'place name',.....")  //under body we need places. .body("places[1].'place name'")- if we have space we use ' '
        // pm.response.jason().places.'place name '                                //body("places.'place name'")

        //**we have the list of 2 arraws! First of all i shold rich the club;under the club - list what the name of the list?
        // which element? of this list? [0]. print what? we want name so .getName()**
        //
        //**sistem.out.print(club)**
        //
        //**sistem.out.print()**
        //
        //**club.members[0].getName();**
        //
        //**sistem.out.print(club.members[0].getName());  // that how its goes!**

    }

    @Test
    public void checkStateFromResponse() {
        given()   //we dont have anything for given
                .when().get("https://api.zippopotam.us/RU/101000")  // state Moscow
                .then().log().body().log().status().statusCode(200)   // lets check status code
                // lets run and see how we can rich state name ! smart way!
                .body("places[0].state", equalTo("Москва")); ////if the state is Moskow  // its works but we shold use it diff way!
//.body("places[0].'place name'", equalTo("Москва"));

    }

    @Test
    public void bodyHasItem() {
        given().when().get("https://api.zippopotam.us/TR/81950").then()
                // we coment it out we cant see body request on the console!
//                .log().body()    //print body   // we dont have to do it all time just to see what we have
//                .log().status()  //print statusCode // we dont have to do it at all! jst check status!
                .statusCode(200).body("places.'place name'", hasItem("Güney Köyü"));   // 2 words - single quotes ' '
        //its give us all place name as a list couse we not use index of array!

        //its its POST metod we can add to it - create list of ....or update it PUT
        //but this is coming from dataBase table from documentation we can see it how ii should be blackBox
        //its called POJO models !! we create classes for big projecdt and check all stuff from there!


    }

    @Test
    public void bodyArraySizeTest() {    // we can test the size of this array!
        given().when().get("https://api.zippopotam.us/US/00210").then().log().body().log().status().statusCode(200).body("places", hasSize(1));
        // places array has only one value - one list !


    }

    @Test
    public void bodyArraySizeTest2() {    // we can test the size of this array!
        given().when()  //.when() // for url and request method (get, post, put, delete)
                .get("https://api.zippopotam.us/TR/01000") //expacted condition 71 elements !
                .then().log().body().log().status().statusCode(200).body("places.'place name'", hasSize(71));  // if size of the list of place name is 71

// number of element of list !
        //Aslambek couted it but for the future we will find out the size of it!
    }

    // u can combine i do muliple test! in one place!
    @Test
    public void multipleTest() {
        given()   // for preparation for setting ....send parameters
                .when()  //.when() // for url and request method (get, post, put, delete)
                .get("https://api.zippopotam.us/TR/01000") //expacted condition 71 elements !
                .then().log().body()   // what we have in the body?? and write what we need according to
//                .log().status()
//                .statusCode(200)
                .body("places", hasSize(71))   // if size of places is 71
                .body("places.'place name'", hasItem("Dervişler Köyü"))  // array of place names so we check if this
                // list has this value of "Dervişler Köyü"

                // 2 diff test in one request!
                .body("places[2].'place name'", equalTo("Dörtağaç Köyü"));  // put wrong asserting here!
        // its matched with expacetd one last one!


    }

//    String Country = "us";   // if you want to use param from multiple @Test set outside of the @Test
//    String ZipCode = "00210";

    @Test
    public void pathParamTest() {
        //public void pathParametersTest(String...name){  remember this usage from Java?  i will get array of names !
        //.pathParams  -does the same things list String...name
        //.pathParam only one param
        // pathParameters called US/00210

        given()                                                //pathParameters we change it here!
                .pathParam("Country", "us")   // its like Sting  -name of parameter and value us
                .pathParam("ZipCode", "00210").log().uri()   // print the request url
                .when()                                        // choose request method
                .get("https://api.zippopotam.us/{Country}/{ZipCode}")  // will get values from parameters  // put the link url
                .then().log().body().log().status().statusCode(200);
    }

    @Test
    public void pathParamTest1() {
        //send get requuest for zipCodes beetwen 90210 and 90213 and virify that in all resposes the size of the places
        // array is 1
        // we shold creae loop but before loop we can send only one zipcode and then figur out how to do multiple zips
        for (int i = 90210; i <= 90213; i++) {    // instead of use stable zip use i
            given()                                                //pathParameters we change it here!
                    .pathParam("Country", "us")   // its like Sting  -name of parameter and value us
                    .pathParam("ZipCode", i)  // set value is i zip
                    .log().uri()// print the request url
                    .when()                                        // choose request method
                    .get("https://api.zippopotam.us/{Country}/{ZipCode}")  // will get values from parameters  // put the link url
                    .then().log().body().log().status().statusCode(200)
                    // only we need to check if it place of array is 1
                    .body("places", hasSize(1));


        }

    }

    @Test
    public void querryParamTest() {
        // we use go rest API for it!!!
//        https://gorest.co.in/public/v1/users?page=3
        given().param("page", 2)   // run the code-send request!
                //if i change page num i can see dirr user from diff pages! its called param from reques body!
                // https://gorest.co.in/public/v1/users?page=2
                //ITS NOT PART OF URI THATS THE DIFF FROM PREVIOUS TASK! ITS PART OF THE BODY REQUEST BODY!
                // WE DONT WANT TO SEE OUR ID OR PASWORD ON THE URI THAT QUWRRY PARAM!
                .log().uri().when().get("https://gorest.co.in/public/v1/users").then().log().body()     // what we have
                .log().status().statusCode(200)
                //we only see page one i want to see page 2 as well!
                // but how we going to change page its not part of uri!! - we will do it from given() param
                // by the way its 292 pages !!!!!!
                // total 2911 users in total!
                //HOW WE CAN RICH 2ND PAGE VALUE???
                .body("meta.pagination.page", equalTo(2));   // forgot assertion again !


    }


    @Test
    public void querryParamTest1() {
        //send the same requst for the pages from 1 to 10
        for (int i = 1; i <= 10; i++) {


            given().param("page", i)   // run the code-send request!
                    //if i change page num i can see dirr user from diff pages! its called param from reques body!
                    // https://gorest.co.in/public/v1/users?page=2
                    //ITS NOT PART OF URI THATS THE DIFF FROM PREVIOUS TASK! ITS PART OF THE BODY REQUEST BODY!
                    // WE DONT WANT TO SEE OUR ID OR PASWORD ON THE URI THAT QUWRRY PARAM!
                    .log().uri().when().get("https://gorest.co.in/public/v1/users").then().log().body()     // what we have
                    .log().status().statusCode(200)
                    //we only see page one i want to see page 2 as well!
                    // but how we going to change page its not part of uri!! - we will do it from given() param
                    // by the way its 292 pages !!!!!!
                    // total 2911 users in total!
                    //HOW WE CAN RICH 2ND PAGE VALUE???
                    .body("meta.pagination.page", equalTo(i)); // forgot assertion again !
            // at the assertion we going to check param and assrt is equal when its run! ??? thats why equalTo()!!!
//                    .body("data[0].name", equalTo("Suryakanta Pillai IV")); just try some stuff !


        }


    }
}