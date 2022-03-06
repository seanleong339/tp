package seedu.address.logic.commands.applicant;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.InterviewStatus;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEAPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEINTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class EditApplicant extends Command {

    public static final String COMMAND_WORD = "editapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number used in the displayed Applicant list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_NRIC + "NRIC] "
            + "[" + PREFIX_QUALIFICATION + "QUALIFICATION]\n"
            + "[" + PREFIX_DATEAPPLIED + "DATE APPLIED] "
            + "[" + PREFIX_JOB + "JOB ID] "
            + "[" + PREFIX_DATEINTERVIEW + "DATE OF INTERVIEW] "
            + "[" + PREFIX_STATUS + "APPLICATION STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_QUALIFICATION + "Bachelor in Computing "
            + PREFIX_JOB + "1234";

    public static final String MESSAGE_NOT_IMPLEMENTED = "The EditApplicant feature is not completed yet.";
    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited Applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This Applicant already exists in the address book.";

    private final Index index;
    private final EditApplicantDescriptor editApplicantDescriptor;

    public EditApplicant(Index index, EditApplicantDescriptor editApplicantDescriptor) {
        requireAllNonNull(index, editApplicantDescriptor);

        this.index = index;
        this.editApplicantDescriptor = new EditApplicantDescriptor(editApplicantDescriptor);
    }

    @Override
    // todo edit if there is going to be a Applicant class
    // todo figure out how the getFilterApplicantList will go
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person applicantToEdit = lastShownList.get(index.getZeroBased());
        Person editedApplicant = createEditedApplicant(applicantToEdit, editApplicantDescriptor);

        if (!applicantToEdit.isSamePerson(editedApplicant) && model.hasPerson(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.setPerson(applicantToEdit, editedApplicant);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editApplicantDescriptor}.
     */
    // todo edit change this to an Applicant class
    private static Person createEditedApplicant(Person applicantToEdit, EditApplicantDescriptor editApplicantDescriptor) {
        assert applicantToEdit != null;

        Name updatedName = editApplicantDescriptor.getName().orElse(applicantToEdit.getName());
        Phone updatedPhone = editApplicantDescriptor.getPhone().orElse(applicantToEdit.getPhone());
        Email updatedEmail = editApplicantDescriptor.getEmail().orElse(applicantToEdit.getEmail());
        Address updatedAddress = editApplicantDescriptor.getAddress().orElse(applicantToEdit.getAddress());
        Set<Tag> updatedTags = editApplicantDescriptor.getTags().orElse(applicantToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
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
                && editApplicantDescriptor.equals(e.editApplicantDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditApplicantDescriptor {
        private Name name;
        private Nric nric;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Qualification qualification;
        private DateApplied dateApplied;
        // todo uncomment this part when the related parts are completed
        // Job ID
        private InterviewDate interviewDate;

        public EditApplicantDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicantDescriptor(EditApplicantDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setNric(toCopy.nric);
            setInterviewDate(toCopy.interviewDate);
            setQualification(toCopy.qualification);
            setDateApplied(toCopy.dateApplied);
            // todo uncomment this part
//            setJobId(toCopy.jobId);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    name, phone, email, address, interviewDate, qualification, dateApplied, tags);
        } //Todo Add job id

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setNric(Nric nric) {
            this.nric = nric;
        }

        public Optional<Nric> getNric() {
            return Optional.ofNullable(nric);
        }

        public void setQualification(Qualification qualification) {
            this.qualification = qualification;
        }

        public Optional<Qualification> getQualification() {
            return Optional.ofNullable(qualification);
        }

        public void setDateApplied(DateApplied dateApplied) {
            this.dateApplied = dateApplied;
        }

        public Optional<DateApplied> getDateApplied() {
            return Optional.ofNullable(dateApplied);
        }
//      // todo uncomment this part
//        public void setJobId(JobId jobId) {
//            this.jobId = jobId;
//        }
//
//        public Optional<JobId> getJobId() {
//            return Optional.ofNullable(JobId);
//        }

        public void setInterviewDate(InterviewDate interviewDate) {
            this.interviewDate = interviewDate;
        }

        public Optional<InterviewDate> getInterviewDate() {
            return Optional.ofNullable(interviewDate);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicantDescriptor)) {
                return false;
            }

            // state check
            EditApplicantDescriptor e = (EditApplicantDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getNric().equals(e.getNric())
                    && getDateApplied().equals(e.getDateApplied())
                    && getInterviewDate().equals(e.getInterviewDate())
                    && getQualification().equals(e.getQualification())
                    && getTags().equals(e.getTags());
        }
    }
}
