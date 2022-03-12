package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.DeleteApplicant;


public class DeleteApplicantParserTest {
    private DeleteApplicantParser parser = new DeleteApplicantParser();

    @Test
    public void parse_validArgs_returnsDeleteApplicant() {
        assertParseSuccess(parser, "1", new DeleteApplicant(INDEX_FIRST_APPLICANT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicant.MESSAGE_USAGE));
    }
}
