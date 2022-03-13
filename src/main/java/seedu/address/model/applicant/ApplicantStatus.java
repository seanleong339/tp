package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Applicant Status of an applicant in the address book.
 */
public class ApplicantStatus {
    private static final int APPLICANTSTATUS_REJECTED = 0;
    private static final int APPLICANTSTATUS_TO_BE_INTERVIEWED = 1;
    private static final int APPLICANTSTATUS_PENDING = 2;
    private static final int APPLICANTSTATUS_ACCEPTED = 3;

    private static final String[] STATUSES = {"rejected", "to be interviewed", "pending", "accepted"};

    public final Integer applicantStatus;

    /**
     * Constructs an Applicant Status
     *
     * @param status An applicant's job application status
     */
    public ApplicantStatus(int status) {
        requireNonNull(status);
        applicantStatus = status;
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
