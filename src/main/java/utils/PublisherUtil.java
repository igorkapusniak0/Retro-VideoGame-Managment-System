package utils;

import java.io.Serializable;

public class PublisherUtil implements Serializable {
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
}
