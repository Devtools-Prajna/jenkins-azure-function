package com.example;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

public class Function {
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {

        context.getLogger().info("Java HTTP trigger processed a request.");

        String name = request.getQueryParameters().get("name");
        if (name == null) {
            name = request.getBody().orElse("world");
        }

        return request.createResponseBuilder(HttpStatus.OK)
            .body("Hello, " + name)
            .build();
    }
}
