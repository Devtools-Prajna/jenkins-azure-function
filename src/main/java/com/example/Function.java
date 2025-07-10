package com.example;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

public class Function {
    @FunctionName("RootHandler")
    public HttpResponseMessage run(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.GET},
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "" // <-- root path
        ) HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context
    ) {
        context.getLogger().info("Root URL accessed.");
        return request.createResponseBuilder(HttpStatus.OK)
            .body("Welcome to the homepage powered by Azure Functions!")
            .build();
    }
}
