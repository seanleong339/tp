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

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

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

### Adding a Job: `job add`
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

### Deleting a Job attribute: `job delete`

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

###Adding Applicant attribute: `applicant add`

Adds attributes to a specified applicant.

Format: `applicant add [ID*] q/[QUALIFICATION] d/[DATE APPLIED] j/[JOB ID] i/[INTERVIEW] nric/[NRIC]`

Tip: Fill in fields in the stipulated order. Since all the fields are optional except `[ID*]`. Just enter the flag of the attributes you want to add, followed by the details related to that flag.

*flag/[ATTRIBUTE]*

`[ID*]` : Id of the applicant

`q/[QUALIFICATION]` : Applicant’s qualification

`d/[DATE APPLIED]` : Date Applicant applied for job. Format: yymmdd

`j/[JOB ID]` : Unique ID of the job Applicant is applying for

`i/[INTERVIEW]` : Applicant’s upcoming job interview date. Format: yymmdd hhMM

`nric/[NRIC]`: Flag to represent the NRIC number

Examples:

`applicant add 110 q/diploma d/220216 1800`
- Adds a diploma attribute and date applied attribute to the applicant with ID 110.

`applicant add 112 j/1 in/220226`
- Adds a job id attribute and interview date attribute to the applicant with ID 112.

### Delete Applicant attribute: `applicant delete`

Delete attributes of an applicant corresponding to the flags.

**Fields:** ID, Name, Contact Number, Qualification, Date applied, Job ID, upcoming interview

Format: `applicant delete [ID*] qual/ d/ j/ i/ nric/`

**Tip:** Fill in fields in the stipulated order. Since all the fields are optional except `[ID*]`. Just enter the flag of the attributes you want to delete.

`[ID*]`: Unique ID of the applicant. The ID must be a positive integer 1, 2, 3,...

*flag/*

`q/`: Flag to represent the qualification
`d/`: Flag to represent the date the applicant applied for a job
`j/`: Flag to represent the job id
`i/`: Flag to represent the date of an upcoming interview
nric/: Flag to represent the NRIC number

Example:

`applicant delete 101 nric/`
- Deletes NRIC attribute of the applicant with an ID 101.

`applicant delete 102 nric/ qual/ i/`
- Deletes NRIC, qualification, date of the interview attribute of the applicant with an ID 102.

`applicant delete 103 da/ j/ delete`
- The date the applicant applied for a job, and job attribute of the applicant with an ID 103.


### Mark an Applicant: `applicant mark`

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

###Unmark an Applicant: `applicant unmark`
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

### Listing all applicants : `list list applicant`

Shows a list of all applicants in the address book.

Format: `list list applicant`

### Listing all jobs : `list list job`

Shows a list of all jobs in the address book.

Format: `list list job`

### Listing all employers : `list list employer`

Shows a list of all employers in the address book.

Format: `list list employer`

### Adding an Applicant to the ApplicantList: `list add applicant`

Adds an applicant to the applicant list in the address book.

Format: `list add applicant n/[NAME] p/[PHONE] nric/[NRIC]
            q/[QUALIFICATIONS] d/[DATE] i/[INTERVIEW DATE] j/[JOB ID]`

Tip: Fill in fields in the stipulated order. Since all the fields are optional.
Just enter the flag of the attributes followed by the details you want to add.

*flag/[ATTRIBUTE]:*

`n/[NAME]`: Applicant name

`p/[PHONE]`: Applicant phone number

`nric/[NRIC]`: Applicant NRIC

`q/[QUALIFICATION]`: Applicant highest qualification

`i/[INTERVIEW DATE]`: Date of applicant's interview. `INTERVIEW DATE` should
be in the following format: yymmdd hhMM ie i/220328 1800

`j/[JOB ID]`: Job ID applicant is applying for. `JOB ID` must be a positive integer 1,2,3,...

Examples:

`list add applicant n/Jaden Ho p/92812192 nric/S1234567A q/A Level i/220528 1900 j/4`

* Adds an applicant with name-Jaden Ho, phone number-92812191, nric-S1234567A, highest qualification-A Level,
interview date-28 May 2022 at 7:00pm, Job ID-4 to the applicant list in the address book.

`list add applicant n/Cheryl Tan p/81728194`

* Adds an applicant with name-Cheryl Tan, phone number-81728194 to the
applicant list in the address book.

### Adding a Job to the JobList: `list add job`

Adds a job to the job list in the address book.

Format: `list add job t/[TITLE] v/[VACANCY] e/[EMPLOYER ID] ed/[EDUCATION] s/[SALARY]
l/[LOCATION] sp/[SPECIALISATION] d/[DURATION] ti/[TIME]`

Tip: Fill in fields in the stipulated order. Since all the fields are optional.
Just enter the flag of the attributes followed by the details you want to add.

*flag/[ATTRIBUTE]:*

`t/[TITLE]`: Job title

`v/[VACANCY]`: Number of vacancies for the job. The `VACANCY` must be a positive integer 1,2,3,...

`e/[EMPLOYER ID]`: Unique ID of the employer. `EMPLOYER ID` must be a positive integer 1,2,3,...

`ed/[EDUCATION]` : Education requirement of job

`s/[SALARY]` : Salary of job, based on how much the job pays per month. The `SALARY` must be a positive integer 1000,2000,3000,...

`l/[LOCATION]` : Location of Job

`sp/[SPECIALISATION]` : Field of specialisation of job

`d/[DURATION]` : Duration of job in months. ie d/8

`t/[TIME]` : time of interview. `TIME` should be in the following format: yymmdd hhMM. ie t/220329 1800

Examples:
`list add job t/Software Engineer Intern v/5 e/1 ed/Undergraduate s/4000
l/Tiong Bahru sp/Software Engineer d/3 ti/220329 1800`

* Adds the job with name-Software Engineer Intern,
number of vacancies-5, employer ID-1, education requirement-Undergraduate, salary-$4000 per month, location-
Tiong Bahru, specialisation-Software Engineer, duration-3 months, time of interview-29 Mar 2022 at 6:00pm to the
job list in the address book

`list add job t/Admin Administrator v/4 e/2`

* Adds a job with name-Admin Administrator, number of vacancies-5, employer ID-2 to the job list in the address book.

### Adding an Employer to the EmployerList: `list add employer`

Adds an employer to the employer list in the address book.

Format: `list add employer n/[NAME] c/[COMPANY] p/[PHONE]`

*flag/[ATTRIBUTE]:*

`n/[NAME]`: Employer name

`c/[COMPANY]`: Employer company

`p/[PHONE]`: Employer phone number

Tip: Fill in fields in the stipulated order. Since all the fields are optional.
Just enter the flag of the attributes followed by the details you want to add.

Examples:

`list add employer n/John Tan c/Mircrosoft p/92023951`

* Adds the employer with name-John Tan,
company name-Microsoft, phone number-92023951 to the employer list in the address book.

`list add employer n/Rachel Solanda c/Facebook`

* Adds the employer with name-Rachel Solanda,
company name-Facebook to the employer list in the address book.

### Deleting an Applicant : `list delete applicant`

Deletes the specified applicant from the applicant list in the address book.

Format: `list delete applicant [ID*]`

`[ID*]`: Unique ID of the applicant. `ID` must be a positive integer 1,2,3,...

Examples:

`list delete 4`

* Deletes an applicant with ID 4 from the applicant list in the address book.

`list delete 5`

* Deletes an applicant with ID 5 from the applicant list in the address book.

### Deleting a Job : `list delete job`

Deletes the specified job from the job list in the address book.

Format: `list delete job [ID*]`

`[ID*]`: Unique ID of the job. `ID` must be a positive integer 1,2,3,...

Examples:

`list delete job 4`

* Deletes a job with ID 4 from the job list in the address book.

`list delete job 5`

* Deletes a job with ID 5 from the job list in the address book.

### Deleting an employer: `list delete employer`

Deletes the specified employer from the employer list in the address book.

Format: `list delete employer [ID*]`

`[ID*]`: Unique ID of the employer. `ID` must be a positive integer 1,2,3,...

Examples:

`list delete employer 4`

* Deletes an employer with ID 4 from the employer list in the address book.

`list delete employer 5`

* Deletes an employer with ID 5 from the employer list in the address book.

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

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
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

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
