package patterns.builders;

import models.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import patterns.factories.LogFactory;

/**
 * Builder for creating {@link MedicalRecord} instances.
 * <p>
 * Supports incremental addition of vaccinations, treatments, and checkups
 * before constructing the immutable {@link MedicalRecord} object.
 */
public class MedicalRecordBuilder {
    private static final Logger logger = LogFactory.getLogger(MedicalRecordBuilder.class);

    private List<String> vaccinations = new ArrayList<>();
    private List<String> treatments = new ArrayList<>();
    private List<String> checkups = new ArrayList<>();

    /**
     * Adds a vaccination record.
     *
     * @param vaccine the name of the vaccination administered
     * @return this builder instance for chaining
     */
    public MedicalRecordBuilder addVaccination(String vaccine) {
        logger.fine("Adding vaccination: " + vaccine);
        vaccinations.add(vaccine);
        return this;
    }

    /**
     * Adds a treatment record.
     *
     * @param treatment the description of treatment provided
     * @return this builder instance for chaining
     */
    public MedicalRecordBuilder addTreatment(String treatment) {
        logger.fine("Adding treatment: " + treatment);
        treatments.add(treatment);
        return this;
    }

    /**
     * Adds a checkup record.
     *
     * @param checkup the description of the checkup performed
     * @return this builder instance for chaining
     */
    public MedicalRecordBuilder addCheckup(String checkup) {
        logger.fine("Adding checkup: " + checkup);
        checkups.add(checkup);
        return this;
    }

    /**
     * Builds a new {@link MedicalRecord} instance with all the collected data.
     *
     * @return a new {@link MedicalRecord} object
     */
    public MedicalRecord build() {
        logger.info(String.format("Building MedicalRecord with %d vaccinations, %d treatments, %d checkups.",
                vaccinations.size(), treatments.size(), checkups.size()));
        return new MedicalRecord(vaccinations, treatments, checkups);
    }
}
