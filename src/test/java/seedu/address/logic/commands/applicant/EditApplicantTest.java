package seedu.address.logic.commands.applicant;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
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
        Model modelTest = new ModelManager(TypicalApplicants.getTypicalAddressBook(), new UserPrefs());
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editedApplicant).build();
        EditApplicant editApplicant = new EditApplicant(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditApplicant.MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant);

        Model expectedModel = new ModelManager(new AddressBook(modelTest.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(modelTest.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editApplicant, modelTest, expectedMessage, expectedModel);
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
}
