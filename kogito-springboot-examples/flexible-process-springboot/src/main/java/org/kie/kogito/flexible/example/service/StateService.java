package org.kie.kogito.flexible.example.service;

import org.kie.kogito.flexible.example.model.Questionnaire;
import org.kie.kogito.flexible.example.model.State;
import org.kie.kogito.flexible.example.model.SupportCase;
import org.springframework.stereotype.Component;

@Component
public class StateService {

    public void resolve() {
    }

    public SupportCase resolve(SupportCase supportCase) {
        return new SupportCase(supportCase).setState(State.RESOLVED);
    }

    public SupportCase close(SupportCase supportCase, int evaluation, String comment) {
        return new SupportCase(supportCase)
                .setQuestionnaire(new Questionnaire().setComment(comment).setEvaluation(evaluation))
                .setState(State.CLOSED);
    }

}
