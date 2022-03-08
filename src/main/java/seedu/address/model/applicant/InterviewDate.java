package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InterviewDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the format yyyy-mm-dd"
                    + "and adhere to the following constraints:\n"
                    + "1. The date should not be blank\n"
                    + "2. The date should be within a range of 1-31\n"
                    + "3. The month should be within a range of 1-12";
    /*
     * The first character of the date must not be a whitespace,
     * and the date has to be valid and in the format of yyyy-mm-dd with leading zeros.
     */
    public static final String VALIDATION_REGEX = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
    public final LocalDate date;

    /**
     * Constructs an Interview Date
     */
    public InterviewDate() {
        date = null;
    }

    /**
     * Constructs an Interview Date
     *
     * @param interviewDate A valid interviewDate.
     */
    public InterviewDate(String interviewDate) {
        requireNonNull(interviewDate);
        checkArgument(isValidInterviewDate(interviewDate), MESSAGE_CONSTRAINTS);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(interviewDate, format);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidInterviewDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.InterviewDate // instanceof handles nulls
                && date.equals(((seedu.address.model.applicant.InterviewDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
