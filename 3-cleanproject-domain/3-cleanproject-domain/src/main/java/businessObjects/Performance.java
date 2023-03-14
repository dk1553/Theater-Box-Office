package businessObjects;
import  javax.persistence.*;
@Entity
@Table(name = "performances")
public final class Performance {
    @Id
    @Column(name = "name")
    private  String name;
    @Column(name = "description")
    private  String description;

    public Performance(String name, String description) {
        this.description=description;
        this.name=name;
    }

    protected Performance() {


    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
