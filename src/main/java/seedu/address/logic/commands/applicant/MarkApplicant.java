package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Set;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.InterviewStatus;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


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

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Mark applicant %1$d status success. status: %2$d";

    private final int id;
    private final int status;

    /**
      * Creates an UnmarkApplicant to unmark {@code interviewStatus} and
      * an applicant with specified {@code interviewStatus}
      */
    // TODO: change int applicationStatus to ApplicationStatus applicationStatus
    public MarkApplicant(Integer id, int status) {
        requireNonNull(id);
        this.id = id;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        /*
        List<Applicant> lastShownList = model.getFilteredApplicantList();
        Applicant applicantToUnmark = lastShownList.get();
        Applicant unmarkedApplicant = createUnmarkApplicant(applicantToUnmark);
        model.setApplicant(applicantToUnmark, unmarkedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        */
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, id, status));
    }

    /**
     * Returns new applicant with InterviewStatus unmark (or set to false)
     * TODO: Add a parameter for application status of Applicant
     */
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
        String job = applicantToUnmark.getJob();
        Qualification qualification = applicantToUnmark.getQualification();

        return new Applicant(name, phone, email, address, tags, dateApplied, nric, job, dateInterview,
                qualification, new InterviewStatus(false));
    }
}
