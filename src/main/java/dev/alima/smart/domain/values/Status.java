package dev.alima.smart.domain.values;

public enum Status {
    DONE('D'), PROGRESS('P'), TODO('T');

    private final Character status;

    Status(Character status) {
        this.status = status;
    }

    public Character getStatus() {
        return status;
    }
}
