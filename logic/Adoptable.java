package logic;

/**
 * Represents an entity that can be adopted. Any class implementing this interface
 * is expected to provide details about the adoptable entity.
 * <p>
 * Typically, this would be implemented by animal classes, where they represent
 * adoptable animals in a shelter or system.
 */
public interface Adoptable {

    /**
     * Returns the unique identifier for the adoptable entity.
     *
     * @return the ID of the adoptable entity
     */
    String getId();

    /**
     * Returns the name of the adoptable entity.
     *
     * @return the name of the adoptable entity
     */
    String getName();

    /**
     * Returns the age of the adoptable entity.
     *
     * @return the age of the adoptable entity
     */
    int getAge();

    /**
     * Returns the type of the adoptable entity.
     * <p>
     * This can be a species, breed, or any identifier for the entity's type.
     *
     * @return the type of the adoptable entity
     */
    String getType();

    /**
     * Returns a detailed description of the adoptable entity.
     * <p>
     * This could include additional information such as breed, health status, or any
     * other relevant data for the adopter to consider.
     *
     * @return the details of the adoptable entity
     */
    String getDetails();
}
