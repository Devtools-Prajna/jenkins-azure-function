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
            + "  <title>Azure Function Java</title>"
            + "  <style>"
            + "    body {"
            + "      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;"
            + "      background-color: #f0f4f8;"
            + "      display: flex;"
            + "      justify-content: center;"
            + "      align-items: center;"
            + "      height: 100vh;"
            + "      margin: 0;"
            + "    }"
            + "    .card {"
            + "      background-color: #ffffff;"
            + "      padding: 30px 40px;"
            + "      border-radius: 12px;"
            + "      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);"
            + "      text-align: center;"
            + "    }"
            + "    h1 {"
            + "      color: #333;"
            + "      margin-bottom: 10px;"
            + "    }"
            + "    p {"
            + "      font-size: 1.1em;"
            + "      color: #666;"
            + "    }"
            + "    .btn {"
            + "      margin-top: 20px;"
            + "      padding: 10px 25px;"
            + "      background-color: #0078d7;"
            + "      color: #fff;"
            + "      border: none;"
            + "      border-radius: 5px;"
            + "      cursor: pointer;"
            + "      font-size: 1em;"
            + "    }"
            + "    .btn:hover {"
            + "      background-color: #005ea6;"
            + "    }"
            + "  </style>"
            + "</head>"
            + "<body>"
            + "  <div class='card'>"
            + "    <h1>Azure Function - Java</h1>"
            + "    <p>This is a simple Java-based Azure Function deployed successfully!</p>"
            + "    <button class='btn' onclick='alert(\"You clicked the button!\")'>Click Me</button>"
            + "  </div>"
            + "</body>"
            + "</html>";

        return request
            .createResponseBuilder(HttpStatus.OK)
            .header("Content-Type", "text/html")
            .body(htmlResponse)
            .build();
    }
}
