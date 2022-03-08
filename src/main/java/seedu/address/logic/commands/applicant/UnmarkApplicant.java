package seedu.address.logic.commands.applicant;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.InterviewStatus;
import seedu.address.model.applicant.Nric;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Changes the remark of an existing person in the address book.
 */
public class UnmarkApplicant extends Command {
    public static final String COMMAND_WORD = "unmarkapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks the applicant's interview and/or application status "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            +  PREFIX_INTERVIEW_STATUS
            + " " +  PREFIX_APPLICATION_STATUS + "\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERVIEW_STATUS + " "
            + PREFIX_APPLICATION_STATUS;

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Unmark applicant %1$d IntStat %2$b AppStat %3$b";

    private final int id;
    private final boolean interviewStatus;
    private final boolean applicationStatus;

    /**
     * Creates an UnmarkApplicant to unmark {@code interviewStatus} and
     * an applicant with specified {@code interviewStatus}
     */
     public UnmarkApplicant(Integer id, boolean interviewStatus, boolean applicationStatus) {
        requireNonNull(id);
        this.id = id;
        this.interviewStatus = interviewStatus;
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
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, id, interviewStatus, applicationStatus));
     }

    /**
     * Returns new applicant with InterviewStatus unmark (or set to false)
     * TODO: Add a parameter for application status of Applicant
     */
    private static Applicant createUnmarkApplicant(Applicant applicantToUnmark) {
        assert applicantToUnmark != null;

        Name name = applicantToUnmark.getName();
        Phone phone =  applicantToUnmark.getPhone();
        Email email =  applicantToUnmark.getEmail();
        Address address = applicantToUnmark.getAddress();
        Set<Tag> tags = applicantToUnmark.getTags();
        String dateApplied = applicantToUnmark.getDateApplied();
        InterviewDate dateInterview = applicantToUnmark.getDateInterview();
        Nric nric = applicantToUnmark.getNric();
        String job = applicantToUnmark.getJob();
        String qualification = applicantToUnmark.getQualification();
        String applicationStauts = applicantToUnmark.getApplicationStatus();

        return new Applicant(name, phone, email, address, tags, dateApplied, dateInterview,
                nric, job, qualification, new InterviewStatus(false), applicationStauts);
    }
}