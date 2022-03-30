package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.job.Job;

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
    private Label id;
    @FXML
    private Label secondLabel;
    @FXML
    private Label fourthLabel;
    @FXML
    private Label thirdLabel;
    @FXML
    private Label sixthLabel;
    @FXML
    private Label fifthLabel;
    @FXML
    private Label firstLabel;
    @FXML
    private Label seventhLabel;
    @FXML
    private Label eighthLabel;
    @FXML
    private Label ninthLabel;
    @FXML
    private FlowPane tags;
    @FXML
    private Label firstSubHeader;
    @FXML
    private Label secondSubHeader;
    @FXML
    private Label thirdSubHeader;
    @FXML
    private Label fourthSubHeader;
    @FXML
    private Label fifthSubHeader;
    @FXML
    private Label sixthSubHeader;
    @FXML
    private Label seventhSubHeader;
    @FXML
    private Label eighthSubHeader;
    @FXML
    private Label ninthSubHeader;
    @FXML
    private ImageView image;

    // Stores image of applicant icon
    private final Image applicantIcon = new Image(this.getClass().getResourceAsStream("/images/applicant_icon.png"),
            50, 50, false, false);
    // Stores image of job icon
    private final Image jobIcon = new Image(this.getClass().getResourceAsStream("/images/job_icon.png"),
            50, 50, false, false);

    /**
     * Constructs InfoPanel.
     */
    public InfoPanel() {
        super(FXML);
    }


    /**
     * Displays an applicant info.
     *
     * @param applicant an applicant selected from the list
     */
    public void setApplicantInfo(Applicant applicant) {
        // Reset panels to display the applicant
        resetInfoPanelApplicant();
        image.setImage(applicantIcon);
        // Set name
        name.setText(applicant.getName().fullName);
        // Set NRIC value
        firstLabel.setText(applicant.getNric().toString());
        // Set phone number
        secondLabel.setText(applicant.getPhone().value);
        // Set email
        thirdLabel.setText(applicant.getEmail().value);
        // Set address
        fourthLabel.setText(applicant.getAddress().value);
        // Set dateApplied
        fifthLabel.setText(applicant.getDateApplied().toString());
        // Set interviewDate
        sixthLabel.setText(applicant.getInterviewDate().toString());
        // Set jobID
        seventhLabel.setText(applicant.getJobId().toString());
        // Set qualification
        eighthLabel.setText(applicant.getQualification().toString());
        // Set status
        StyleUtil.setApplicantStatusStyle(ninthLabel, applicant.getApplicantStatus().toString());
        // Set tag
        applicant.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Resets the layout for displaying a job in order to display an applicant.
     */
    private void resetInfoPanelApplicant() {
        tags.getChildren().clear();
        secondLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        fifthLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        id.setVisible(false);
        seventhLabel.setVisible(true);
        eighthLabel.setVisible(true);
        ninthLabel.setVisible(true);
        setApplicantSubHeader();
    }

    /**
     * Sets the sub-headers for displaying an applicant.
     */
    private void setApplicantSubHeader() {
        seventhSubHeader.setVisible(true);
        eighthSubHeader.setVisible(true);
        ninthSubHeader.setVisible(true);
        firstSubHeader.setText("Nric: ");
        secondSubHeader.setText("Phone number: ");
        thirdSubHeader.setText("Email: ");
        fourthSubHeader.setText("Address: ");
        fifthSubHeader.setText("Date applied: ");
        sixthSubHeader.setText("Interview date: ");
        seventhSubHeader.setText("Job ID: ");
        eighthSubHeader.setText("Qualification: ");
        ninthSubHeader.setText("Application status: ");
    }

    /**
     * Displays a job info.
     *
     * @param job a job selected from the list
     */
    public void setJobInfo(Job job) {
        resetInfoPanelJob();
        image.setImage(jobIcon);
        name.setText(job.getJobTitle().toString());
        id.setText("#" + job.getJobId().toString());
        // Set company name
        firstLabel.setText(job.getCompany().toString());
        // Set job position
        StyleUtil.setJobPositionStyle(secondLabel, job.getPosition().toString());
        // Set salary
        thirdLabel.setText(job.getSalary().toString());
        // Set address
        fourthLabel.setText(job.getAddress().toString());
        // Set job status
        StyleUtil.setJobStatusStyle(fifthLabel, job.getJobStatus().toString());
        // Set job qualification
        sixthLabel.setText(job.getQualification().toString());
        // Make unused label to invisible
    }

    /**
     * Resets the layout for displaying an applicant to display a job.
     */
    private void resetInfoPanelJob() {
        tags.getChildren().clear();
        id.setVisible(true);
        seventhLabel.setVisible(false);
        eighthLabel.setVisible(false);
        ninthLabel.setVisible(false);
        setJobSubHeader();
    }

    /**
     * Sets the sub-headers for job information.
     */
    private void setJobSubHeader() {
        firstSubHeader.setText("Company name: ");
        secondSubHeader.setText("Position: ");
        thirdSubHeader.setText("Salary: ");
        fourthSubHeader.setText("Address: ");
        fifthSubHeader.setText("Job Status: ");
        sixthSubHeader.setText("Qualification: ");
        // Make unused heading to invisible
        seventhSubHeader.setVisible(false);
        eighthSubHeader.setVisible(false);
        ninthSubHeader.setVisible(false);
    }
}
