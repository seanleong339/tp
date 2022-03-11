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

    private boolean isInit;

    /**
     * Constructs a Job ID
     *
     * @param jobId ID of job applicant is applying for
     */
    public JobId(int jobId) {
        requireNonNull(jobId);
        checkArgument(isValidJobId(jobId), MESSAGE_CONSTRAINTS);
        isInit = true;
        this.jobId = jobId;
    }

    public JobId() {
        isInit = false;
        this.jobId = 10;
    }

    /**
     * Returns true if the input integer is a valid Id.
     */
    public static boolean isValidJobId(int test) {
        return test > 0;
    }

    @Override
    public String toString() {
        if (isInit) {
            return "Job #" + this.jobId;
        } else {
            return "PENDING";
        }
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
