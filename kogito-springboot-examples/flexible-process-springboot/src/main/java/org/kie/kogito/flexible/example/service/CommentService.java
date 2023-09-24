package org.kie.kogito.flexible.example.service;

import org.kie.kogito.flexible.example.model.Comment;
import org.kie.kogito.flexible.example.model.State;
import org.kie.kogito.flexible.example.model.SupportCase;
import org.springframework.stereotype.Component;

import static org.kie.kogito.flexible.example.model.State.WAITING_FOR_CUSTOMER;
import static org.kie.kogito.flexible.example.model.State.WAITING_FOR_OWNER;

@Component
public class CommentService {

    public SupportCase addCustomerComment(SupportCase supportCase, String comment, String author) {
        return addComment(supportCase, author, comment, WAITING_FOR_OWNER);
    }

    public SupportCase addSupportComment(SupportCase supportCase, String comment, String author) {
        return addComment(supportCase, author, comment, WAITING_FOR_CUSTOMER);
    }

    private SupportCase addComment(SupportCase supportCase, String author, String comment, State newState) {
        SupportCase sCase = new SupportCase(supportCase).addComment(new Comment().setAuthor(author).setText(comment));
        if (State.NEW.equals(supportCase.getState())) {
            return sCase;
        }
        return sCase.setState(newState);
    }

}
