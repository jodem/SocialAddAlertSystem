package fr.softquipeut.pocvertx;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.deploy.Verticle;


public class RouteMatchExample extends Verticle
{

    @Override
    public void start() {
        
       RouteMatcher rm = new RouteMatcher();
       
       rm.get("/test/:user/:id",new Handler<HttpServerRequest>() {

            public void handle(HttpServerRequest req) {
                req.response.end("User: " + req.params().get("user") + " ID: " + req.params().get("id"));
            }
        });
        
       vertx.createHttpServer().requestHandler(rm).listen(8080);
        
    }
}
