package utils;

public class DeveloperUtil {
    public String developer;

    public DeveloperUtil(String developer){
        setDeveloper(developer);
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDeveloper(){
        return developer;
    }
    @Override
    public String toString(){
        return developer;
    }
}
