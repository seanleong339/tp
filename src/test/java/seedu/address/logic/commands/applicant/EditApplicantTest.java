package seedu.address.logic.commands.applicant;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.EditApplicantDescriptorBuilder;


import org.junit.jupiter.api.Test;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

class EditApplicantTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Applicant editedApplicant = new ApplicantBuilder().build();
//        EditCommand.EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editedApplicant).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedApplicant);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }

}