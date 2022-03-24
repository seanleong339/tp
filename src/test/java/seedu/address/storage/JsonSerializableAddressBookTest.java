package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalApplicants;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path TYPICAL_APPLICANTS_FILE = TEST_DATA_FOLDER.resolve("typicalApplicantsAddressBook.json");
    private static final Path INVALID_APPLICANTS_FILE = TEST_DATA_FOLDER.resolve("invalidApplicantsAddressBook.json");
    private static final Path DUPLICATE_APPLICANTS_FILE =
            TEST_DATA_FOLDER.resolve("duplicateApplicantsAddressBook.json");

    @Test
    public void toModeType_typicalApplicantsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPLICANTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalApplicantsAddressBook = TypicalApplicants.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalApplicantsAddressBook);
    }

    @Test
    public void toModelType_invalidApplicantsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPLICANTS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateApplicants_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPLICANTS_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_APPLICANT,
                dataFromFile::toModelType);
    }

    //Todo when Person class is removed, remove all these
    //
    // @Test
    // public void toModelType_typicalPersonsFile_success() throws Exception {
    //     JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
    //             JsonSerializableAddressBook.class).get();
    //     AddressBook addressBookFromFile = dataFromFile.toModelType();
    //     AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
    //     assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    // }

    // @Test
    // public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
    //     JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
    //             JsonSerializableAddressBook.class).get();
    //     assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    // }

    // @Test
    // public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
    //     JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
    //             JsonSerializableAddressBook.class).get();
    //     assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
    //             dataFromFile::toModelType);
    // }

}
