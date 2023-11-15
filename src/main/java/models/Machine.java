package models;

import utils.Utilities;

public class Machine {
    private String name;
    private String manufacturer;
    private String description;
    private String type;
    private String media;
    private int launchYear;
    private double price;
    private String image;

    public Machine(String name, String manufacturer,String description,String type,String media,int launchYear, double price,String image){
        setName(name);
        setDescription(description);
        setManufacturer(manufacturer);
        setType(type);
        setMedia(media);
        setLaunchYear(launchYear);
        setPrice(price);
        setImage(image);
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
    public void setManufacturer(String manufacturer){
        if (manufacturer!=""||manufacturer!=null){
            this.manufacturer=Utilities.truncateString(manufacturer,20);
        }else{
            this.manufacturer="Manufacturer Error";
        }
    }
    public String getManufacturer(){
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
            this.price=price;
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
}
