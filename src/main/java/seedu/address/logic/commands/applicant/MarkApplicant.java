package seedu.address.logic.commands.applicant;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantStatus;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

public class MarkApplicant extends Command {
    public static final String COMMAND_WORD = "markapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an existing Applicant's status. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_DATEAPPLIED + "DATE APPLIED "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NRIC + "S12345678D "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_DATEAPPLIED + "21-3-2022 "
            + PREFIX_TAG + "new applicant "
            + PREFIX_TAG + "owesMoney";

    private static final String MESSAGE_SUCCESS = "Updated Applicant %1$s's status to: %2$s";

    private final Index index;
    private final ApplicantStatus applicantStatus;

    /**
     * Instantiates a new MarkApplicant to update the ApplicantStatus to a specified {@code ApplicantStatus}
     *
     * @param applicantStatus the updated applicant status
     */
    public MarkApplicant(Index index, ApplicantStatus applicantStatus) {
        requireAllNonNull(index, applicantStatus);

        this.index = index;
        this.applicantStatus = applicantStatus;
    }

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToMark = lastShownList.get(index.getZeroBased());
        Applicant markedApplicant = applicantToMark.updateApplicantStatus(applicantStatus);

        model.setApplicant(applicantToMark, markedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, applicantToMark, applicantStatus));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MarkApplicant)
                && applicantStatus.equals(((MarkApplicant) other).applicantStatus);
    }
}
