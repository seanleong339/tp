package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.EditApplicant;

class EditApplicantParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditApplicant.MESSAGE_USAGE);

    private EditApplicantParser parser = new EditApplicantParser();

    @Test
    void parse() {
    }
}
