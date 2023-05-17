package com.stackroute.zuulapi.filter;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class PreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        return null;
    }

//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/employee/**")
//                        //Pre and Post Filters provided by Spring Cloud Gateway
//                        .filters(f -> f.addRequestHeader("first-request", "first-request-header")
//                                .addResponseHeader("first-response", "first-response-header"))
//                        .uri("http://localhost:8081/")
//                        .id("employeeModule"))
//
//                .route(r -> r.path("/consumer/**")
//                        //Pre and Post Filters provided by Spring Cloud Gateway
//                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
//                                .addResponseHeader("second-response", "second-response-header"))
//                        .uri("http://localhost:8082/")
//                        .id("consumerModule"))
//                .build();
//    }

}
