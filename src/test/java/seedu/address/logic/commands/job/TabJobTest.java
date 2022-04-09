package seedu.address.logic.commands.job;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.job.TabJob.SHOWING_TABJOB_MESSAGE;

public class TabJobTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_TABJOB_MESSAGE, false,
                false, false, true);
        assertCommandSuccess(new TabJob(), model, expectedCommandResult, expectedModel);
    }
}
