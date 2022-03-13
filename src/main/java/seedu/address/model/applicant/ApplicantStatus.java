package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant Status of an applicant in the address book.
 */
public class ApplicantStatus {
    private static final int APPLICANTSTATUS_REJECTED = 0;
    private static final int APPLICANTSTATUS_TO_BE_INTERVIEWED = 1;
    private static final int APPLICANTSTATUS_PENDING = 2;
    private static final int APPLICANTSTATUS_ACCEPTED = 3;

    private static final String[] STATUSES = {"rejected", "to be interviewed", "pending", "accepted"};
    private static final String STATUS_OUT_OF_RANGE = "Applicant status should be within range 0 to 3.";

    public final Integer applicantStatus;

    /**
     * Constructs an Applicant Status
     *
     * @param status An applicant's job application status
     */
    public ApplicantStatus(int status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), STATUS_OUT_OF_RANGE);
        applicantStatus = status;
    }

    public boolean isValidStatus(int status) {
        return status >= APPLICANTSTATUS_REJECTED && status <= APPLICANTSTATUS_ACCEPTED;
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
