package models;

import storing.Hashing;
import utils.Utilities;

public class OriginalGame extends Game{
    private String publisher;
    private Machine originalMachine;
    private int releaseYear;
    private String cover;
    public OriginalGame(String title, String publisher, String description, String developer, Machine originalMachine, int releaseYear, String cover) {
        super(title, description, developer);
        setPublisher(publisher);
        setOriginalMachine(originalMachine);
        setReleaseYear(releaseYear);
        setCover(cover);
    }



    public void setPublisher(String publisher) {
        if (publisher != null || publisher != "") {
            this.publisher = Utilities.truncateString(publisher, 40);
        } else {
            this.publisher = "Publisher Error";
        }
    }
    public String getPublisher(){
        return this.publisher;
    }

    public void setOriginalMachine(Machine machine){
        if (machine!=null){
            this.originalMachine=machine;
        }else{
            this.originalMachine = new Machine("",null,"","","", 0, 0,"",null,null);
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

    @Override
    public String toString() {
        return "OriginalGame{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", developer='" + getDeveloper() + '\'' +
                ", publisher='" + getPublisher() + '\'' +
                ", originalMachine=" + getOriginalMachine() +
                ", releaseYear=" + getReleaseYear() +
                ", cover='" + getCover() + '\'' +
                '}';
    }


}
