package models.forms;

/**
 * Represents an adoption form used in the animal shelter system.
 * <p>
 * This interface defines the basic contract for adoption forms,
 * including submitting the form and retrieving its details.
 * Implementations of this interface should provide the specific
 * behavior for form submission and how details are formatted or returned.
 * </p>
 */
public interface AdoptionForm {

    /**
     * Submits the adoption form.
     * <p>
     * Implementations should handle all necessary actions upon form submission,
     * such as saving the form data, notifying relevant parties, or triggering
     * workflow processes.
     * </p>
     */
    void submit();

    /**
     * Returns detailed information about the adoption form.
     * <p>
     * This method should provide a string representation of the form's content,
     * which might include adopter information, animal details, and any other
     * relevant data collected in the form.
     * </p>
     *
     * @return a {@code String} containing the details of the adoption form
     */
    String getDetails();
}
