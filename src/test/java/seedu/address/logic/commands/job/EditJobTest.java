package seedu.address.logic.commands.job;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Job;
import seedu.address.testutil.EditJobDescriptorBuilder;
import seedu.address.testutil.JobBuilder;
import seedu.address.testutil.TypicalJobs;

class EditJobTest {
    private Model model = new ModelManager(TypicalJobs.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Job currentJob = model.getFilteredJobList().get(0);

        // EditJob should not edit the JobId. JobId should not change.
        Job editedJob = new JobBuilder().withJobId(currentJob.getJobId().toString()).build();
        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder(editedJob).build();
        EditJob editJob = new EditJob(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastJob = Index.fromOneBased(model.getFilteredJobList().size());
        Job lastJob = model.getFilteredJobList().get(indexLastJob.getZeroBased());

        JobBuilder JobInList = new JobBuilder(lastJob);
        Job editedJob = JobInList.withJobTitle(VALID_JOB_TITLE_DATA_ANALYSIS)
                .withJobAddress(VALID_ADDRESS_DATA_ANALYSIS).build();

        EditJob.EditJobDescriptor descriptor = new EditJobDescriptorBuilder()
                .withJobTitle(VALID_JOB_TITLE_DATA_ANALYSIS)
                .withAddress(VALID_ADDRESS_DATA_ANALYSIS).build();
        EditJob editJob = new EditJob(indexLastJob, descriptor);

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(lastJob, editedJob);

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditJob editJob =
                new EditJob(INDEX_FIRST_PERSON, new EditJob.EditJobDescriptor());
        Job editedJob = model.getFilteredJobList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_PERSON);

        Job JobInFilteredList = model.getFilteredJobList().get(INDEX_FIRST_PERSON.getZeroBased());
        Job editedJob = new JobBuilder(JobInFilteredList).withJobTitle(VALID_JOB_TITLE_PROJECT_MANAGER).build();
        EditJob editJob = new EditJob(INDEX_FIRST_PERSON,
                new EditJobDescriptorBuilder().withJobTitle(VALID_JOB_TITLE_PROJECT_MANAGER).build());

        String expectedMessage = String.format(EditJob.MESSAGE_EDIT_JOB_SUCCESS, editedJob);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editJob, model, expectedMessage, expectedModel);
    }





}
