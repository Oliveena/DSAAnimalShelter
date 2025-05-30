package models.forms;

/**
 * Interface representing a form for returning an adopted animal back to the shelter.
 * <p>
 * Classes implementing this interface should provide the logic to submit
 * the return form and retrieve detailed information about the return.
 * </p>
 */
public interface ReturnToShelterForm {

    /**
     * Submits the return to shelter form.
     * <p>
     * This method triggers the actions required to process the return,
     * such as updating records or notifying shelter staff.
     * </p>
     */
    void submit();

    /**
     * Retrieves details about the return form.
     *
     * @return a string representing the details of the return to shelter
     */
    String getDetails();
}
