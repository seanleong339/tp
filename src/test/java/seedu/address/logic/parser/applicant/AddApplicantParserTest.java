package seedu.address.logic.parser.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATEAPPLIED_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.JOB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.AddApplicant;
import seedu.address.logic.parser.exceptions.ParseException;

class AddApplicantParserTest {

    private AddApplicantParser parser = new AddApplicantParser();

    @Test
    void parse_allCompulsoryFieldsPresent_success() {
        String arg = " n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, "
                + "#02-25 q/Bachelors d/2022-12-12 j/123456 nric/S1234567D";
        String expected = "John Doe; Phone: 98765432; Email: johnd@example.com; Address: 311, Clementi Ave 2, #02-25;"
                + " Nric: S1234567D; Date applied: 2022-12-12; Date of interview: PENDING; "
                + "Job: 123456; Qualification: PENDING";
        try {
            assertEquals(expected, parser.parse(arg).showToAdd());
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    @Test
    void parse_missingField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicant.MESSAGE_USAGE);

        //Missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC + DATEAPPLIED_DESC + JOB_DESC, expectedMessage);

        //Missing nric prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_NRIC + DATEAPPLIED_DESC + JOB_DESC, expectedMessage);

        //Missing dateApplied prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + NRIC_DESC + VALID_DATE + JOB_DESC, expectedMessage);


        //Missing job prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + NRIC_DESC + DATEAPPLIED_DESC + VALID_JOB, expectedMessage);
    }
}
