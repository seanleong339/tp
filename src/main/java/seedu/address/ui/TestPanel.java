package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    public TestPanel() {
        super(FXML);
    }

    public void setPersonText(Person person) {
        tags.getChildren().clear();
        name.setText(person.getName().fullName);
        phone.setText("phone number: " + person.getPhone().value);
        address.setText("address: " + person.getAddress().value);
        email.setText("email: " + person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
