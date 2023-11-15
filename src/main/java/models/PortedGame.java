package models;

import utils.Utilities;

public class PortedGame {
    private Game game;
    private String title;
    private String publisher;
    private String description;
    private String developer;
    private Machine[] machines;
    private int releaseYear;
    private String cover;
    public PortedGame(String publisher, String developer, Machine Machines, int releaseYear, String cover,Game game) {
        setTitle();
        setPublisher(publisher);
        setDeveloper(developer);
        setReleaseYear(releaseYear);
        setCover(cover);
        setGame(game);
    }
    public void setTitle(){
        this.title=game.getTitle();
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescription(){
        this.description=game.getDescription();
    }
    public String getDescription(){
        return this.description;
    }
    public void setPublisher(String publisher){
        if (publisher!=null||publisher!=""){
            this.publisher= Utilities.truncateString(publisher,20);
        }else{
            this.publisher="Publisher Error";
        }
    }
    public String getPublisher(){
        return this.publisher;
    }
    public void setDeveloper(String developer){
        if (developer!=null||developer!=""){
            this.developer= Utilities.truncateString(developer,20);
        }else{
            this.developer="Developer Error";
        }
    }
    public String getDeveloper(){
        return this.developer;
    }
    public void setReleaseYear(int releaseYear){
        if  (releaseYear>= game.getReleaseYear()){
            this.releaseYear=releaseYear;
        }
        else{
            this.releaseYear= game.getReleaseYear()+1;
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
    public void setGame(Game game){
        if (game!=null){
            this.game=game;
        }
    }
    public Game game(){
        return this.game;
    }


}
