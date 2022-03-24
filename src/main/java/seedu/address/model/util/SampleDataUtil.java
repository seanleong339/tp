package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantStatus;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alice Tan"), new Phone("98567843"), new Email("alicetan@example.com"),
                        new Address("123, Jurong West Ave 6, #08-111"), getTagSet("Applicant"),
                        new DateApplied("2022-02-12"), new Nric("S9920202A"), new JobId("1"),
                        new InterviewDate("2022-03-18"), new Qualification("Degree in Sociology"),
                        new ApplicantStatus("1")),
            new Applicant(new Name("Benson Goh"), new Phone("98184343"), new Email("bensongoh@example.com"),
                    new Address("311, Clementi Ave 2, #02-25"), getTagSet("Applicant"),
                    new DateApplied("2022-01-22"), new Nric("S9920202A")),
            new Applicant(new Name("Carl Teo"), new Phone("83127843"), new Email("carlteo@example.com"),
                    new Address("456, Hougang North Street 6, #03-21"), getTagSet("Applicant"),
                    new DateApplied("2022-01-19"), new Nric("S9920202A"), new JobId("1"),
                    new InterviewDate("2022-02-10"), new Qualification("Degree in Psychology"),
                    new ApplicantStatus("3")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Applicant sampleApplicant : getSampleApplicants()) {
            sampleAb.addApplicant(sampleApplicant);
        }
        sampleAb.setIdCount(9);
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
