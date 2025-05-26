package models;

import java.util.List;

/**
 * Represents the medical history of an animal in the shelter.
 * <p>
 * A {@code MedicalRecord} contains lists of vaccinations, treatments, and checkups
 * associated with an animal. This information can be used to determine the health
 * status of the animal during intake, adoption, or return.
 */
public class MedicalRecord {
    private List<String> vaccinations;
    private List<String> treatments;
    private List<String> checkups;

    /**
     * Constructs a new {@code MedicalRecord} with the given lists of medical details.
     *
     * @param vaccinations list of vaccinations received
     * @param treatments list of medical treatments received
     * @param checkups list of past medical checkups
     */
    public MedicalRecord(List<String> vaccinations, List<String> treatments, List<String> checkups) {
        this.vaccinations = vaccinations;
        this.treatments = treatments;
        this.checkups = checkups;
    }

    /**
     * Returns the list of vaccinations.
     *
     * @return list of vaccinations
     */
    public List<String> getVaccinations() {
        return vaccinations;
    }

    /**
     * Sets the list of vaccinations.
     *
     * @param vaccinations new list of vaccinations
     */
    public void setVaccinations(List<String> vaccinations) {
        this.vaccinations = vaccinations;
    }

    /**
     * Returns the list of treatments.
     *
     * @return list of treatments
     */
    public List<String> getTreatments() {
        return treatments;
    }

    /**
     * Sets the list of treatments.
     *
     * @param treatments new list of treatments
     */
    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }

    /**
     * Returns the list of checkups.
     *
     * @return list of checkups
     */
    public List<String> getCheckups() {
        return checkups;
    }

    /**
     * Sets the list of checkups.
     *
     * @param checkups new list of checkups
     */
    public void setCheckups(List<String> checkups) {
        this.checkups = checkups;
    }
}
