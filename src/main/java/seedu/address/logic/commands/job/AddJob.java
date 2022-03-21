package seedu.address.logic.commands.job;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.applicant.AddApplicant;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Job;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

public class AddJob extends Command {
    public static final String COMMAND_WORD = "addjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Job to ReCLIne. "
            + "Parameters: "
            + PREFIX_JOB_TITLE + "JOB TITLE "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_QUALIFICATION + "QUALIFICATION "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_SALARY + "SALARY "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_JOB_TITLE + "Software Developer "
            + PREFIX_COMPANY + "Ebiz Pte Ltd "
            + PREFIX_ADDRESS + "59 Hougang Road Blk 38 "
            + PREFIX_QUALIFICATION + "Bachelors in Computer Science "
            + PREFIX_POSITION + "ft "
            + PREFIX_SALARY + "4000 ";

    public static final String MESSAGE_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the ReCLIne";

    private final Job toAdd;

    /**
     * Constructs an AddJob to add the specified {@code Job}
     */
    public AddJob(Job job) {
        requireNonNull(job);
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasJob(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        // The getIdCount() increments the idCount in addressbook
        Job jobWithId = new Job(toAdd.getJobTitle(), toAdd.getCompany(), model.getIdCount(), toAdd.getAddress(),
                toAdd.getQualification(), toAdd.getJobStatus(), toAdd.getPosition(), toAdd.getSalary());
        model.addJob(jobWithId);
        return new CommandResult(String.format(MESSAGE_SUCCESS, jobWithId));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddJob // instanceof handles nulls
                && toAdd.equals(((AddJob) other).toAdd));
    }
}
