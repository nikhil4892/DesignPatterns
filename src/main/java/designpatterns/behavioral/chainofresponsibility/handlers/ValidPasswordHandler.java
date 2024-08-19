package designpatterns.behavioral.chainofresponsibility.handlers;

import designpatterns.behavioral.chainofresponsibility.Database;

public class ValidPasswordHandler extends Handler{
    private Database database;

    public ValidPasswordHandler(Database database){this.database = database;}

    @Override
    public boolean handle(String username, String password) {
        if(!database.isValidPassword(username, password)){
            System.out.println("Invalid password to login..");
            return false;
        }

        return handleNext(username, password);
    }
}
