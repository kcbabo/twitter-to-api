/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.example.ipaas;

import java.util.Arrays;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;



/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes
 */
@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class Application extends RouteBuilder {
	
	@Autowired
    private Bus bus;

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configure() throws Exception {
  
        from("twitter://timeline/mentions?type=polling&delay=50000" +
        		"&consumerKey={{twitter.consumer.key}}" + 
        		"&consumerSecret={{twitter.consumer.secret}}" + 
        		"&accessToken={{twitter.access.token}}" + 
        		"&accessTokenSecret={{twitter.access.token.secret}}")
        	.filter(simple("${body.text} contains 'rocker'"))
        	.log("${body.text}")
        	.to("bean:mapTweet")
        	.marshal().json(JsonLibrary.Jackson, true)
        	.setHeader("CamelHttpMethod", constant("POST"))
        	.setHeader("Content-Type", constant("application/json"))
        	.to("http4://localhost:8080/services/trader/orders");
    }

    @Bean
    public Server rsServer() {
        // setup CXF-RS
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Arrays.<Object>asList(new OrderDeskBean()));
        endpoint.setAddress("/");
        endpoint.setProviders(Arrays.<Object>asList(new JacksonJsonProvider()));
        return endpoint.create();
    }
    
    @Bean
    public DataMapping mapTweet() {
    	return new DataMapping();
    }
    
}
