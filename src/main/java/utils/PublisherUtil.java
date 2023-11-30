package utils;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class PublisherUtil implements Serializable, Comparable<PublisherUtil> {
    public String publisher;

    public PublisherUtil(String publisher){
        setPublisher(publisher);
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher(){
        return publisher;
    }
    @Override
    public String toString(){
        return publisher;
    }

    @Override
    public int compareTo(@NotNull PublisherUtil o) {
        return 0;
    }
}
