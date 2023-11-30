package utils;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class DeveloperUtil implements Serializable, Comparable<DeveloperUtil> {
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

    @Override
    public int compareTo(@NotNull DeveloperUtil o) {
        return 0;
    }
}
