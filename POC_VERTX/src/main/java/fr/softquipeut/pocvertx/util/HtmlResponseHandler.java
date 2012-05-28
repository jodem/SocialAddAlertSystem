/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.softquipeut.pocvertx.util;

import fr.softquipeut.pocvertx.exceptions.ClientReadableException;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

/**
 *
 * @author Joce
 */
public abstract class HtmlResponseHandler implements Handler<HttpServerRequest> {

    public void handle(HttpServerRequest request) {
        request.response.headers().put("Content-Type", "text/html");
        try {
            handleRequest(request);
        } catch (ClientReadableException exp) {
            request.response.statusCode = exp.getErrorCode();
            request.response.statusMessage = exp.getMessage();
            request.response.end();
        }
    }

    public abstract void handleRequest(HttpServerRequest request);
}

