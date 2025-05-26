package patterns.builders;

import models.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

public class MedicalRecordBuilder {
    private static final Logger logger = LogFactory.getLogger(MedicalRecordBuilder.class);

    private List<String> vaccinations = new ArrayList<>();
    private List<String> treatments = new ArrayList<>();
    private List<String> checkups = new ArrayList<>();

    public MedicalRecordBuilder addVaccination(String vaccine) {
        logger.fine(STR."Adding vaccination: \{vaccine}");
        vaccinations.add(vaccine);
        return this;
    }

    public MedicalRecordBuilder addTreatment(String treatment) {
        logger.fine(STR."Adding treatment: \{treatment}");
        treatments.add(treatment);
        return this;
    }

    public MedicalRecordBuilder addCheckup(String checkup) {
        logger.fine(STR."Adding checkup: \{checkup}");
        checkups.add(checkup);
        return this;
    }

    public MedicalRecord build() {
        logger.info(STR."Building MedicalRecord with \{vaccinations.size()} vaccinations, \{treatments.size()} treatments, \{checkups.size()} checkups.");
        return new MedicalRecord(vaccinations, treatments, checkups);
    }
}
