package models;

import utils.DeveloperUtil;
import utils.Utilities;

public abstract class Game {
    private String title;
    private String description;
    private DeveloperUtil developer;

    public Game(String title, String description, DeveloperUtil developer){
        setTitle(title);
        setDescription(description);
        setDeveloper(developer);
    }

    public void setTitle(String title){
        if (title!=null||title!=""){
            this.title= Utilities.truncateString(title,20);
        }else{
            this.title="Title Error";
        }
    }
    public String getTitle(){
        return this.title;
    }

    public void setDescription(String description){
        if (description!=null||description!=""){
            this.description= Utilities.truncateString(description,200);
        }else{
            this.description="Description Error";
        }
    }
    public String getDescription(){
        return this.description;
    }
    public void setDeveloper(DeveloperUtil developer){
        this.developer= developer;

    }
    public DeveloperUtil getDeveloper(){
        return this.developer;
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", developer='" + developer + '\'' +
                '}';
    }

}
