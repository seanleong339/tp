package seedu.address.logic.parser.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICANT_MARK_STATUS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.applicant.MarkApplicant;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.ApplicantStatus;

public class MarkApplicantParser implements Parser<MarkApplicant> {
    @Override
    public MarkApplicant parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_STATUS);

        if (!argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkApplicant.MESSAGE_USAGE));
        }

        Index index;
        String status;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX,
                    MarkApplicant.MESSAGE_USAGE), pe);
        }

        try {
            status = ParserUtil.parseApplicantStatus(argMultimap.getValue(PREFIX_STATUS).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_APPLICANT_MARK_STATUS,
                    MarkApplicant.MESSAGE_USAGE), pe);
        }

        return new MarkApplicant(index, new ApplicantStatus(status));
    }
}
