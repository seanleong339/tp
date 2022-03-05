package seedu.address.model.applicant;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
    private final String dateInterview;
    private final String job;
    private final String qualification;

    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     String dateApplied, String dateInterview, Nric nric, String job, String qualification) {
        requireAllNonNull(name, phone, email, address, tags, dateApplied, dateInterview, nric, job, qualification);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.nric = nric;
        this.dateApplied = dateApplied;
        this.dateInterview = dateInterview;
        this.job = job;
        this.qualification = qualification;
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

    public String getDateInterview() {
        return dateInterview;
    }

    public String getJob() {
        return job;
    }

    public String getQualification() {
        return qualification;
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
