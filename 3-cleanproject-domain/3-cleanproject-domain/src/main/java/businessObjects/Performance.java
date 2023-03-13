package businessObjects;

public final class Performance {
    private final String name;
    private final String description;

    public Performance(String name, String description) {
        this.description=description;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
