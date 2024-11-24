package mk.ukim.finki.wp.lab.model;

import lombok.Getter;
import java.util.Random;

public class BaseEntity {

    @Getter
    private Long id;
    Random rd = new Random();

    public BaseEntity() {
        this.id = generateId();
    }

    private Long generateId() {
        return Math.abs(rd.nextLong());
    }
}
