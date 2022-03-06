package seedu.address.logic.commands.applicant;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEAPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEINTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddApplicant extends Command {

    public static final String COMMAND_WORD = "addapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Applicant to ReCLIne. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_QUALIFICATION + "QUALIFICATION "
            + PREFIX_DATEAPPLIED + "DATE APPLIED "
            + PREFIX_JOB + "JOB "
            + PREFIX_DATEINTERVIEW + "DATE OF INTERVIEW "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NRIC + "S12345678D "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_QUALIFICATION + "Bachelors "
            + PREFIX_DATEAPPLIED + "21-3-2022 "
            + PREFIX_JOB + "#123456"
            + PREFIX_DATEINTERVIEW + "30-3-2022 "
            + PREFIX_TAG + "new applicant "
            + PREFIX_TAG + "owesMoney";

    private final Applicant toAdd;

    public AddApplicant(Applicant toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        System.out.println(this.toString());
        return new CommandResult(String.format("Testing applicant add Parser " + toAdd));
    }

    public String showToAdd() {
        return toAdd.toString();
    }
}
