---
layout: page
title: User Guide
---

ReCLIne is a **desktop app to organize contacts for recruiters to track, optimized for use via a
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, ReCLIne can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
    * [Quick Start](#quick-start)
    * [Features](#features)
    * [FAQ](#faq)
    * [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ReCLIne.jar` from [here](https://github.com/AY2122S2-CS2103T-W15-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ReCLIne.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [coming soon]

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding an Applicant to the ApplicantList: `addapplicant`

Adds an applicant to the applicant list of the address book.

Format: `addapplicant *n/[NAME] *p/[PHONE] *nric/[NRIC] *a/[ADDRESS] *e/[EMAIL]
            *d/[DATEAPPLIED]`

Tip: Fill in fields in any order. All fields are compulsory.

*flag/[ATTRIBUTE]:*

`n/[NAME]`: Applicant's name

`p/[PHONE]`: Applicant's phone number

`nric/[NRIC]`: Applicant's NRIC

`a/[ADDRESS]` : Applicant's address

`e/[EMAIL]` : Applicant's email address

`d/[DATEAPPLIED]` : Date that Applicant applied for the job


Examples:

`addapplicant n/Jaden Ho p/92812192 nric/S1234567A a/Tampines St 96 Block 312 e/jadenho@email.com d/2022-03-12`

* Adds an applicant with name-Jaden Ho, phone number-92812191, nric-S1234567A, address-Tampines St 96 Block 312 
  email-jadenho@email.com, date applied- 2022-03-12 to the applicant list in the address book.


### Editing an Applicant in the ApplicantList: `editapplicant`

Edits an applicant in the applicant list of the address book.

Format: `editApplicant *[ID] n/[NAME] p/[PHONE NUMBER] e/[EMAIL ADDRESS] 
nric/[NRIC] q/[QUALIFICATION] d/[DATE APPLIED] j/[JOB ID] 
i/[INTERVIEWDATE] s/[STATUS] t/[TAG]`

Tip: Fill in fields in any order. Just input the fields that you would like to change for the specific index number.
To leave out fields, skip the flag and attribute completely.

*flag/[ATTRIBUTE]:*

`[ID*]` : Index number of the applicant that is displayed in the List

`n/[NAME]`: Updated Applicant's name

`p/[PHONE]`: Updated Applicant's phone number

`nric/[NRIC]`: Updated Applicant's NRIC

`a/[ADDRESS]` : Updated Applicant's address

`e/[EMAIL]` : Updated Applicant's email address

`nric/[NRIC]` : Updated Applicant's NRIC

`d/[DATEAPPLIED]` : Updated Applicant's application date

`q/[QUALIFICATION]` : Updated Applicant's qualification

`j/[JOB ID]` : Updated unique ID of the job applicant is applying for

`i/[INTERVIEW]` : Updated Applicant’s upcoming job interview date

`t/[TAG]` :  Updated Applicant's Tag

Examples:

`editapplicant 1 n/Jaden Ho a/Tampines St 96 Block 312 e/jadenho@email.com d/2022-03-12 q/Degree in Computer Science
i/2022-03-18`

* Edits an applicant with index number 1 with name - Jaden Ho, address - Tampines St 96 Block 312
  email - jadenho@email.com, date applied - 2022-03-12, qualification - Degree of Computer Science, 
  interview date - 2022-03-18.
  
### Saving the data

ReCLIne data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ReCLIne data are saved as a JSON file `[JAR file location]/data/recline.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ReCLIne will discard all data and start with an empty data file at the next run.
</div>

### Mark an Applicant: `applicant mark` [coming soon]

Mark interview and application status of the applicant.

**Fields:** ID, interview status, application status

Format: `applicant mark [ID*] i/ st/[STATUS]`

*flag/[Attribute]*

`i/`: Flag to represent interview status of the applicant
`st/[STATUS]`: Flag to mark the application status of the applicant. [STATUS] must be either pending, offered

**Tip:** Fill in fields in the stipulated order. To leave out optional fields, skip the flag and attribute completely.

Example:

`applicant mark 104 i/`
- Marks the applicant as interviewed

`applicant mark 105 st/pending`
- Marks the status of application of the applicant with ID 105 as pending.

`applicant delete 106 st/offered`
- Marks the status of application of the applicant with ID 106 as offered.

###Unmark an Applicant: `applicant unmark` [coming soon]
Unmark the interview and application status of the applicant.

**Fields:** ID, interview status, application status

Format: `applicant unmark [ID*] i/ st/`

*flag/*

`i/`: Flag to represent interview status of the applicant
`st/`: Flag to represent the application status of the applicant

**Tip:** Fill in fields in the stipulated order. To leave out optional fields, skip the flag and attribute completely.

Example:

`applicant unmark 107 i/`
- Unmarks the applicant as interviewed.
  `applicant mark 108 st/`
- -Unmarks the status of application of the applicant with ID 108. The application status will no longer be either pending or offered.

### Adding a Job: `job add` [coming soon]
Adds a job attribute

Format: `job add [ID*] ed/[EDUCATION] l/[LOCATION] s/[SALARY] sp/[SPECIALISATION] d/[DURATION]`

*flag/[Attribute]:*

`[ID*]`: Unique ID for the job instance. The ID must be a positive integer 1, 2, 3..

`e/[EDUCATION]`: Education level requirement for the job

`l/[LOCATION]`: Location of the job

`s/[SALARY]`:  Salary of job, based on how much the job pays a month. The SALARY must be a positive integer
1000,2000,3000,...

`sp/[SPECIALIZATION]`: Field of Specialisation required for the job

`d/[DURATION]`: Duration of job in months. ie d/8 represents 8 months

**Tip**: Fill in fields in the stipulated order. Since all the fields are optional except `[ID*]`.
Just enter the flag of the attributes you want to add, followed by the details related to that flag.

Example:

`job add 231 e/degree in computer science l/Tiong Bahru s/4000`

* Add details to a Job ID 231, a Computer Science degree requirement, adds that location of the job
  is at Tiong Bahru and salary of the Job is 4000 a month.

`job add 432 sp/Machine Learning`

* Add details to a Job ID 432, a Machine Learning field of specialisation.

`job add 32 l/OCBC s/5000 sp/Accountancy d/3`

* Add details to Job ID 32, that location of job is at OCBC, salary is 5000 a month, looking for someone with
  specialisation in Accountancy and a contract of 3 years.

### Deleting a Job attribute: `job delete` [coming soon]

* Deletes attributes from the specified job id. Command should include at least 1 flag.

Format: `job delete [ID] ed/ l/ s/ sp/ d/`

*flag/[Attribute]*

`[ID]` : Unique id for the job instance. The ID must be a positive integer 1, 2, 3,...

`ed/`  : Flag to represent education attribute

`l/` : Flag to represent location attribute

`s/` : Flag to represent salary attribute

`sp/` : Flag to represent specialisation attribute

`d/` : Flag to represent duration attribute

**Tip**: Fill in fields in the stipulated order. Since all the fields are optional except `[ID*]`. Just enter the flag of the attributes you want to delete.

Examples:

`job delete 101 e/ s/ d/`

* Deletes the education history, salary and duration attribute from job with ID 101.

`job delete 132 sp/ ed/ l/`

* Deletes the specialisation, education and location attribute from job with ID 132.

### Editing a person : `edit` [coming soon]

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find` [coming soon]

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Clearing all entries : `clear` [coming soon]

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit` [coming soon]

Exits the program.

Format: `exit`

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ReCLIne home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
