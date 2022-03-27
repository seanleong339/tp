package seedu.address.logic.commands.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBSTATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_JOBS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobStatus;

public class MarkJob extends Command {

    public static final String COMMAND_WORD = "markjob";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an existing job as 'filled' or 'vacant'."
            + "Parameters: "
            + PREFIX_JOBSTATUS + "JOB STATUS";
    public static final String MESSAGE_SUCCESS = "Job status updated: %1$s";
    public static final String MESSAGE_JOBSTATUS_UP_TO_DATE = "This job is already marked '%1$s'.";

    private final Index index;
    private final JobStatus jobStatus;

    /**
     * Instantiates a new Mark Job command
     *
     * @param index     the index of job to be marked
     * @param jobStatus the updated job status
     */
    public MarkJob(Index index, JobStatus jobStatus) {
        requireAllNonNull(index, jobStatus);
        this.index = index;
        this.jobStatus = jobStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job toMark = lastShownList.get(index.getZeroBased());

        if (model.jobStatusUpToDate(toMark, jobStatus)) {
            throw new CommandException(String.format(MESSAGE_JOBSTATUS_UP_TO_DATE, jobStatus.toString()));
        }

        Job markedJob = new Job(toMark, jobStatus);
        model.setJob(toMark, markedJob);
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, markedJob));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkJob // instanceof handles nulls
                && (index.equals(((MarkJob) other).index))
                    && jobStatus.equals(((MarkJob) other).jobStatus));
    }
}
