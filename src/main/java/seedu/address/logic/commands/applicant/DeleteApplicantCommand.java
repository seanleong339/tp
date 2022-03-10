package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Id;
import seedu.address.model.applicant.UniqueApplicantList;


public class DeleteApplicantCommand extends Command {
    public static final String COMMAND_WORD = "deleteapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant identified by the id of the applicant.\n"
            + "Parameters: ID (must be the unique id of the applicant)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPLICANT_SUCCESS = "Deleted Applicant: %1$s";

    private final Id uniqueID;

    public DeleteApplicantCommand(Id id) {
        this.uniqueID = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //List<Person> lastShownList = model.getFilteredPersonList();
        UniqueApplicantList lastShownList = model.getApplicantList();

        if (!lastShownList.containsById(uniqueID)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Applicant applicantToDelete = lastShownList.getApplicantById(uniqueID);
        model.deleteApplicant(applicantToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicantCommand // instanceof handles nulls
                && uniqueID.equals(((DeleteApplicantCommand) other).uniqueID)); // state check
    }
}
