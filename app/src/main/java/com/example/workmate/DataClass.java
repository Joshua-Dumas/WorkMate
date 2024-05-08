package com.example.workmate;

// Define a class named DataClass
public class DataClass {
    // Private member variables to hold data
    private String dataName; // Name of the data
    private String dataJob; // Job of the data
    private String dataEmail; // Email of the data
    private String dataNumber; // Number of the data
    private String dataImage; // Image of the data
    private String key; // Key for the data

    // Getter method for retrieving the key
    public String getKey() {
        return key;
    }

    // Setter method for setting the key
    public void setKey(String key) {
        this.key = key;
    }

    // Getter method for retrieving the name
    public String getDataName() {
        return dataName;
    }

    // Getter method for retrieving the job
    public String getDataJob() {
        return dataJob;
    }

    // Getter method for retrieving the email
    public String getDataEmail() {
        return dataEmail;
    }

    // Getter method for retrieving the number
    public String getDataNumber() {
        return dataNumber;
    }

    // Getter method for retrieving the image
    public String getDataImage() {
        return dataImage;
    }

    // Constructor to initialize the DataClass with data
    public DataClass(String dataName, String dataJob, String dataEmail, String dataNumber, String dataImage, String oldImageURL) {
        this.dataName = dataName; // Initialize dataName
        this.dataJob = dataJob; // Initialize dataJob
        this.dataEmail = dataEmail; // Initialize dataEmail
        this.dataNumber = dataNumber; // Initialize dataNumber
        this.dataImage = dataImage; // Initialize dataImage
    }

    // Default constructor
    public DataClass(){
    }
}
