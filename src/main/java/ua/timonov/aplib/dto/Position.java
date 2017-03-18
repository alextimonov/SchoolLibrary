package ua.timonov.aplib.dto;

/**
 * Available positions for employees
 */
public enum Position {
    DIRECTOR,
    DEPUTY_DIRECTOR,
    LIBRARIAN,
    TEACHER,
    TUTOR,
    SECURITY,
    CLEANER;

    public static Position byName(String name) {
        if (name == null) return null;
        Position[] positions = Position.values();
        for (Position position : positions) {
            if (name.equals(position.name()))
                return position;
        }
        return null;
    }
}
