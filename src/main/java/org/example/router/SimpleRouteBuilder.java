/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.router;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("file:C:/Entrada").split().tokenize("\n").to("jms:queue:myQueue");
    }
    
}
