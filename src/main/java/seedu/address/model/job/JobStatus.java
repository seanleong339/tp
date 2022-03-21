package seedu.address.model.job;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Job listings' availability status: filled or vacant.
 */
public class JobStatus {
    private static final String JOBSTATUS_FILLED = "filled";
    private static final String JOBSTATUS_VACANT = "vacant";

    private final Boolean isFilled;

    /**
     * Instantiates a new Job status.
     *
     * @param isFilled the is filled
     */
    public JobStatus(Boolean isFilled) {
        requireNonNull(isFilled);
        this.isFilled = isFilled;
    }

    @Override
    public String toString() {
        return isFilled
                ? JOBSTATUS_FILLED
                : JOBSTATUS_VACANT;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobStatus
            && isFilled.equals(((JobStatus) other).isFilled));
    }

    @Override
    public int hashCode() {
        return isFilled.hashCode();
    }

}
