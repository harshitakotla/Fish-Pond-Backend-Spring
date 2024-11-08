package com.admin.pondmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "ponds") // Specifies the MongoDB collection name
public class Pond {

    @Id // Marks this field as the primary key
    private String id;
    private String name;
    private String location; // New field for location
    private String size; // New field for size
    private List<Sensor> sensors = new ArrayList<>();

    @CreatedDate // Automatically set the creation timestamp
    private Date createdDate;

    @LastModifiedDate // Automatically set the last modification timestamp
    private Date lastModifiedDate;

    // Default constructor (required by MongoDB and Spring Data)
    public Pond() {}

    // Constructor with ID, name, location, and size
    public Pond(String id, String name, String location, String size) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.size = size;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    // Method to add a single sensor to the pond
    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }
}