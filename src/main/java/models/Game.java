package models;

import utils.Utilities;

public class Game {
    private String title;
    private String publisher;
    private String description;
    private String developer;
    private Machine originalMachine;
    private int releaseYear;
    private String cover;

    public Game(String title, String publisher, String description, String developer, Machine originalMachine, int releaseYear, String cover){
        setTitle(title);
        setPublisher(publisher);
        setDescription(description);
        setDeveloper(developer);
        setOriginalMachine(originalMachine);
        setReleaseYear(releaseYear);
        setCover(cover);
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
    public void setPublisher(String publisher){
        if (publisher!=null||publisher!=""){
            this.publisher= Utilities.truncateString(publisher,40);
        }else{
            this.publisher="Publisher Error";
        }
    }
    public String getPublisher(){
        return this.publisher;
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
    public void setDeveloper(String developer){
        if (developer!=null||developer!=""){
            this.developer= Utilities.truncateString(developer,40);
        }else{
            this.developer="Developer Error";
        }
    }
    public String getDeveloper(){
        return this.developer;
    }
    public void setOriginalMachine(Machine machine){
        if (machine!=null){
            this.originalMachine=machine;
        }else{
            this.originalMachine = new Machine("","","","","", 0, 0,"");
        }
    }
    public Machine getOriginalMachine(){
        return this.originalMachine;
    }
    public void setReleaseYear(int releaseYear){
        if (releaseYear>=1950){
            this.releaseYear=releaseYear;
        }else{
            this.releaseYear=2000;
        }
    }
    public int getReleaseYear(){
        return this.releaseYear;
    }
    public void setCover(String cover){
        if (cover!=null||cover!=""){
            this.cover= Utilities.truncateString(cover,50);
        }else{
            this.cover="Cover Error";
        }
    }
    public String getCover(){
        return this.cover;
    }



}
