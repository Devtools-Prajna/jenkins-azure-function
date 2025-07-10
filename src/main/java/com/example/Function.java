package com.example;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {

    @FunctionName("HttpExample")
    public HttpResponseMessage run(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.GET},
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = ""  // This maps the function to the root URL "/"
        ) HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context
    ) {
        context.getLogger().info("HTTP trigger function processed a request at root path.");
        return request
            .createResponseBuilder(HttpStatus.OK)
            .header("Content-Type", "text/plain")
            .body("Welcome to the homepage! Your Azure Function is working.")
            .build();
    }
}
