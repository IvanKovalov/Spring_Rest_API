package com.example.demo;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicSimulationTest extends Simulation {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicSimulationTest.class);



    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8081") // Here is the root for all relative URLs
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            /*.authorizationHeader("Basic YWRtaW46YWRtaW4=")*/
            //.authorizationHeader(session -> session.getString("sessid"))
            .userAgentHeader("Gatling MegaMonitoring QA Simulation");


    {


        ScenarioBuilder GetTasks = scenario("GetAllTasks")
                .repeat(10).on(exec(http("list tasks")
                        .get("/tasks")
                        .basicAuth("admin", "admin")
                        .transformResponse((response , session) -> {
                            if (response.status().code() == 200) {
                                LOGGER.info("http bla bla bla Response: {}", response.body().string());
                                return response;
                            } else {
                                LOGGER.info("http bla bla bla Response: {}", response.body().string());
                                return response;
                            }
                        })
                        .check(status().is(200))));

        ScenarioBuilder GetTasks1 = scenario("Taxdebt trigger ALL #296")
                .repeat(10).on(exec(http("MM: taxdebt trigger ALL ")
                        .get("/tasks")
                        .basicAuth("admin", "admin")
                        .transformResponse((response , session) -> {
                            if (response.status().code() == 200) {
                                LOGGER.info("http bla bla bla Response: {}", response.body().string());
                                return response;
                            } else {
                                LOGGER.info("http bla bla bla Response: {}", response.body().string());
                                return response;
                            }
                        })
                        .check(status().is(200))));

        ScenarioBuilder PostTasks = scenario("PostTasks")
                .repeat(2, "n").on(exec(http("post 2 tasks")
                        .post("/task")
                        .basicAuth("admin", "admin")
                        .transformResponse((response , session) -> {
                            if (response.status().code() == 200) {
                                LOGGER.info("http bla bla bla Response: {}", response.body().string());
                                return response;
                            } else {
                                LOGGER.info("http bla bla bla Response: {}", response.body().string());
                                return response;
                            }
                        })
                        .header("accept", "application/json")
                        .header("content-type", "application/json")
                        .body(StringBody("{ \"name\": \"#{n}\", \"description\": \"todo\"}"))
                        .check(status().is(200))
                                //.check(jsonPath("$.name").is(#{n}"))
                                .check(jsonPath("$.description").is("todo"))));



        setUp(
                        PostTasks.injectOpen(atOnceUsers(1)).protocols(httpProtocol).andThen(
                                GetTasks.injectOpen(constantUsersPerSec(1).during(1)).protocols(httpProtocol)
                        )
            );


    }



}
