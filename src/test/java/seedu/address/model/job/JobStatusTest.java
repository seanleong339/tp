package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobStatusTest {
    private static final JobStatus JOBSTATUS_FILLED = new JobStatus(true);
    private static final JobStatus JOBSTATUS_VACANT = new JobStatus(false);

    @Test
    public void checkJobStatusValidity() {
        // null job status
        assertThrows(NullPointerException.class, () -> new JobStatus(null));

        // valid job status
        assertEquals(JOBSTATUS_FILLED, new JobStatus(true));
        assertEquals(JOBSTATUS_VACANT, new JobStatus(false));
    }

    @Test
    public void jobStatus_toString() {
        assertEquals(new JobStatus(true).toString(), "filled");
        assertEquals(new JobStatus(false).toString(), "vacant");
    }
}
