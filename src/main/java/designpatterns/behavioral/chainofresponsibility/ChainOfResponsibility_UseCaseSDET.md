# Chain of Responsibility [Example for SDETs]

-----
The Chain of Responsibility design pattern is a behavioral pattern that allows an object to pass a request along a chain of handlers. Each handler in the chain has a chance to process the request or pass it on to the next handler in the chain. This pattern is particularly useful when you have multiple types of authentication mechanisms to handle, and you want to process them in a flexible and extensible manner.

## Scenario
Let's say you have an automation framework for RESTful APIs using RESTAssured and Java. Your framework needs to support various authentication methods like Basic Authentication, Bearer Token, and OAuth. By using the Chain of Responsibility pattern, you can handle these different authentication methods in a clean and modular way.

----
## Implementation Steps
* Define the Handler Interface
* Implement Concrete Handlers for Each Authentication Type
* Set Up the Chain of Responsibility
* Use the Chain in RESTAssured Requests

____
## Detailed Code Example
### (1) Define the Handler Interface
The handler interface defines a method to set the next handler in the chain and an abstract method to process the authentication.

```
public interface AuthenticationHandler {
void setNextHandler(AuthenticationHandler nextHandler);
void handleAuthentication(RequestSpecification requestSpec);
}
```

### (2) Implement Concrete Handlers for Each Authentication Type
Each concrete handler will implement the handleAuthentication method according to its specific authentication method.

```
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BasicAuthHandler implements AuthenticationHandler {
    private AuthenticationHandler nextHandler;
    private String username;
    private String password;

    public BasicAuthHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void setNextHandler(AuthenticationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleAuthentication(RequestSpecification requestSpec) {
        requestSpec.auth().preemptive().basic(username, password);
        if (nextHandler != null) {
            nextHandler.handleAuthentication(requestSpec);
        }
    }
}

public class BearerTokenHandler implements AuthenticationHandler {
    private AuthenticationHandler nextHandler;
    private String token;

    public BearerTokenHandler(String token) {
        this.token = token;
    }

    @Override
    public void setNextHandler(AuthenticationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleAuthentication(RequestSpecification requestSpec) {
        requestSpec.auth().oauth2(token);
        if (nextHandler != null) {
            nextHandler.handleAuthentication(requestSpec);
        }
    }
}
```

### (3) Set Up the Chain of Responsibility
Configure the chain of handlers according to the desired order of authentication mechanisms.

public class AuthenticationChain {
private AuthenticationHandler firstHandler;

```
public void setChain(AuthenticationHandler... handlers) {
        if (handlers.length == 0) return;
        AuthenticationHandler currentHandler = handlers[0];
        firstHandler = currentHandler;
        for (int i = 1; i < handlers.length; i++) {
            currentHandler.setNextHandler(handlers[i]);
            currentHandler = handlers[i];
        }
    }

    public void applyAuthentication(RequestSpecification requestSpec) {
        if (firstHandler != null) {
            firstHandler.handleAuthentication(requestSpec);
        }
    }
}
```

### (4) Use the Chain in RESTAssured Requests
Finally, use the configured chain to apply authentication in your RESTAssured requests.

```
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiTest {
    public static void main(String[] args) {
        // Set up the authentication chain
        AuthenticationChain authChain = new AuthenticationChain();
        BasicAuthHandler basicAuthHandler = new BasicAuthHandler("user", "pass");
        BearerTokenHandler bearerTokenHandler = new BearerTokenHandler("your_bearer_token");
        
        // Configure the chain
        authChain.setChain(basicAuthHandler, bearerTokenHandler);

        // Prepare the request specification
        RequestSpecification requestSpec = RestAssured.given().baseUri("https://api.example.com");

        // Apply the authentication
        authChain.applyAuthentication(requestSpec);

        // Make the API request
        requestSpec.get("/endpoint")
                   .then()
                   .statusCode(200);
    }
}
```

## Explanation
* **Handler Interface:** Defines the common interface for authentication handlers, allowing them to be linked together and process requests.

* **Concrete Handlers:** Implement specific authentication logic for Basic Auth and Bearer Token. Each handler also supports passing the request to the next handler in the chain.

* **Chain Setup:** The AuthenticationChain class manages the order of authentication handlers. It sets up the chain by linking handlers and applies them to the request.

* **Using the Chain:** In the test class (ApiTest), you configure and use the authentication chain with RESTAssured to perform API requests.

By following this approach, you achieve a modular and extensible authentication mechanism that can easily be adapted to support additional authentication types or change the order of processing as needed.