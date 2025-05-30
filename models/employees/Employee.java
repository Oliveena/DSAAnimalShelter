package models.employees;

import java.time.LocalDate;

/**
 * Represents an employee in the animal shelter system.
 * This is an abstract base class extended by concrete employee types.
 */
public abstract class Employee {

    private String name;
    private LocalDate dateOfBirth;
    private String phoneNo;
    private String email;
    private LocalDate schedule;

    public Employee() {
        // default no-arg constructor
    }

    public Employee(String name, LocalDate dateOfBirth, String phoneNo, String email, LocalDate schedule) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNo = phoneNo;
        this.email = email;
        this.schedule = schedule;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getSchedule() {
        return schedule;
    }

    public void setSchedule(LocalDate schedule) {
        this.schedule = schedule;
    }
}
