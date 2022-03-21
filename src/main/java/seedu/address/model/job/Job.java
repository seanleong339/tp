package seedu.address.model.job;

public class Job {

    /**
     * Returns true if both Jobs have the same title and company.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && otherJob.getJobTitle().equals(getJobTitle())
                && otherJob.getCompany().equals(getCompany());
    }
}
