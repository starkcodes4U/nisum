package DAY_2;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
            Person other = (Person) obj;
            return this.name.equals(other.name) && this.age == other.age;
        }
        return false;
    }

    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    public int hashCode() {
        return name.hashCode() + age;
    }
}

public class ObjectMethodsDemo {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Alice", 25);
        Person p3 = new Person("Bob", 30);

        System.out.println("p1 toString(): " + p1.toString());
        System.out.println("p1 equals p2: " + p1.equals(p2));
        System.out.println("p1 equals p3: " + p1.equals(p3));
        System.out.println("p1 hashCode(): " + p1.hashCode());
        System.out.println("p2 hashCode(): " + p2.hashCode());
    }
}
// Output:
// p1 toString(): Person{name='Alice', age=25}  
// p1 equals p2: true
// p1 equals p3: false
// p1 hashCode(): 63350393
// p2 hashCode(): 63350393
// The hashCode() method returns the same value for p1 and p2 because they are equal.
// The equals() method checks if two Person objects have the same name and age.
// The toString() method provides a string representation of the Person object.
