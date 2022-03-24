package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.applicant.DeleteApplicant;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteApplicantParser implements Parser<DeleteApplicant> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicant command
     * and returns a DeleteApplicant object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteApplicant parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteApplicant(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteApplicant.MESSAGE_USAGE), pe);
        }
    }
}
