package seedu.address.logic.parser.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICANT_ID;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.applicant.MarkApplicant;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

public class MarkApplicantParser implements Parser<MarkApplicant> {
    @Override
    public MarkApplicant parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_STATUS);

        if (argMultimap.getPreamble().isEmpty() || !argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkApplicant.MESSAGE_USAGE));
        }

        int id;
        try {
            id = ParserUtil.parseId(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(MESSAGE_INVALID_APPLICANT_ID, ive);
        }

        int status;
        status = ParserUtil.parseApplicantStatus(
                    argMultimap.getValue(PREFIX_STATUS).get());
        return new MarkApplicant(id, status);
    }

    /**
     * Returns true if there is a value mapped to  PREFIX_INTERVIEW_STATUS and/or PREFIX_APPLICATION_STATUS
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
