package com.krak.olympicmap.entities;

import java.util.Objects;

/*
    Модель страны, включающая её координаты, рекомендуемый зум, имя
 */
public class Country {
    private String name, russianName;
    private float longitude, latitude, zoom;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Float.compare(country.getLongitude(), getLongitude()) == 0 &&
                Float.compare(country.getLatitude(), getLatitude()) == 0 &&
                Float.compare(country.getZoom(), getZoom()) == 0 &&
                Objects.equals(getName(), country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLongitude(), getLatitude(), getZoom());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public Country(String name, String russianName, float longitude, float latitude, float zoom) {
        this.name = name;
        this.russianName = russianName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.zoom = zoom;
    }
}
