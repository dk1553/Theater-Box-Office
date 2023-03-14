package test.model;

import javax.persistence.Entity;

@Entity
public class Tutor extends Person {
    public Tutor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Tutor() {
    }
}
