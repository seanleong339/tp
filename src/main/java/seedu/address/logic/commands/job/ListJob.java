package seedu.address.logic.commands.job;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ListJob extends Command {
    public static final String COMMAND_WORD = "listjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the Applicant tab.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Switched to Applicant tab.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false, false, true);
    }
}
