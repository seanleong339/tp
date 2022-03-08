package seedu.address.model.applicant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class Applicant {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Nric nric;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final String dateApplied;
    private final InterviewDate interviewDate;
    private final String job;
    private final String qualification;
    private final InterviewStatus interviewStatus;
    private final String applicationStatus;

    /**
     * Creates an Applicant object with all attributes
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     String dateApplied, InterviewDate interviewDate, Nric nric, String job,
                     String qualification, InterviewStatus interviewStatus, String applicationStatus) {
        requireAllNonNull(name, phone, email, address, tags, dateApplied, interviewDate, nric, job, qualification);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.nric = nric;
        this.dateApplied = dateApplied;
        this.interviewDate = interviewDate;
        this.job = job;
        this.qualification = qualification;
        this.interviewStatus = interviewStatus;
        this.applicationStatus = applicationStatus;
    }

    /**
     * Creates an Applicant object with minimum required attributes
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     String dateApplied, Nric nric, String job) {
        requireAllNonNull(name, phone, email, address, tags, dateApplied, nric, job);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.nric = nric;
        this.dateApplied = dateApplied;
        this.job = job;
        this.interviewDate = new InterviewDate();
        this.qualification = null;
        this.interviewStatus = new InterviewStatus();
        this.applicationStatus = null;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Nric getNric() {
        return nric;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public InterviewDate getDateInterview() {
        return interviewDate;
    }

    public String getJob() {
        return job;
    }

    public String getQualification() {
        return qualification;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Nric: ")
                .append(getNric())
                .append("; Date applied: ")
                .append(getDateApplied())
                .append("; Date of interview: ")
                .append(getDateInterview())
                .append("; Job: ")
                .append(getJob())
                .append("; Qualification: ")
                .append(getQualification());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
