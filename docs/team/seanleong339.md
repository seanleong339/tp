---
layout: page
title: Sean Leong's Project Portfolio Page
---

### Project: ReCLIne

ReCLIne is a management application used by recruiters to organise their applicant and job data. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to store Applicants, Jobs and Unique Job id in a JSON file
  * What it does: Allows the user to save Applicants, Jobs, and the current unique id for new Jobs.
  * Justification: This feature is important for the application to save application information in the appropriate data file.
  * Highlights: This enchancement was challenging as it required an in-depth understanding of how data from the model and logic classes converts to a JSON format. Requires an understanding of how the data from individual models are gathered and saved in a single JSON file. Finally, it requires and understanding of how the data from the Json file converts back to a model. Given the number of attribute classes in both Applicants and Jobs, it also required extensive testing to ensure all attributes could be converted to JSON file, and converted back into the correct models. The implementation required additions in the JSON storage classes, creation of new Storage classes JsonAdaptedJob and JsonAdaptedApplicant, as well as changes in the model classes.
  * Credits: This feature builds on the storage system of AB3. It uses the same design pattern and methods as JsonAdaptedPerson for JsonAdaptedApplicant and JsonAdaptedJob, but adapted for Applicant and Jobs respectively. 

* **New Feature**: Added the ability to assign Unique ID to Jobs on creation of Job.
  * What it does: Assigns a unique Id to Jobs when they are created.
  * Justification: This feature is important as there can be multiple identical Job openings, as companies might require more than 1 individual to do a job. Hence, a Unique Job Id is important to differentiate between Jobs.
  * Highlights: This enchancement requires an understanding of how data is saved from the AddressBook model into the JSON file, as well as how to retrieve stored data from the model. Since there was no prior implementation of this in AB3, I came up with the way to store this single integer in the application, and then to increment and save the new ID whenever a new Job is added.
  * Credits: This feature was designed on my own. It builds on the current data storage file to store a single integer representing the unique ID, incrementing and saving the unique ID to the data file whenever a new JobID is assigned.

* **New Feature**: Added the ability to add new Jobs.
  * What it does: allows the user to add new Jobs to ReCLIne.
  * Justification: This feature is important for the user to create new Jobs.
  * Highlights: This enchancement was challenging as it requires knowledge of the valid inputs for all the different attributes in a Job. The compulsory attributes when adding a Job had to be chosen, and for the remaining attributes, I had to modify the attribute classes such that they were able to have a "PENDING" state.
  * Credits: This feature is adapted from the Add command of AB3. It uses the same command design pattern, a parser and a command class. However, the class that is created is changed to Applicant.

* **New Feature**: Added the ability to add new Applicants.
    * What it does: allows the user to add new Applicants to ReCLIne.
    * Justification: This feature is important for the user to create new Applicants.
    * Highlights: This enchancement was challenging as it requires knowledge of the valid inputs for all the different attributes in an Applicant. The compulsory attributes when adding an Applicant had to be chosen, and for the remaining attributes, I had to modify the attribute classes such that they were able to have a "PENDING" state.
    * Credits: This feature is adapted from the Add command of AB3. It uses the same command design pattern, a parser and a command class. However, the class that is created is changed to Applicant.

* **Code contributed**: [RepoSense link]("https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=seanleong339&breakdown=true")

* **Project management**:
    * Managed release `v1.3.1` (1 release) on GitHub
    * Lead weekly meetings on project deliverables and tasks for the week.

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addapplicant` and `addjob` [\#72]()
    * Developer Guide:
        * Added implementation details of the `addapplicant` feature.


* **Contribution to team-based tasks**:
  * Code quality: Looks after code quality, ensures adherence to coding standards, etc.
  * Manage the issue tracker, close completed issues and completed milestones.
  * Remove traces of AB3 from User Guide [\#56]("https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/56/files")


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#77](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/77), [\#79](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/79), [\#82](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/82), [\#86](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/86), [\#89](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/89), [\#154](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/154), [\#157](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/157).

