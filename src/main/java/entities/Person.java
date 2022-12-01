package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Person {
    private int id;
    private LocalDateTime createdDate;
    private Sex sex;

    public Person() {
        sex = Sex.randomSex();
        this.id = id;
        this.createdDate = LocalDateTime.now();
    }

    public Person(int id, Sex sex) {
        this.id = id;
        this.sex = sex;
        this.createdDate = LocalDateTime.now();
    }

    public String getCreatedDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dtf.format(createdDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Person))
            return false;
        Person person = (Person) o;
        return getId() == person.getId() && sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), sex);
    }

    @Override
    public String toString() {
        return "Person{" +
                ", createdDate=" + getCreatedDate() +
                ", sex=" + sex +
                '}';
    }
}
