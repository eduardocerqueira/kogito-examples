package org.kie.kogito.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieRuntimeBuilder;
import org.kie.api.runtime.KieSession;

@Path("/find-approved")
public class FindApprovedLoansEndpoint {

    @Inject
    KieRuntimeBuilder kieRuntimeBuilder;

    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<LoanApplication> executeQuery(LoanDto loanDto) {
        KieSession session = kieRuntimeBuilder.newKieSession();

        List<LoanApplication> approvedApplications = new ArrayList<>();
        session.setGlobal("approvedApplications", approvedApplications);
        session.setGlobal("maxAmount", loanDto.getMaxAmount());

        loanDto.getLoanApplications().forEach(session::insert);
        session.fireAllRules();

        return approvedApplications;
    }
}
