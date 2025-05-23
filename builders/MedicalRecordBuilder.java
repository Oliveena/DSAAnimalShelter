package builders;

import domain.MedicalRecord;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordBuilder {
    private List<String> vaccinations = new ArrayList<>();
    private List<String> treatments = new ArrayList<>();
    private List<String> checkups = new ArrayList<>();

    public MedicalRecordBuilder addVaccination(String vaccine) {
        vaccinations.add(vaccine);
        return this;
    }

    public MedicalRecordBuilder addTreatment(String treatment) {
        treatments.add(treatment);
        return this;
    }

    public MedicalRecordBuilder addCheckup(String checkup) {
        checkups.add(checkup);
        return this;
    }

    public MedicalRecord build() {
        return new MedicalRecord(vaccinations, treatments, checkups);
    }
}

