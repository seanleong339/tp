package seedu.address.logic.commands.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.applicant.EditApplicant.EditApplicantDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.EditApplicantDescriptorBuilder;
import seedu.address.testutil.TypicalApplicants;

class EditApplicantTest {
    private Model model = new ModelManager(TypicalApplicants.getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editedApplicant).build();
        EditApplicant editApplicant = new EditApplicant(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditApplicant.MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editApplicant, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplicant = Index.fromOneBased(model.getFilteredApplicantList().size());
        Applicant lastApplicant = model.getFilteredApplicantList().get(indexLastApplicant.getZeroBased());

        ApplicantBuilder applicantInList = new ApplicantBuilder(lastApplicant);
        Applicant editedApplicant = applicantInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditApplicant editApplicant = new EditApplicant(indexLastApplicant, descriptor);

        String expectedMessage = String.format(EditApplicant.MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(lastApplicant, editedApplicant);

        assertCommandSuccess(editApplicant, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditApplicant editApplicant =
                new EditApplicant(INDEX_FIRST_PERSON, new EditApplicant.EditApplicantDescriptor());
        Applicant editedApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditApplicant.MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editApplicant, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        Applicant editedApplicant = new ApplicantBuilder(applicantInFilteredList).withName(VALID_NAME_BOB).build();
        EditApplicant editApplicant = new EditApplicant(INDEX_FIRST_PERSON,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditApplicant.MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editApplicant, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicantUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditApplicant.EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant).build();
        EditApplicant editCommand = new EditApplicant(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditApplicant.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_duplicateApplicantFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        // edit Applicant in filtered list into a duplicate in address book
        Applicant applicantInList = model.getAddressBook().getApplicantList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditApplicant editCommand = new EditApplicant(INDEX_FIRST_PERSON,
                new EditApplicantDescriptorBuilder(applicantInList).build());

        assertCommandFailure(editCommand, model, EditApplicant.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_invalidApplicantIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        EditApplicant.EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditApplicant editCommand = new EditApplicant(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidApplicantIndexFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicantList().size());

        EditApplicant editCommand = new EditApplicant(outOfBoundIndex,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditApplicant standardCommand = new EditApplicant(INDEX_FIRST_PERSON, DESC_CHARLIE);

        // same values -> returns true
        EditApplicant.EditApplicantDescriptor copyDescriptor = new EditApplicant.EditApplicantDescriptor(DESC_CHARLIE);
        EditApplicant commandWithSameValues = new EditApplicant(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditApplicant(INDEX_SECOND_PERSON, DESC_CHARLIE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditApplicant(INDEX_FIRST_PERSON, DESC_DON)));
    }
}
