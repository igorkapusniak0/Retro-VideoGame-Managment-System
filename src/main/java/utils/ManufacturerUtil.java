package utils;

public class ManufacturerUtil {
    public String manufacturer;

    public ManufacturerUtil(String manufacturer){
        setManufacturer(manufacturer);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer(){
        return manufacturer;
    }
    @Override
    public String toString(){
        return  manufacturer;
    }
}
