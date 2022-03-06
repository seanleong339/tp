package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Nric in the address book.
 */
public class Nric {

    public static final String MESSAGE_CONSTRAINTS = "Nric can take any values, and it should not be blank";

    /*
     * The first character of the Nric must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a Nric object.
     *
     * @param nric A valid Nric string
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        value = nric;
    }

    /**
     * Returns true if the input string is a valid Nric.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.Nric
                && value.equals(((seedu.address.model.applicant.Nric) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

