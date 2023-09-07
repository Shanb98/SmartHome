class TimeData {
    private int startHours;
    private int startMinutes;
    private int endHours;
    private int endMinutes;

    public TimeData(int startHours, int startMinutes, int endHours, int endMinutes) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
    }

    public int getStartHours() {
        return startHours;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public int getEndHours() {
        return endHours;
    }

    public int getEndMinutes() {
        return endMinutes;
    }
}
