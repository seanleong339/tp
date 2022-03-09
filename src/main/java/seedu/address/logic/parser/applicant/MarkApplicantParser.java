package seedu.address.logic.parser.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

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
                PREFIX_INTERVIEW_STATUS, PREFIX_APPLICATION_STATUS);

        // if the field for ID is empty or none of the prefixes are present, throws an error.
        if (argMultimap.getPreamble().isEmpty() || !anyPrefixesPresent(argMultimap,
                PREFIX_INTERVIEW_STATUS, PREFIX_APPLICATION_STATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkApplicant.MESSAGE_USAGE));
        }

        int id;
        // TODO: check if ID is valid meaning associated to an applicant.
        try {
            id = ParserUtil.parseId(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkApplicant.MESSAGE_USAGE), ive);
        }

        boolean unmarkInterviewStatus = false;

        // TODO ApplicationStatus = null
        int unmarkApplicationStatus = -1;

        // Checks if prefix for interview status is present.
        if (argMultimap.containsPrefix(PREFIX_INTERVIEW_STATUS)) {
            unmarkInterviewStatus = true;
        }

        // Checks if prefix for interview status is present.
        if (argMultimap.containsPrefix(PREFIX_APPLICATION_STATUS)) {
            unmarkApplicationStatus = ParserUtil.parseApplicationStatus(
                    argMultimap.getValue(PREFIX_APPLICATION_STATUS).get());
        }

        return new MarkApplicant(id, unmarkInterviewStatus, unmarkApplicationStatus);
    }

    /**
     * Returns true if there is a value mapped to  PREFIX_INTERVIEW_STATUS and/or PREFIX_APPLICATION_STATUS
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(argumentMultimap::containsPrefix);
    }
}
