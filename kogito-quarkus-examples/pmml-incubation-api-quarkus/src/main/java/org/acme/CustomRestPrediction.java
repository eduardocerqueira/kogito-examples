package org.acme;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.MapDataContext;
import org.kie.kogito.incubation.predictions.PredictionIds;
import org.kie.kogito.incubation.predictions.services.PredictionService;

@Path("/custom-rest-prediction")
public class CustomRestPrediction {

    @Inject
    AppRoot appRoot;
    @Inject
    PredictionService svc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DataContext linearRegression(Map<String, Object> payload) {
        // path: /predictions/Testregression/LinReg
        var id = appRoot
                .get(PredictionIds.class)
                .get("test_regression.pmml", "LinReg");

        MapDataContext ctx = MapDataContext.of(payload);
        return svc.evaluate(id, ctx).data();
    }

}
