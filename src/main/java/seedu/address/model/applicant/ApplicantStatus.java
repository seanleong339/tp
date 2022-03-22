package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant Status of an applicant in the address book.
 */
public class ApplicantStatus {

    /**
     * The error message for invalid applicant status received.
     */
    public static final String MESSAGE_CONSTRAINTS = "Application status can take any positive integer value between "
            + "0 and 3 inclusive, and it should not be blank";

    private static final int APPLICANTSTATUS_REJECTED = 0;
    private static final int APPLICANTSTATUS_PENDING = 1;
    private static final int APPLICANTSTATUS_INTERVIEWED = 2;
    private static final int APPLICANTSTATUS_ACCEPTED = 3;

    private static final String[] STATUSES = {"Rejected", "Pending", "Interviewed", "Accepted"};
    private static final String STATUS_OUT_OF_RANGE = "Applicant status should be within range 0 to 3.";

    public final Integer applicantStatus;

    /**
     * Constructs an Applicant Status
     *
     * @param status An applicant's job application status
     */
    public ApplicantStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), STATUS_OUT_OF_RANGE);
        applicantStatus = Integer.parseInt(status);
    }

    /**
     * Returns true if a given string is a valid state for application status
     */
    public static boolean isValidStatus(String status) {
        try {
            int test = Integer.parseInt(status);
            return test >= 0 && test <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return STATUSES[this.applicantStatus];
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.ApplicantStatus
                && applicantStatus.equals(((ApplicantStatus) other).applicantStatus));
    }

    @Override
    public int hashCode() {
        return applicantStatus.hashCode();
    }
}
