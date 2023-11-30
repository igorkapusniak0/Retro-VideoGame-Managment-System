package utils;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class ManufacturerUtil implements Serializable, Comparable<ManufacturerUtil> {
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

    @Override
    public int compareTo(@NotNull ManufacturerUtil o) {
        return 0;
    }
}
