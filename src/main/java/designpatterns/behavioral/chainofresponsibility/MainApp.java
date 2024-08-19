package designpatterns.behavioral.chainofresponsibility;

import designpatterns.behavioral.chainofresponsibility.handlers.Handler;
import designpatterns.behavioral.chainofresponsibility.handlers.RoleCheckHandler;
import designpatterns.behavioral.chainofresponsibility.handlers.UserExistsHandler;
import designpatterns.behavioral.chainofresponsibility.handlers.ValidPasswordHandler;

public class MainApp {

    public static void main(String[] args) {
        Database database = new Database();
        Handler handler = new UserExistsHandler(database);
        handler.setNextHandler(new ValidPasswordHandler(database))
                .setNextHandler(new RoleCheckHandler());
        AuthService service = new AuthService(handler);

        System.out.println("==========================================");

        System.out.println(service.logIn("username", "password"));

        System.out.println("==========================================");

        System.out.println(service.logIn("user_username", "password"));

        System.out.println("==========================================");

        System.out.println(service.logIn("admin_username", "admin_password"));

        System.out.println("==========================================");

    }
}
