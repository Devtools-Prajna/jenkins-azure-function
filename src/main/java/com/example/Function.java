package com.example;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {

    @FunctionName("rootHandler")
    public HttpResponseMessage run(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.GET},
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = ""
        ) HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context
    ) {
        context.getLogger().info("HTTP trigger function processed a request at root path.");

        String htmlResponse = "<!DOCTYPE html>"
            + "<html lang='en'>"
            + "<head>"
            + "  <meta charset='UTF-8'>"
            + "  <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
            + "  <title>Azure Function App</title>"
            + "  <style>"
            + "    body {"
            + "      font-family: Arial, sans-serif;"
            + "      background: linear-gradient(to right, #6a11cb, #2575fc);"
            + "      color: white;"
            + "      display: flex;"
            + "      flex-direction: column;"
            + "      align-items: center;"
            + "      justify-content: center;"
            + "      height: 100vh;"
            + "      margin: 0;"
            + "    }"
            + "    h1 { font-size: 3em; margin-bottom: 0.5em; }"
            + "    p { font-size: 1.5em; }"
            + "    .button {"
            + "      margin-top: 20px;"
            + "      padding: 10px 20px;"
            + "      background-color: white;"
            + "      color: #2575fc;"
            + "      border: none;"
            + "      border-radius: 5px;"
            + "      cursor: pointer;"
            + "      font-size: 1em;"
            + "    }"
            + "    .button:hover {"
            + "      background-color: #eee;"
            + "    }"
            + "  </style>"
            + "</head>"
            + "<body>"
            + "  <h1>Welcome to Azure Function App</h1>"
            + "  <p>Your Azure Function is working!</p>"
            + "  <button class='button' onclick='alert(\"Button Clicked!\")'>Click Me</button>"
            + "</body>"
            + "</html>";

        return request
            .createResponseBuilder(HttpStatus.OK)
            .header("Content-Type", "text/html")
            .body(htmlResponse)
            .build();
    }
}
