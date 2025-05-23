package domain;

import java.util.List;

public class MedicalRecord {
    private List<String> vaccinations;
    private List<String> treatments;
    private List<String> checkups;

    public MedicalRecord(List<String> vaccinations, List<String> treatments, List<String> checkups) {
        this.vaccinations = vaccinations;
        this.treatments = treatments;
        this.checkups = checkups;
    }

    public List<String> getVaccinations() {
        return vaccinations;
    }
    public void setVaccinations(List<String> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public List<String> getTreatments() {
        return treatments;
    }
    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }

    public List<String> getCheckups() {
        return checkups;
    }
    public void setCheckups(List<String> checkups) {
        this.checkups = checkups;
    }

}