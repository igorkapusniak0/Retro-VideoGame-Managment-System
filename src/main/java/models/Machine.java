package models;

import storing.Hashing;
import utils.ManufacturerUtil;
import utils.Utilities;

import java.io.Serializable;

public class Machine implements Serializable {
    private String name;
    private ManufacturerUtil manufacturer;
    private String description;
    private String type;
    private String media;
    private int launchYear;
    private double price;
    private String image;
    public Hashing<OriginalGame> originalGames;
    public Hashing<PortedGame> portedGames;
    public Machine(String name, ManufacturerUtil manufacturer,String description,String type,String media,int launchYear, double price,String image,Hashing originalGames, Hashing portedGames){
        setName(name);
        setDescription(description);
        setManufacturer(manufacturer);
        setType(type);
        setMedia(media);
        setLaunchYear(launchYear);
        setPrice(price);
        setImage(image);
        setPortedGames(portedGames);
        setOriginalGames(originalGames);
    }
    public void setName(String name){
        if (name!=null||name!=""){
            this.name= Utilities.truncateString(name,20);
        }else{
            this.name="Name Error";
        }
    }
    public String getName(){
        return this.name;
    }
    public void setDescription(String description){
        if (description!=""||description!=null){
            this.description=Utilities.truncateString(description,200);
        }else{
            this.description="Description Error";
        }
    }
    public String getDescription(){
        return this.description;
    }
    public void setManufacturer(ManufacturerUtil manufacturer){
        this.manufacturer=manufacturer;
    }
    public ManufacturerUtil getManufacturer(){
        return this.manufacturer;
    }
    public void setType(String type){
        if (type!=null||type!=""){
            this.type=Utilities.truncateString(type,20);
        }else {
            this.type="Type Error";
        }
    }
    public String getType(){
        return this.type;
    }
    public void setMedia(String media){
        if (media!=null||media!=""){
            this.media=Utilities.truncateString(media,20);
        }else {
            this.type="Media Error";
        }
    }
    public String getMedia(){
        return this.media;
    }
    public void setLaunchYear(int launchYear){
        if (launchYear>=1950){
            this.launchYear=launchYear;
        }else {
            this.launchYear=2000;
        }
    }
    public int getLaunchYear(){
        return this.launchYear;
    }
    public void setPrice(double price){
        if (price>=0){
            this.price=Utilities.toTwoDecimalPlaces(price);
        }else{
            this.price=0;
        }
    }
    public double getPrice(){
        return this.price;
    }
    public void setImage(String image){
        if (image!=null||image!=""){
            this.image=Utilities.truncateString(image,50);
        }else {
            this.image="Media Error";
        }
    }
    public String getImage(){
        return this.image;
    }
    public void addGame(OriginalGame game){
        originalGames.add(game);
    }
    public void removeGame(OriginalGame originalGame){
        originalGames.remove(originalGame);
    }
    public void addPortedGame(PortedGame portedGame){
        portedGames.add(portedGame);
    }
    public void removePortedGame(PortedGame portedGame){
        portedGames.remove(portedGame);
    }

    public void setOriginalGames(Hashing originalGames){
        this.originalGames = originalGames;
    }
    public Hashing<OriginalGame> getGames(){
        return this.originalGames;
    }
    public void setPortedGames(Hashing portedGames){
        this.portedGames= portedGames;
    }
    public Hashing<PortedGame> getPortedGames(){
        return this.portedGames;
    }

    public String toString() {
        return "Machine{" +
                "name='" + name + '\'' +
                ", manufacturer=" + manufacturer +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", media='" + media + '\'' +
                ", launchYear=" + launchYear +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", games=" + originalGames +
                ", portedGames=" + portedGames +
                '}';
    }


}
