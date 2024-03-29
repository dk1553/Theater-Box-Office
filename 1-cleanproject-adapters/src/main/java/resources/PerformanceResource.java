package resources;

public class PerformanceResource {
    private final String name;
    private final String description;

    public PerformanceResource(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
