package models;

public interface Treatable {
    void addMedicalRecord(MedicalRecord record);
    MedicalRecord getMedicalRecord();
}