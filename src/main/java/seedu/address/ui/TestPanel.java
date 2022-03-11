package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class TestPanel extends UiPart<Region> {
    private static final String FXML = "TestPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TestPanel.class);

    public final Person person;

    @FXML
    public HBox cardPane;
    @FXML
    public Label name;
    @FXML
    public Label id;
    @FXML
    public Label phone;
    @FXML
    public Label address;
    @FXML
    public Label email;
    @FXML
    public FlowPane tags;

    public TestPanel() {
        super(FXML);
        this.person = null;
    }
}
