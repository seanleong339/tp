package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

class QualificationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Qualification(null));
    }

    @Test
    public void constructor_invalidQualification_throwsIllegalArgumentException() {
        String invalidQualification = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidQualification));
    }

    @Test
    void isValidQualification() {
        // null qualification
        assertThrows(NullPointerException.class, () -> Qualification.isValidQualification(null));

        // invalid qualification
        assertFalse(Qualification.isValidQualification("")); // empty string
        assertFalse(Qualification.isValidQualification(" ")); // spaces only
        assertFalse(Qualification.isValidQualification("^")); // only non-alphanumeric characters
        assertFalse(Qualification.isValidQualification("bachelor*")); // contains non-alphanumeric characters

        // valid qualification
        assertTrue(Qualification.isValidQualification("bachelor of computer science")); // alphabets only
        assertTrue(Qualification.isValidQualification("12345")); // numbers only
        assertTrue(Qualification.isValidQualification("2nd Year University Student")); // alphanumeric characters
        assertTrue(Qualification.isValidQualification("Bachelor Of Computer Science")); // with capital letters
    }
}