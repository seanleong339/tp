package seedu.address.ui.applicant;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.applicant.Applicant;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Applicant} in the list.
 */
public class ApplicantCard extends UiPart<Region> {
    // Todo: create ApplicantListCard.fxml
    private static final String FXML = "ApplicantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Applicant applicant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label job;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ApplicantCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;
        id.setText(displayedIndex + ". ");
        name.setText(applicant.getName().fullName);
        email.setText(applicant.getEmail().value);
        applicant.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.ui.applicant.ApplicantCard)) {
            return false;
        }

        // state check
        seedu.address.ui.applicant.ApplicantCard card = (seedu.address.ui.applicant.ApplicantCard) other;
        return id.getText().equals(card.id.getText())
                && applicant.equals(card.applicant);
    }
}
