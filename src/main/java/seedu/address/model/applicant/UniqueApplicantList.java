package seedu.address.model.applicant;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.applicant.exceptions.ApplicantNotFoundException;
import seedu.address.model.applicant.exceptions.DuplicateApplicantException;

public class UniqueApplicantList implements Iterable<Applicant> {
    private final ObservableList<Applicant> internalList =
            FXCollections.observableArrayList();
    private final ObservableList<Applicant> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Applicant as the given argument.
     */
    public boolean contains(Applicant toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameApplicant);
    }

    /**
     * Adds an Applicant to the list.
     * The Applicant must not already exist in the list.
     */
    public void add(Applicant toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateApplicantException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the list.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the list.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ApplicantNotFoundException();
        }

        if (!target.isSameApplicant(editedApplicant) && contains(editedApplicant)) {
            throw new DuplicateApplicantException();
        }

        internalList.set(index, editedApplicant);
    }

    /**
     * Removes the equivalent applicant from the list.
     * The applicant must exist in the list.
     */
    public void remove(Applicant toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ApplicantNotFoundException();
        }
    }

    public void setApplicants(UniqueApplicantList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        requireAllNonNull(applicants);
        if (!applicantAreUnique(applicants)) {
            throw new DuplicateApplicantException();
        }

        internalList.setAll(applicants);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Applicant> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Applicant> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueApplicantList // instanceof handles nulls
                && internalList.equals(((UniqueApplicantList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code applicants} contains only unique applicants.
     */
    private boolean applicantAreUnique(List<Applicant> applicants) {
        for (int i = 0; i < applicants.size() - 1; i++) {
            for (int j = i + 1; j < applicants.size(); j++) {
                if (applicants.get(i).isSameApplicant(applicants.get(j))) {
                    return false;
                }
            }
        }
        return true;

    }
}
