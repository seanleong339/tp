package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;



/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_NRIC = "S9901010A";
    public static final String DEFAULT_DATEAPPLIED = "8-3-2022";
    public static final String DEFAULT_INTERVIEWDATE = "15-3-2022";
    public static final String DEFAULT_JOB = "1";
    public static final String DEFAULT_QUALIFICATION = "degree in computing";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Nric nric;
    private DateApplied dateApplied;
    private InterviewDate interviewDate;
    private Qualification qualification;

    // TODO: Change this to Job class once it is ready.
    private String job;


    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        nric = new Nric(DEFAULT_NRIC);
        dateApplied = new DateApplied(DEFAULT_DATEAPPLIED);
        interviewDate = new InterviewDate(DEFAULT_INTERVIEWDATE);
        qualification = new Qualification(DEFAULT_QUALIFICATION);
        tags = new HashSet<>();

        // TODO: change this to the Job class once Job class is done.
        job = DEFAULT_JOB;
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        name = applicantToCopy.getName();
        phone = applicantToCopy.getPhone();
        email = applicantToCopy.getEmail();
        address = applicantToCopy.getAddress();
        tags = new HashSet<>(applicantToCopy.getTags());

        nric = applicantToCopy.getNric();
        dateApplied = applicantToCopy.getDateApplied();
        interviewDate = applicantToCopy.getDateInterview();
        qualification = applicantToCopy.getQualification();

        // TODO: change this to the Job class once the Job class is done.
        job = applicantToCopy.getJob();
    }

    /**
     * Sets the {@code Name} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Applicant build() {
        return new Applicant(name, phone, email, address, tags, dateApplied, nric, job, interviewDate, qualification);
    }
}
