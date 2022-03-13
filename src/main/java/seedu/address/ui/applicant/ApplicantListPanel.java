package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.person.Person;
import seedu.address.ui.applicant.ApplicantCard;

/**
 * Panel containing the list of persons.
 */
public class ApplicantListPanel extends UiPart<Region> {

    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private TestPanel testPanel;

    @FXML
    private ListView<Applicant> applicantListView;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ApplicantListPanel(ObservableList<Applicant> applicantList, TestPanel testPanel) {
        super(FXML);
        applicantListView.setItems(applicantList);
        applicantListView.setCellFactory(listView -> new ApplicantListViewCell());
        this.testPanel = testPanel;
    }

    /**
     * Displays ListView item clicked on the ListView on the {@code testPanel}
     */
    /* public void handlePersonClicks() {
        applicantListView.setOnMouseClicked(event -> {
            Person person = applicantListView.getSelectionModel().getSelectedItem();
            testPanel.setPersonText(person);
        });
    } */

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ApplicantListViewCell extends ListCell<Applicant> {
        @Override
        protected void updateItem(Applicant applicant, boolean empty) {
            super.updateItem(applicant, empty);
            if (empty || applicant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ApplicantCard(applicant, getIndex() + 1).getRoot());
            }
        }
    }
}

