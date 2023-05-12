package businessObjects;


public class Performance {

   private String name;

     private String description;

   public Performance(String name, String description) {
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
