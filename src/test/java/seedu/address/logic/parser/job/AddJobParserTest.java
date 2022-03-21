package seedu.address.logic.parser.job;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.applicant.AddApplicant;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.Nric;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class AddJobParserTest {
    private AddJobParser parser = new AddJobParser();

    @Test
    void parse_allCompulsoryFieldsPresent_success() {
        String arg = " jt/Software Engineer c/BitService Pte Ltd a/311, Clementi Ave 2 "
                + " q/Bachelors Degree js/S1234567D ";
        Set<Tag> tagSet = new HashSet<Tag>();

        Applicant app = new Applicant(new Name("John Doe"), new Phone("98765432"), new Email("johnd@example.com"),
                new Address("311, Clementi Ave 2"), tagSet, new DateApplied("2022-12-12"),
                new Nric("S1234567D")
        );
        assertParseSuccess(parser, arg, new AddApplicant(app));
    }
}