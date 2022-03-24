---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### AddApplicant feature
The `addapplicant` mechanism is facilitated by `AddApplicantParser` and `AddApplicant` command. It extends `AddressBook` with the capability to 
create new applicants, and store them in the `UniqueApplicantList` of the `AddressBook`. Following the command pattern of the 
application, the `parse()` method will parse the user input, use the arguments parsed to create a new `Applicant`, and then
the `Applicant` will be used to create an `AddApplicant` command.
The command will then be executed to add the new `Applicant` to the `AddressBook`.

Given below is an example usage scenario, and how the `addapplicant` mechanism behaves at each step. The scenario assumes that
the application is already launched.

Step 1. The user inputs the command `addapplicant n/John Tan nric/S1374678D p/98765432 e/johntan@hotmail.com a/311, Clementi Ave 2, #02-25 d/21-3-2022`.
The `AddressBookParser#parseCommand()` is called in `LogicManager` and it uses the `BASIC_COMMAND_FORMAT` to separate the `commandWord` and `arguments`.
The `commandWord` will then cause a new `AddApplicantParser` to be created.

![AddApplicantStep1](images/AddApplicantStep1.png)

Step 2. The `AddApplicantParser#parse()` method is then called with `arguments` as the argument. The `arguments` will then be
further parsed using their respective class parser methods in `ParserUtil` to create their respective attribute classes, 
and then used to create a new `Applicant` object. The `Applicant` object will be used to create a new `AddApplicant` command.

![AddApplicantStep2](images/AddApplicantStep2.png)

Step 3. The `AddApplicant` command is executed, and it will call the `Model#addApplicant()`, which calls the 
`AddressBook#addApplicant()`, to store the `Applicant` in the `UniqueApplicantList`. 

![AddApplicantStep3](images/AddApplicantStep3.png)

The following sequence diagram shows the full sequence when a user adds a new Applicant
![AddApplicantFullSequence](images/AddApplicantFullSequence.png)

### EditApplicant feature

The `editapplicant` mechanism is facilitated by `AddressBook`. `EditApplicant` extends `Command` class. Within the `EditApplicant` class,
it has a nested class `EditApplicantDescriptor` that is used to store the updated Applicant details that will then be used
to create a new Applicant object. This new Applicant object will replace the current Applicant object that is in the `AddressBook`.

When the user wants to edit an applicant, the user will input `editapplicant` along with the index number of Applicant
and prefix of any attributes that the user wants to change followed by the new value of the attribute. The user only
needs to include the prefixes of attributes that he wants to change for a particular Applicant.

For example, `editapplicant 1 n/Alice Yeoh` will change the name of Applicant 1 to "Alice Yeoh".

Given below is an example usage scenario, and how the `editapplicant` mechanism behaves at each step.

Step 1. The user inputs `editapplicant 1 n/Alice Yeoh` into ReCLIne. `AddressBookParser#parseCommand()` and `EditApplicantParser#parse()`
is executed, which will return a `EditApplicant` object.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `index` inputted is greater than the
size of the current `UniqueApplicantList` the execution of the command will fail. A `CommandException` will be thrown
and displayed for the user. This ensures that inputted `index` is not out of bound.

</div>

Step 2. `EditApplcant#execute()` is executed. Firstly, get the current Applicant object that is in the
indicated index in the `UniqueApplicantList`. In this case, Applicant 1 in the `UniqueApplicantList` is stored in the
`applicantToEdit` variable.

![EditApplicantState1](images/EditApplicantState1.png)

Step 3. Next, a new Applicant object, `editedApplicant`, that is going to replace `applicantToEdit` is created. This is done with
`createEditedApplicant`. Updated information will replace the current Applicant attributes in `applicantToEdit`. All other
attributes will be obtained from the current Applicant object.

![EditApplicantState2](images/EditApplicantState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** A check between the new Applicant object
and current Applicant object occurs. If both Applicant objects are the same, a `CommandException` is thrown. This ensures
that there is no duplicate Applicants in the `UniqueApplicantList` and `AddressBook`

</div>

Step 4. Lastly, the new Applicant object will replace the current Applicant object of the indicated index number in the
`AddressBook`.

![EditApplicantState3](images/EditApplicantState3.png)

The following sequence diagram shows how the `editapplicant` command works:

![EditApplicantSequenceDiagram](images/EditApplicantSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new `editapplicant` command:

![EditApplicantActivityDiagram](images/EditApplicantActivityDiagram.png)


### MarkApplicant feature

The `markapplicant` mechanism is facilitated by `AddressBook`. `MarkApplicant` extends `Command` class.
`MarkApplicant` updates the status via creating a new applicant object with  the new status value
and same original Applicant details.  This new Applicant object will replace the current Applicant object that is in the `AddressBook`.

When the user wants to mark an applicant, the user will input `markapplicant` along with the index number of Applicant,
and the new status value, which includes `pending`, `rejected`, `interviewed`,and `accepted`.

For example, the applicant has the default state of `pending` when it is initialized.
A command `markapplicant 1 s/rejected` will change the status of applicant in index 1 to rejected.

Given below is an example usage scenario, and how the `markapplicant` mechanism behaves at each step.

Step 1. The user inputs `markapplicant 1 s/rejected` into ReCLIne. `AddressBookParser#parseCommand()`
and `MarkApplicantParser#parse()`. `MarkApplicantParser#parse()`calls `ParserUtil#parseIndex()` and `ParserUtil#parseApplicant()`,
which returns Index and ApplicantStatus objects representing the index and status value that user inputted. This returns a
`MarkApplicant` with Index and ApplicantStatus objects as arguments.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `index` inputted is greater than the
size of the current `UniqueApplicantList` the execution of the command will fail. A `CommandException` will be thrown
and displayed for the user. This ensures that inputted `index` is not out of bound.
</div>

The following sequence diagram shows how the `markapplicant` command works:

![MarkApplicantSequenceDiagram](images/MarkApplicantSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new `markapplicant` command:

![MarkApplicantActivityDiagram](images/MarkApplicantActivityDiagram.png)

### DeleteApplicant feature

The `deleteapplicant` mechanism is facilitated by `AddressBook`. `DeleteApplicant` extends `Command` class. 

When the user wants to delete an applicant from the address book, the user will input `deleteapplicant` along with the
index number of the applicant. Note that this index is the same as the index that is displayed to the user under the
applicant list tab in the ReCLIne application. 

Given below is an example usage scenario and how the `deleteapplicant` mechanism behaves at each step.

Step 1. The user inputs `deleteapplicant 1` into ReCLIne. `LogicManager#execute()` is executed, inside this method,
`LogicManager#execute()` is executed which will return a DeleteApplicant object. 

Step 2. Inside `LogicManager#execute()`, `DeleteApplicant#execute()` is executed. Inside this method, we obtained the last
shown applicant list by calling `Model#getFilteredApplicantList()`. We also check if the index is invalid in `DeleteApplicant#execute()`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `index` inputted is invalid, meaning 
that it is greater than the size of the current `UnqiueApplicantList` or it is a negative integer or 0, the execution of 
the command will fail and `AddressBookParser#parseCommand()` will throw a `CommandException` and the 
`MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX` will be displayed to the user. This ensures that the inputted `index` is not out of bound.

</div>

Step 3. Next, we obtain the zero base of the target index by calling `Index#getZeroBased()`. This is because the index displayed in the applicant
list tab in the application list is in one based eg 1,2,3,4... We obtained the zero based of the target index so that we are able to get the
true index of the applicant in the last shown applicant list. We then obtain the applicant to delete from the last shown list via the zero based index

Step 4. Lastly, We then call `Model#deleteApplicant()` which will delete the targeted applicant from the applicant list.
The applicant will display the new applicant list without the deleted applicant and a MESSAGE_DELETE_APPLICANT_SUCCESS is shown.

The following sequence diagram shows how the `deleteapplicant` command works:

![DeleteApplicantSequenceDiagram](images/DeleteApplicantSequenceDiagram.png)

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:
* Job recruiters who have to sieve through numerous CVs a day and contact multiple people for different job applications
* Prefer desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**: Our application helps to organize the contacts by priority, keeps contacts in a centralized workspace, streamlines workflow and increases efficiency of recruiter work


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ----------------------------------------| ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new recruiter                           | see usage instructions         | refer to instructions when I forget how to use the App                 |
|  `* * *` | recruiter                               | add an Applicant that is applying for a Job | save the Applicant’s details into the database            |
|  `* * *` | recruiter                               | add the name of Applicant that applied | know the name of the Applicant |
|  `* * *` | recruiter                               | add the phone number of Applicant that applied | know how to contact the Applicant |
|  `* * *` | recruiter                               | add the email of Applicant that applied | know an alternative method to contact the Applicant |
|  `* * *` | recruiter                               | add the home address of Applicant that applied | match Jobs that are closer to the Applicant’s home|
|  `* * *` | recruiter                               | add the date the Applicant that applied | know and sort the Applicant list by the date applied |
|  `* * *` | recruiter                               | add the NRIC of Applicant that applied | know the NRIC of the Applicant |
|  `* * *` | recruiter                               | add tags that are associated with the  Applicant | add additional information associated with the Applicant |
|  `* * *` | recruiter                               | edit an Applicant’s details  | keep the contact details updated during the recruitment process |
|  `* * *` | recruiter                               | edit the name of Applicant that applied | update the name of the Applicant |
|  `* * *` | recruiter                               | edit the phone number of Applicant that applied | update how to contact the Applicant |
|  `* * *` | recruiter                               | edit the email of Applicant that applied | update an alternative method to contact the Applicant |
|  `* * *` | recruiter                               | edit the home address of Applicant that applied | update the address of an Applicant |
|  `* * *` | recruiter                               | edit the date the Applicant that applied | update and keep track of the date applied|
|  `* * *` | recruiter                               | edit the NRIC of Applicant that applied | update the NRIC of the Applicant in case of any error |
|  `* * *` | recruiter                               | edit tags that are associated with the  Applicant | update additional information associated with the Applicant |
|  `* * *` | recruiter                               | edit the Job ID of the Job that the Applicant that applied | keep track and sort the list of Applicants by Job ID |
|  `* * *` | recruiter                               | edit the Qualification the Applicant has | match the Applicant’s qualification to the Job qualification requirement |
|  `* * *` | recruiter                               | edit the Interview Date of the Applicant | know when to contact the Applicant again for an interview reminder |
|  `* * *` | recruiter                               | delete an Applicant contact| keep the database neat |
|  `* * *` | recruiter                               | mark status of the Applicant’s interview status | keep track of the status of all Applicants |
|  `* * *` | recruiter                               | mark whether an Applicant has been interviewed |  make sure not to contact him twice |
|  `* * *` | recruiter                               | mark a contact as pending for a Job posting| keep track of Applicants who are pending|
|  `* * *` | recruiter                               | create a new Job posting | add the Job into the database and find suitable Applicants for a Job opening |
|  `* * *` | recruiter                               | add the Job Title of a new Job posting | know the name of the job |
|  `* * *` | recruiter                               | add the company name that is providing the new Job posting | know which company the Job Posting is for |
|  `* * *` | recruiter                               | add the ID of the Job posting | assign the Applicant to a Job |
|  `* * *` | recruiter                               | add the address of where the Job is at | match Applicant to Jobs that are near their home address |
|  `* * *` | recruiter                               | add the Salary of a new Job posting | provide the Salary information to Applicants |
|  `* * *` | recruiter                               | add the position (Part time, Contract, Permanent) of a new Job posting | provide the position information to Applicants |
|  `* * *` | recruiter                               | add the Qualifications required for the Job posting | match the Applicant’s qualification to the Job qualification requirement |
|  `* * *` | recruiter                               | edit a current Job posting | update the Job in the database and find suitable Applicants for a Job opening |
|  `* * *` | recruiter                               | edit the Job Title of a new Job posting | update the name of the job |
|  `* * *` | recruiter                               | edit the company name that is providing the new Job posting | edit which company the Job Posting is for |
|  `* * *` | recruiter                               | edit the ID of the Job posting | edit which job is assigned to the Applicant |
|  `* * *` | recruiter                               | edit the address of where the Job is at | better match Applicant to Jobs that are near their home address |
|  `* * *` | recruiter                               | edit the Salary of a new Job posting | provide updated Salary information to Applicants |
|  `* * *` | recruiter                               | edit the position (Part time, Contract, Permanent) of a new Job posting | provide the updated position information to Applicants |
|  `* * *` | recruiter                               | edit the qualifications required for the Job posting | better match the Applicant’s qualification to the Job qualification requirement |
|  `* * *` | recruiter                               | delete a Job posting contact| keep the database neat |
|  `* * *` | recruiter                               | mark whether the Job is filled or not | know which Job posting is currently still hiring |
|  `* * *` | recruiter                               | list out all Applicants in my database| view all Applicants in the database |
|  `* * *` | recruiter                               | list out all Job postings in my database| view all Job postings and also |
|  `* * *` | recruiter                               |  Sort all Applicants in my database by the date applied | prioritise which Applicants to Job match for |
|  `* * *` | recruiter                               |  Sort all Applicants in my database by the interview date | check whose interviews are coming up and send them a reminder |
|  `* * *` | recruiter                               |  Sort all Applicants in my database by the Job ID | check which Job I have applied for which Applicant |
|  `* * *` | recruiter                               |  Sort all Jobs in my database by whether the Job is filled or not | prioritise on Jobs that are not filled yet |
|  `* * *` | recruiter                               |  Search the Applicant database for a particular Applicant | quickly search for the Applicant when needed |
|  `* * *` | recruiter                               |  Search the Jobs database for a particular Job | quickly search for the Job when needed |
|  `* *` | recruiter                               | record down that replies from the interviewee during the interview| have a centralized location for all interviewees’ answers |
|  `* *` | recruiter                               | save the questions that need to be asked during the interview| ask important questions during the interview|
|  `*` | recruiter                               | add the Employer's contact details | keep track of the employer’s contact details. |
|  `*` | recruiter                               | add the qualification requirement that an Employer is looking for | know what type of Applicants the Employer is looking for|
|  `*` | recruiter                               | edit Employer’s details| keep the details up to date|


*{More to be added}*

### Use cases


**Use case: Mark an Applicant as interviewed**

Precondition: Recruiter knows the applicant contact ID

**MSS**

1. Recruiter requests to mark a specific Applicant as interviewed given their ID and /i flag.
2. ReCLIne marks the person as interviewed.

    Use case ends.

**Extensions**

* 1a. ID is not given

    * 1a1. ReCLIne shows an error message.
    * 1a2. Recruiter enters the command with a valid ID

       Use case resumes from step 2.

* 1b. The given ID is invalid.

    * 1b1. ReCLIne shows an error message.
    * 1b2. Recruiter enters the command with another ID

      Steps 1b1 and 1b2 repeats until Recruiter enters a valid Id. Use case resumes from step 2.

*{More to be added}*



**Use case: Delete an Applicant Contact**

Precondition: Recruiter knows the applicant contact ID

Guarantees:
-  ReCLine will only delete a contact if the contact exists.

**MSS**

1.  Recruiter indicates that he wants to delete a contact.
6.  ReCLine deletes indicated contact from database and shows a success message.

    Use case ends

**Extensions**

* 2a.  Indicated contact is not found on the database.
    * 2a1. ReCLine shows an error message.
    * 2a2. Recruiter enters new data.

Steps 2a1 – 2a2 are repeated until the contact inputted exists in the database.
Use case resumes at step 3.

* 3a. The given index is invalid.

    * 3a1. ReCLIne shows an error message.

      Use case resumes at step 2.

*{More to be added}*

**Use case: Mark an Applicant job application status as pending.**

**MSS**

1. Recruiter enters command to mark an applicant job application status as pending.
2. ReCLIne gives confirmation and mark status as pending.
3. ReCLIne displays the updated applicant list.

   Use case ends.

**Extensions**

* 1a. Recruiter enters wrong command.
    * 1a1. ReCLIne responds saying that command is
      invalid.
    * 1a2. Recruiter enters new command.


Steps 1a1-1a2 are repeated until recruiter enters
correct command.

Use case resumes from step 2.


* 1b. Recruiter enters wrong format for mark command.
    * 1b1. ReCLIne responds with the correct format for
      mark command.
    * 1b2. Recruiter enters new command.

Steps 1b1-1b2 are repeated until recruiter enters
correct format.
Use case resumes from step 2.

* 1c. Recruiter inputs an applicant that is not found in the
  address book.
    * 1c1. ReCLIne responds with invalid applicant.
    * 1c2. Recruiter enters new command.
      Steps 1c1-1c2 are repeated until recruiter enters
      valid applicant.

      Use case resumes from step 2.


*{More to be added}*

**Use case: Edit the date of an Applicant upcoming job interview**

Precondition: Recruiter knows the applicant contact ID

**MSS**

1. Recruiter enters command to edit date of interview of an Applicant.
2. ReCLIne gives confirmation and displays new Applicant attributes.

   Use case ends.

**Extensions**

* 1a. Recruiter inputs an invalid Id

    * 1a1. ReCLIne shows an error message saying that Id is invalid.
    * 1a2. Recruiter enters new edit command with correct Id.

      Steps 1a1-1a2 are repeated until recruiter enters
      valid Id. Use case resumes from step 2.

* 1b. Recruiter enters the wrong flag.

    * 1b1. ReCLIne shows an error message saying that argument is invalid.
    * 1b2. Recruiter enters new edit command with correct flag.

      Steps 1b1-1b2 are repeated until recruiter enters
      correct flag. Use case resumes from step 2.

*{More to be added}*

Use case: List out all job postings in my database

Precondition: Recruiter has previously added job postings

MSS

1. Recruiter enters command to list all job postings.
2. ReCLIne gives confirmation and displays all job posting.

   Use case ends.

Extensions

* 1a. There are no job postings in the database

    * 1a1. ReCLIne shows message that stating that there are no job postings yet
    * 1a2. Recruiter adds job posting (if applicable, otherwise stop attempting to list)

      Steps 1a1-1a2 are repeated until recruiter adds a job posting. Use case resumes from step 2.

* 1b. Recruiter enters unnecessary/invalid argument

    * 1b1. ReCLIne shows an error message saying that argument is invalid.
    * 1b2. Recruiter enters new list command without argument.

      Steps 1b1-1b2 are repeated until recruiter removes invalid argument. Use case resumes from step 2.

*{More to be added}*


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 contacts without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
able to accomplish most of the tasks faster using commands than using the mouse.
4. Documentaion follows Java SE convention.
5. The system should respond within two seconds.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
