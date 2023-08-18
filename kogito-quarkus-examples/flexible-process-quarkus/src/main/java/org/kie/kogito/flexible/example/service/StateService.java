package org.kie.kogito.flexible.example.service;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.flexible.example.model.Questionnaire;
import org.kie.kogito.flexible.example.model.State;
import org.kie.kogito.flexible.example.model.SupportCase;

@ApplicationScoped
public class StateService {

    public void resolve() {
    }

    public SupportCase resolve(SupportCase supportCase) {
        return new SupportCase(supportCase).setState(State.RESOLVED);
    }

    public SupportCase close(SupportCase supportCase, Integer evaluation, String comment) {
        return new SupportCase(supportCase)
                .setQuestionnaire(new Questionnaire().setComment(comment).setEvaluation(evaluation))
                .setState(State.CLOSED);
    }

}
