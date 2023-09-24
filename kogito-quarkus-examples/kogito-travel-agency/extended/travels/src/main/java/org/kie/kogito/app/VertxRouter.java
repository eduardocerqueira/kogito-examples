package org.kie.kogito.app;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FaviconHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.StaticHandler;

import static io.vertx.core.http.HttpMethod.GET;

@ApplicationScoped
public class VertxRouter {

    @Location("index")
    Template indexTemplate;

    @Inject
    Vertx vertx;

    void setupRouter(@Observes Router router) {
        router.route().handler(LoggerHandler.create());
        router.route().handler(FaviconHandler.create(vertx));
        router.route().handler(StaticHandler.create());
        String indexPage = indexTemplate.render();
        router.route(GET, "/").handler(ctx -> ctx.response().end(indexPage));
    }
}
