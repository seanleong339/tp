package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {

    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    public ListView<Person> personListView;

    TestPanel testPanel;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, TestPanel testPanel) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        this.testPanel = testPanel;
    }


    public void handlePersonClicks() {
        personListView.setOnMouseClicked(event -> {
            testPanel.tags.getChildren().clear();
            Person person  = personListView.getSelectionModel()
                    .getSelectedItem();
            testPanel.name.setText(person.getName().fullName);
            testPanel.phone.setText("phone number: " + person.getPhone().value);
            testPanel.address.setText("address: " + person.getAddress().value);
            testPanel.email.setText("email: " + person.getEmail().value);
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> testPanel.tags.getChildren().add(new Label(tag.tagName)));
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);
            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}

