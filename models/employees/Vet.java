package models.employees;

import models.employees.Employee;

import java.time.LocalDate;
import java.util.UUID;

public class Vet extends Employee {

    private String practiceNo;

    public Vet() {
        super();
    }

    public Vet(String name, LocalDate dateOfBirth, String phoneNo, String email, LocalDate schedule, String practiceNo) {
        super(name, dateOfBirth, phoneNo, email, schedule);
        this.practiceNo = practiceNo;
    }

    public void generatePracticeNo() {
        if (practiceNo == null) {
            this.practiceNo = "VET-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public String getPracticeNo() {
        return practiceNo;
    }

    public void setPracticeNo(String practiceNo) {
        this.practiceNo = practiceNo;
    }

    @Override
    public String toString() {
        return "Vet - " + super.toString();
    }
}
