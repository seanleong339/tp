package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job listings' position: full-time or part-time.
 * Guarantees: immutable; is valid as declared in {@link #validPosition(String)}
 */
public class Position {
    private static final String PREFIX_POSITION_FULLTIME = "ft";
    private static final String PREFIX_POSITION_PARTTIME = "pt";

    private static final String POSITION_FULLTIME = "full-time";
    private static final String POSITION_PARTTIME = "part-time";

    private static final String POSITION_INVALID = "Job position should be 'ft' (full-time) or 'pt' (part-time).";
    private final String position;

    /**
     * Instantiates a new Position.
     *
     * @param position the position
     */
    public Position(String position) {
        requireNonNull(position);
        checkArgument(validPosition(position), POSITION_INVALID);
        this.position = position.toLowerCase();
    }

    /**
     * Checks if a given string is a valid position.
     *
     * @param position the position
     * @return if the given position is valid
     */
    public static boolean validPosition(String position) {
        return position.toLowerCase().matches(PREFIX_POSITION_FULLTIME + "|" + PREFIX_POSITION_PARTTIME);
    }

    @Override
    public String toString() {
        return position.equals(PREFIX_POSITION_FULLTIME)
                ? POSITION_FULLTIME
                : POSITION_PARTTIME;
    }
}
