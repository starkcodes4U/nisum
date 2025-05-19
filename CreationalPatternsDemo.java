// Factory Pattern
interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() { System.out.println("Woof Woof"); }
}

class Cat implements Animal {
    public void speak() { System.out.println("Meow Meow"); }
}

class AnimalFactory {
    public static Animal getAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) return new Dog();
        if ("cat".equalsIgnoreCase(type)) return new Cat();
        return null;
    }
}

// Abstract Factory Pattern
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

interface Button { void paint(); }
interface Checkbox { void paint(); }

class WinButton implements Button {
    public void paint() { System.out.println("Windows Button"); }
}

class MacButton implements Button {
    public void paint() { System.out.println("Mac Button"); }
}

class WinCheckbox implements Checkbox {
    public void paint() { System.out.println("Windows Checkbox"); }
}

class MacCheckbox implements Checkbox {
    public void paint() { System.out.println("Mac Checkbox"); }
}

class WinFactory implements GUIFactory {
    public Button createButton() { return new WinButton(); }
    public Checkbox createCheckbox() { return new WinCheckbox(); }
}

class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Singleton Pattern
class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
    public void showMessage() {
        System.out.println("Singleton instance");
    }
}

// Prototype Pattern
interface Prototype extends Cloneable {
    Prototype clone() throws CloneNotSupportedException;
}

class Person implements Prototype {
    private String name;
    public Person(String name) { this.name = name; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public Prototype clone() throws CloneNotSupportedException { return (Person) super.clone(); }
    public String toString() { return "Person: " + name; }
}

// Builder Pattern
class Computer {
    private String CPU;
    private String RAM;
    private String storage;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
    }

    public String toString() {
        return "CPU: " + CPU + ", RAM: " + RAM + ", Storage: " + storage;
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class CreationalPatternsDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Factory Pattern demo
        System.out.println("Factory Pattern:");
        Animal dog = AnimalFactory.getAnimal("dog");
        dog.speak();
        Animal cat = AnimalFactory.getAnimal("cat");
        cat.speak();

        System.out.println("\nAbstract Factory Pattern:");
        GUIFactory factory = new WinFactory();
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        button.paint();
        checkbox.paint();

        System.out.println("\nSingleton Pattern:");
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        s1.showMessage();
        System.out.println("Same instance? " + (s1 == s2));

        System.out.println("\nPrototype Pattern:");
        Person p1 = new Person("John");
        Person p2 = (Person) p1.clone();
        System.out.println(p1);
        System.out.println(p2);
        p2.setName("Jane");
        System.out.println("After changing clone's name:");
        System.out.println(p1);
        System.out.println(p2);

        System.out.println("\nBuilder Pattern:");
        Computer comp = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM("16GB")
                .setStorage("1TB SSD")
                .build();
        System.out.println(comp);
    }
}
