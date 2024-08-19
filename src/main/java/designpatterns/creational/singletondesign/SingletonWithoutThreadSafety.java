package designpatterns.creational.singletondesign;

public class SingletonWithoutThreadSafety {
    private static SingletonWithoutThreadSafety instance;
    private String attribute;

    private SingletonWithoutThreadSafety(String attribute){
        this.attribute = attribute;
    }

    public static SingletonWithoutThreadSafety getInstance(String attribute){
        if(instance == null)
            instance = new SingletonWithoutThreadSafety(attribute);

        return instance;
    }

    public String getAttribute(){
        return attribute;
    }
}
