package hellojpa;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class Album extends Item {
    private String artist;
}
