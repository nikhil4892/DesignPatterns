package designpatterns.behavioral.chainofresponsibility.handlers;

public class RoleCheckHandler extends Handler{
    @Override
    public boolean handle(String username, String password) {
        if(username.equals("admin_username")){
            System.out.println("Loading Admin Page...");
            return true;
        }

        System.out.println("Loading Default Page...");
        return handleNext(username, password);
    }
}
