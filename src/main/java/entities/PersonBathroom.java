package entities;

public class PersonBathroom extends Thread {
    Bathroom bathroom;
    Person person;

    public PersonBathroom(Bathroom bathroom) {
        System.out.println("PersonBathroom");
        this.bathroom = bathroom;
        this.person = new Person();
    }

    private int getRandomDelay(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void use() {
        tryEnter();
        try {
            Thread.sleep(getRandomDelay(1000, 4000));

            removePerson();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void removePerson() {
        bathroom.getPeople().remove(person);

        if(bathroom.getPeople().size() == 0) {
            System.out.println("SEX: " + this.bathroom.getSex());
            bathroom.setSex(null);
        }
    }

    @Override
    public void run() {
        this.use();
    }

    private synchronized void tryEnter() {
        System.out.println("tryEnter >   " + this.person.toString());
        System.out.println("QTD: " + bathroom.getPeople().size() );
        waitIfExceededMaximumCapacity();
        waitIfSexNotMatch();

        if (bathroom.bathroomIsEmpty()) {
            bathroom.setSex(person.getSex());
        }
        bathroom.setPerson(person);
        System.out.println("entrou   >   " + this.person.toString());
        notify();
    }

    private void waitIfExceededMaximumCapacity() {
        while (bathroom.isExceededMaximumCapacity()) {
            System.out.println("Bathroom is full.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitIfSexNotMatch() {
        while (!bathroom.isSexEquals(person.getSex())) {
            System.out.println("Bathroom is sex opposite.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
