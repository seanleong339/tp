package seedu.address.model.job;

import seedu.address.model.job.exception.DuplicateJobException;
import seedu.address.model.job.exception.JobNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BOB;

class UniqueJobListTest {
    private final UniqueJobList uniqueJobList = new UniqueJobList();

    @Test
    public void contains_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.contains(null));
    }

    @Test
    public void contains_jobNotInList_returnsFalse() {
        assertFalse(uniqueJobList.contains(DATA));
    }

    @Test
    public void contains_jobInList_returnsTrue() {
        uniqueJobList.add(ALICE);
        assertTrue(uniqueJobList.contains(ALICE));
    }

    @Test
    public void contains_jobWithSameIdentityFieldsInList_returnsTrue() {
        uniqueJobList.add(ALICE);
        Job editedAlice = new JobBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueJobList.contains(editedAlice));
    }

    @Test
    public void add_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.add(null));
    }

    @Test
    public void add_duplicateJob_throwsDuplicateJobException() {
        uniqueJobList.add(ALICE);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.add(ALICE));
    }

    @Test
    public void setJob_nullTargetJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(null, ALICE));
    }

    @Test
    public void setJob_nullEditedJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(ALICE, null));
    }

    @Test
    public void setJob_targetJobNotInList_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.setJob(ALICE, ALICE));
    }

    @Test
    public void setJob_editedJobIsSameJob_success() {
        uniqueJobList.add(ALICE);
        uniqueJobList.setJob(ALICE, ALICE);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(ALICE);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasSameIdentity_success() {
        uniqueJobList.add(ALICE);
        Job editedAlice = new JobBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueJobList.setJob(ALICE, editedAlice);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(editedAlice);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasDifferentIdentity_success() {
        uniqueJobList.add(ALICE);
        uniqueJobList.setJob(ALICE, BOB);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(BOB);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasNonUniqueIdentity_throwsDuplicateJobException() {
        uniqueJobList.add(ALICE);
        uniqueJobList.add(BOB);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.setJob(ALICE, BOB));
    }

    @Test
    public void remove_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.remove(null));
    }

    @Test
    public void remove_jobDoesNotExist_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.remove(ALICE));
    }

    @Test
    public void remove_existingJob_removesJob() {
        uniqueJobList.add(ALICE);
        uniqueJobList.remove(ALICE);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullUniqueJobList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((UniqueJobList) null));
    }

    @Test
    public void setJobs_uniqueJobList_replacesOwnListWithProvidedUniqueJobList() {
        uniqueJobList.add(ALICE);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(BOB);
        uniqueJobList.setJobs(expectedUniqueJobList);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((List<Job>) null));
    }

    @Test
    public void setJobs_list_replacesOwnListWithProvidedList() {
        uniqueJobList.add(ALICE);
        List<Job> jobList = Collections.singletonList(BOB);
        uniqueJobList.setJobs(jobList);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(BOB);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_listWithDuplicateJobs_throwsDuplicateJobException() {
        List<Job> listWithDuplicateJobs = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateJobException.class, () ->
                uniqueJobList.setJobs(listWithDuplicateJobs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueJobList.asUnmodifiableObservableLvist().remove(0));
    }

}