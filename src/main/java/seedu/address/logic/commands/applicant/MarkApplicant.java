package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;


/**
 * Changes the remark of an existing person in the address book.
 */
public class MarkApplicant extends Command {
    public static final String COMMAND_WORD = "markapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the applicant's "
            + "application status given ID."
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ID (must be a valid ID existing in applicant list) "
            + PREFIX_STATUS + "[STATUS]\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_STATUS + "rejected\n";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Mark Applicant: %1$s with status %2$d";

    private final Index index;
    private final int status;

    /**
      * Creates an UnmarkApplicant to unmark {@code interviewStatus} and
      * an applicant with specified {@code interviewStatus}
      */
    // TODO: change int applicationStatus to ApplicationStatus applicationStatus
    public MarkApplicant(Index index, int status) {
        requireNonNull(index);
        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }
        Applicant applicantToMark = lastShownList.get(index.getZeroBased());
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                applicantToMark.getName().toString(), status));
    }

    /*
     private static Applicant createUnmarkApplicant(Applicant applicantToUnmark) {
        assert applicantToUnmark != null;

        Name name = applicantToUnmark.getName();
        Phone phone = applicantToUnmark.getPhone();
        Email email = applicantToUnmark.getEmail();
        Address address = applicantToUnmark.getAddress();
        Set<Tag> tags = applicantToUnmark.getTags();
        DateApplied dateApplied = applicantToUnmark.getDateApplied();
        InterviewDate dateInterview = applicantToUnmark.getInterviewDate();
        Nric nric = applicantToUnmark.getNric();
        JobId job = applicantToUnmark.getJobId();
        Qualification qualification = applicantToUnmark.getQualification();
        return new Applicant(name, phone, email, address, tags, dateApplied, nric, job, dateInterview,
                qualification, new InterviewStatus(false));
    } */
}
