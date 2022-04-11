---
layout: page
title: Rosa Kang's Project Portfolio Page
---

### Project: ReCLIne

####Overview
ReCLIne is a desktop address book application used by recruiters to organise the contacts to track.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to s. [\#96](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/96), [\#144](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/144/files)
    * What it does: Allows the user to save Applicants, Jobs, and the current unique id for new Jobs.
    * Justification: This feature is important for the application to save application information in the appropriate data file.
    * Highlights: This enhancement was challenging as it required an in-depth understanding of how data from the model and logic classes converts to a JSON format. Requires an understanding of how the data from individual models are gathered and saved in a single JSON file. Finally, it requires and understanding of how the data from the Json file converts back to a model. Given the number of attribute classes in both Applicants and Jobs, it also required extensive testing to ensure all attributes could be converted to JSON file, and converted back into the correct models. The implementation required additions in the JSON storage classes, creation of new Storage classes JsonAdaptedJob and JsonAdaptedApplicant, as well as changes in the model classes.
    * Credits: This feature builds on the storage system of AB3. It uses the same design pattern and methods as JsonAdaptedPerson for JsonAdaptedApplicant and JsonAdaptedJob, but adapted for Applicant and Jobs respectively.

* **New Feature**: Added the ability to assign Unique ID to Jobs on creation of Job. [\#139](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/139/files)
    * What it does: Assigns a unique Id to Jobs when they are created.
    * Justification: This feature is important as there can be multiple identical Job openings, as companies might require more than 1 individual to do a job. Hence, a Unique Job Id is important to differentiate between Jobs.
    * Highlights: This enhancement requires an understanding of how data is saved from the AddressBook model into the JSON file, as well as how to retrieve stored data from the model. Since there was no prior implementation of this in AB3, I came up with the way to store a single integer in the application, and then to increment and save the new ID to the data file whenever a new Job is added.
    * Credits: This feature was designed on my own. It builds on the current data storage file and addressbook to store a single integer representing the unique ID in the addressbook, which will be incremented and saved to the data file whenever a new JobID is assigned.

* **New Feature**: Added new Predicate for application to search the job list by job id. [\#152](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/152/files)
    * What it does: allows the user to search for Jobs by the JobID.
    * Justification: This feature is important for the user to find the desired Job quickly without having to scan the whole job list.

* **New Feature**: Added the ability to add new Jobs. [\#139](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/139/files)
    * What it does: allows the user to add new Jobs to ReCLIne.
    * Justification: This feature is important for the user to create new Jobs.
    * Highlights: This enhancement was challenging as it requires knowledge of the valid inputs for all the different attributes in a Job. The compulsory attributes when adding a Job had to be chosen, and for the remaining attributes, I had to modify the attribute classes such that they were able to have a "PENDING" state.
    * Credits: This feature is adapted from the Add command of AB3. It uses the same command design pattern, a parser and a command class. However, the class that is created is changed to Applicant.

* **New Feature**: Added the ability to add new Applicants. [\#75](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/75), [\#76](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/76), [\#91](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/91)
    * What it does: allows the user to add new Applicants to ReCLIne.
    * Justification: This feature is important for the user to create new Applicants.
    * Highlights: This enhancement was challenging as it requires knowledge of the valid inputs for all the different attributes in an Applicant. The compulsory attributes when adding an Applicant had to be chosen, and for the remaining attributes, I had to modify the attribute classes such that they were able to have a "PENDING" state.
    * Credits: This feature is adapted from the Add command of AB3. It uses the same command design pattern, a parser and a command class. However, the class that is created is changed to Applicant.

* **New Feature**: Added new attribute class ApplicantStatus. [\#58](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/58)
    * What it does: allows user to save the ApplicantStatus of an Applicant.
    * Justification: This feature is important for the user to create new Applicants.

* **New Feature**: Added new attribute class InterviewStatus. [\#58](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/58)
  * What it does: allows the user to save the InterviewStatus of an Applicant.
  * Justification: This feature is important when it comes to 

* **New Feature**: Added new attribute class CompanyName. [\#58](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/58)
    * What it does: allows the user to save the NRIC of an Applicant.
    * Justification: This feature is important for the user to create new Applicants.

* **New Feature**: Added the ability to switch between tabs in ReCLIne. [\#150](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/150)
    * What it does: allows the user to see all the Jobs in the application after he executes the find command.
    * Justification: This feature is important as the user will only be able to see the Job he searches for after using the find command. To see all the jobs again, the user will use this ListJob command for the application to list all Jobs.
    * Credits: This feature is adapted from the List command of AB3. It just changes the filteredlist back to the full observable list.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ro4k9&breakdown=true)

* **Project management**:

* **Enhancements to existing features**:

* **Documentation**:
    * User Guide:
        * Added documentation for the features `applicant add`, `applicant delete`, `applicant mark`
      and `applicant unmark`
    * Developer Guide:

* **Community**:
    * PRs reviewed (with non-trivial review comments):
      [\#9](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/9/files/3ec32ad945f8156a9a6359c1ec68609daffe2ebd)
      [\#26](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/26/files/b2a9d2a27fdcb519f62753e0a1335ff7ea9ce5c5)
* **Tools**:
