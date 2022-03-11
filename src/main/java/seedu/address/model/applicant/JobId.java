package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class JobId {

    /**
     * The error message for invalid job ID received.
     */
    public static final String MESSAGE_CONSTRAINTS = "Job ID can take any positive integer value, "
            + "and it should not be blank";

    /**
     * The job id.
     */
    public final Integer jobId;

    /**
     * Constructs a Job ID
     *
     * @param jobId ID of job applicant is applying for
     */
    public JobId(int jobId) {
        requireNonNull(jobId);
        checkArgument(isValidId(jobId), MESSAGE_CONSTRAINTS);
        this.jobId = jobId;
    }

    /**
     * Returns true if the input integer is a valid Id.
     */
    public static boolean isValidId(int test) {
        return test > 0;
    }

    @Override
    public String toString() {
        return "Job #" + this.jobId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.JobId
                && jobId.equals(((JobId) other).jobId));
    }

    @Override
    public int hashCode() {
        return jobId.hashCode();
    }
}
