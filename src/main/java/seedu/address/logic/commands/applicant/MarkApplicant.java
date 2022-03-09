package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks the applicant's interview and/or "
            + "application status "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INTERVIEW_STATUS + " "
            + PREFIX_APPLICATION_STATUS + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERVIEW_STATUS + " "
            + PREFIX_APPLICATION_STATUS;

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Unmark applicant %1$d success. Interview status"
            + "Interview status is marked: %2$b. Application status is unmarked: %3$d";

    private final int id;
    private final boolean markInterviewStatus;
    private final int applicationStatus;

    /**
      * Creates an UnmarkApplicant to unmark {@code interviewStatus} and
      * an applicant with specified {@code interviewStatus}
      */
    // TODO: change int applicationStatus to ApplicationStatus applicationStatus
    public MarkApplicant(Integer id, boolean markInterviewStatus, int applicationStatus) {
        requireNonNull(id);
        this.id = id;
        this.markInterviewStatus = markInterviewStatus;
        this.applicationStatus = applicationStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO: - Implement Applicant List
        //       - Get applicant by the given id
        //       - Set unmarked applicant to the applicant list

        /*
        List<Applicant> lastShownList = model.getFilteredApplicantList();
        Applicant applicantToUnmark = lastShownList.get();
        Applicant unmarkedApplicant = createUnmarkApplicant(applicantToUnmark);
        model.setApplicant(applicantToUnmark, unmarkedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        */
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, id, markInterviewStatus, applicationStatus));
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
