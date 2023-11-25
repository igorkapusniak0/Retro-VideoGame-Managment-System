package models;

import utils.DeveloperUtil;
import utils.PublisherUtil;
import utils.Utilities;

public class PortedGame extends Game{
    private OriginalGame originalGame;
    private PublisherUtil publisher;
    private Machine machine;
    private int releaseYear;
    private String cover;
    public PortedGame(String title, PublisherUtil publisher, String description, DeveloperUtil developer, Machine machine, int releaseYear, String cover, OriginalGame originalGame) {
        super(title,description, developer);
        setPublisher(publisher);
        setReleaseYear(releaseYear);
        setCover(cover);
        setOriginalGame(originalGame);
        setMachine(machine);
    }
    public void setMachine(Machine machine){
        this.machine=machine;
    }
    public Machine getMachine(){
        return this.machine;
    }

    public void setPublisher(PublisherUtil publisher){
        this.publisher= publisher;

    }
    public PublisherUtil getPublisher(){
        return this.publisher;
    }

    public void setReleaseYear(int releaseYear){
        if  (releaseYear>= originalGame.getReleaseYear()){
            this.releaseYear=releaseYear;
        }
        else{
            this.releaseYear= originalGame.getReleaseYear()+1;
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
    public void setOriginalGame(OriginalGame originalGame){
        if (originalGame!=null){
            this.originalGame=originalGame;
        }
    }
    public Game getOriginalGame(){
        return this.originalGame;
    }

    @Override
    public String toString() {
        return "PortedGame{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", developer='" + getDeveloper() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", machine=" + getMachine() +
                ", releaseYear=" + getReleaseYear() +
                ", cover='" + getCover() + '\'' +
                ", originalGame=" + getOriginalGame() +
                '}';
    }



}
