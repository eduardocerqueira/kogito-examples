package org.kie.kogito.examples;

import io.quarkus.funqy.Funq;

public class PlainJsonFunction {

    @Funq
    public Output plainJsonFunction(Input input) {
        return new Output("Greetings from Serverless Workflow, " + input.getName());
    }

}
