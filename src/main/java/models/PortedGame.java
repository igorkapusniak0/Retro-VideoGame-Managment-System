package models;

import utils.Utilities;

public class PortedGame extends Game{
    private OriginalGame originalGame;
    private String publisher;
    private Machine machine;
    private int releaseYear;
    private String cover;
    public PortedGame(String title,String publisher,String description, String developer, Machine machine, int releaseYear, String cover,OriginalGame originalGame) {
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
