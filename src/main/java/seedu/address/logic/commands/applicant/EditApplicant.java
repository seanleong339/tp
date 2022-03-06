package seedu.address.logic.commands.applicant;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEAPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class EditApplicant extends Command {

    public static final String COMMAND_WORD = "editApplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number used in the displayed Applicant list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
//            + "[" + PREFIX_EMAIL + "EMAIL] "
//            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_QUALIFICATION + "QUALIFICATION] "
            + "[" + PREFIX_DATEAPPLIED + "DATE APPLIED] "
            + "[" + PREFIX_JOB + "JOB ID] "
            + "[" + PREFIX_STATUS + "APPLICATION STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_QUALIFICATION + "Bachelor in Computing"
            + PREFIX_JOB + "1234";

    public static final String MESSAGE_NOT_IMPLEMENTED = "The EditApplicant feature is not completed yet.";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditCommand.EditPersonDescriptor editPersonDescriptor;

    public EditApplicant(Index index, EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireAllNonNull(index, editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_NOT_IMPLEMENTED);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditApplicant)) {
            return false;
        }

        // state check
        EditApplicant e = (EditApplicant) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
