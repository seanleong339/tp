package seedu.address.logic.commands.applicant;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

public class AddApplicant extends Command {

    public static final String COMMAND_WORD = "addapplicant";

    private final Applicant toAdd;

    public AddApplicant(Applicant toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        System.out.println(this.toString());
        return new CommandResult(String.format("Testing applicant add Parser " + toAdd));
    }

    public String showToAdd(){
        return toAdd.toString();
    }
}
