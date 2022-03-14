package seedu.address.ui.applicant;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Applicant;
import seedu.address.ui.UiPart;

/**
 * Panel containing all the information of the Applicant that has been selected in the list.
 */
public class InfoPanel extends UiPart<Region> {
    // Todo: create InfoPanel.fxml
    private static final String FXML = "InfoPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InfoPanel.class);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label dateApplied;
    @FXML
    private Label job;
    @FXML
    private Label nric;
    @FXML
    private Label interviewDate;
    @FXML
    private Label qualification;
    // TODO: Add Label status;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ApplicantInfoPanel}
     */
    public InfoPanel() {
        super(FXML);
    }

    public void setApplicantText(Applicant applicant) {
        tags.getChildren().clear();
        name.setText(applicant.getName().fullName);
        phone.setText("Phone number: " + applicant.getPhone().value);
        address.setText("Address: " + applicant.getAddress().value);
        email.setText("Email: " + applicant.getEmail().value);
        applicant.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        job.setText("Job ID: " + applicant.getJobId().toString());
        dateApplied.setText("Date applied: " + applicant.getDateApplied().toString());
        nric.setText("Nric: " + applicant.getNric().toString());
        interviewDate.setText("Interview date: " + applicant.getInterviewDate().toString());
        qualification.setText("Qualification: " + applicant.getQualification().toString());
    }
}
