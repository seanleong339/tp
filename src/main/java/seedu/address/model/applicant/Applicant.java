package seedu.address.model.applicant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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
    private final DateApplied dateApplied;
    private final InterviewDate interviewDate;
    private final String job;
    private final Qualification qualification;

    /**
     * Creates an Applicant object with all attributes
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     DateApplied dateApplied, Nric nric, String job, InterviewDate interviewDate,
                     Qualification qualification) {
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
    }

    /**
     * Creates an Applicant object with minimum required attributes
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     DateApplied dateApplied, Nric nric, String job) {
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

    public DateApplied getDateApplied() {
        return dateApplied;
    }

    public InterviewDate getInterviewDate() {
        return interviewDate;
    }

    public String getJob() {
        return job;
    }

    public Qualification getQualification() {
        if (qualification == null) {
            return new Qualification("null");
        }
        return qualification;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(getName());
    }

    //todo may have to do a hard equal function to compare all the properties of Applicant
    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getAddress().equals(getAddress())
                && otherApplicant.getTags().equals(getTags())
                && otherApplicant.getDateApplied().equals(getDateApplied())
                && otherApplicant.getNric().equals(getNric())
                && otherApplicant.getJob().equals(getJob());
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
                .append(getInterviewDate())
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
