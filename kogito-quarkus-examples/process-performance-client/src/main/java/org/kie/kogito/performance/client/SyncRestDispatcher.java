package org.kie.kogito.performance.client;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import io.vertx.core.http.HttpMethod;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpRequest;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;

public class SyncRestDispatcher implements RequestDispatcher {

    private HttpRequest<Buffer> request;
    private WebClient webClient;
    private Vertx vertx;

    public SyncRestDispatcher(String processId) {
        this.vertx = Vertx.vertx();
        this.webClient = WebClient.create(vertx);
        this.request = webClient.request(HttpMethod.POST, 8080, "localhost", '/' + processId);
    }

    @Override
    public void dispatch(long delay, Consumer<Throwable> callback) {
        HttpResponse<Buffer> response = request.sendJsonAndAwait(Collections.singletonMap("delay", delay));
        if (response.statusCode() != 201) {
            callback.accept(new IllegalStateException(response.statusMessage()));
        }
    }

    @Override
    public void close() throws InterruptedException, ExecutionException {
        webClient.close();
        vertx.close();
    }

}
