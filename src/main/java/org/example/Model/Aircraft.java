package org.example.Model;

public class Aircraft {
    private String number;
    private String model;
    private String registration;
    private String technicalStatus;
    private String aircraftStatus;

    public Aircraft(String number, String model, String registration) {
        this.number = number;
        this.model = model;
        this.registration = registration;
        this.technicalStatus = null;
        this.aircraftStatus = null;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getRegistration() {
        return registration;
    }

    public String getTechnicalStatus() {
        return technicalStatus;
    }

    public String getAircraftStatus() {
        return aircraftStatus;
    }

    public void setTechnicalStatus(String technicalStatus) {
        this.technicalStatus = technicalStatus;
    }

    public void setAircraftStatus(String aircraftStatus) {
        this.aircraftStatus = aircraftStatus;
    }
}
