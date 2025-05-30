package models;

/**
 * Represents entities that can be treated medically.
 * <p>
 * Provides methods to add and retrieve medical records.
 * </p>
 */
public interface Treatable {

    /**
     * Adds a medical record to this treatable entity.
     *
     * @param record the medical record to add
     */
    void addMedicalRecord(MedicalRecord record);

    /**
     * Retrieves the current medical record associated with this entity.
     *
     * @return the medical record, or {@code null} if none exists
     */
    MedicalRecord getMedicalRecord();
}
