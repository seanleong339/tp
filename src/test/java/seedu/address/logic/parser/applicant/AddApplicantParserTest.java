package seedu.address.logic.parser.applicant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class AddApplicantParserTest {

    @Test
    void parse() {
        String arg = " n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, "
                + "#02-25 q/Bachelors d/2022-12-12 j/123456 i/2022-12-14 nric/S1234567D";
        String expected = "John Doe; Phone: 98765432; Email: johnd@example.com; Address: 311, Clementi Ave 2, #02-25;"
                + " Nric: S1234567D; Date applied: 2022-12-12; Date of interview: 2022-12-14; "
                + "Job: 123456; Qualification: Bachelors";
        AddApplicantParser a = new AddApplicantParser();
        try {
            assertEquals(expected, a.parse(arg).showToAdd());
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
}
