import java.util.*;

// Adapter Pattern
interface Target {
    void request();
}

class Adaptee {
    void specificRequest() {
        System.out.println("Adaptee: Specific request");
    }
}

class Adapter implements Target {
    private Adaptee adaptee;
    Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    public void request() {
        adaptee.specificRequest();
    }
}

// Bridge Pattern
interface Implementor {
    void operationImpl();
}

class ConcreteImplementorA implements Implementor {
    public void operationImpl() {
        System.out.println("ConcreteImplementorA operation");
    }
}

class ConcreteImplementorB implements Implementor {
    public void operationImpl() {
        System.out.println("ConcreteImplementorB operation");
    }
}

abstract class Abstraction {
    protected Implementor implementor;
    Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }
    abstract void operation();
}

class RefinedAbstraction extends Abstraction {
    RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }
    void operation() {
        System.out.print("RefinedAbstraction calls -> ");
        implementor.operationImpl();
    }
}

// Composite Pattern
interface Component {
    void operation();
}

class Leaf implements Component {
    private String name;
    Leaf(String name) {
        this.name = name;
    }
    public void operation() {
        System.out.println("Leaf " + name + " operation");
    }
}

class Composite implements Component {
    private List<Component> children = new ArrayList<>();
    private String name;
    Composite(String name) {
        this.name = name;
    }
    public void add(Component c) {
        children.add(c);
    }
    public void operation() {
        System.out.println("Composite " + name + " operation");
        for (Component c : children) {
            c.operation();
        }
    }
}

// Decorator Pattern
interface Component2 {
    void operation();
}

class ConcreteComponent implements Component2 {
    public void operation() {
        System.out.println("ConcreteComponent operation");
    }
}

class ConcreteDecorator implements Component2 {
    private Component2 component;
    ConcreteDecorator(Component2 component) {
        this.component = component;
    }
    public void operation() {
        System.out.println("Decorator before operation");
        component.operation();
        System.out.println("Decorator after operation");
    }
}

// Facade Pattern
class SubSystem1 {
    void operation1() {
        System.out.println("Subsystem1 operation");
    }
}

class SubSystem2 {
    void operation2() {
        System.out.println("Subsystem2 operation");
    }
}

class Facade {
    private SubSystem1 ss1 = new SubSystem1();
    private SubSystem2 ss2 = new SubSystem2();
    void operation() {
        System.out.println("Facade delegates operations:");
        ss1.operation1();
        ss2.operation2();
    }
}

// Flyweight Pattern
interface Flyweight {
    void operation(String extrinsicState);
}

class ConcreteFlyweight implements Flyweight {
    private String intrinsicState;
    ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }
    public void operation(String extrinsicState) {
        System.out.println("Flyweight [" + intrinsicState + "] with extrinsic state " + extrinsicState);
    }
}

class FlyweightFactory {
    private Map<String, Flyweight> flyweights = new HashMap<>();
    Flyweight getFlyweight(String key) {
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(key));
        }
        return flyweights.get(key);
    }
}

// Proxy Pattern
interface Subject {
    void request();
}

class RealSubject implements Subject {
    public void request() {
        System.out.println("RealSubject handling request");
    }
}

class Proxy implements Subject {
    private RealSubject realSubject;
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        System.out.println("Proxy delegating request");
        realSubject.request();
    }
}

// Demo class with main method
public class StructuralPatternsDemo {
    public static void main(String[] args) {

        // Adapter Pattern demo
        System.out.println("=== Adapter Pattern Demo ===");
        Target target = new Adapter(new Adaptee());
        target.request();

        // Bridge Pattern demo
        System.out.println("\n=== Bridge Pattern Demo ===");
        Abstraction absA = new RefinedAbstraction(new ConcreteImplementorA());
        Abstraction absB = new RefinedAbstraction(new ConcreteImplementorB());
        absA.operation();
        absB.operation();

        // Composite Pattern demo
        System.out.println("\n=== Composite Pattern Demo ===");
        Composite root = new Composite("root");
        Composite branch = new Composite("branch");
        Leaf leaf1 = new Leaf("leaf1");
        Leaf leaf2 = new Leaf("leaf2");
        branch.add(leaf1);
        root.add(branch);
        root.add(leaf2);
        root.operation();

        // Decorator Pattern demo
        System.out.println("\n=== Decorator Pattern Demo ===");
        Component2 simple = new ConcreteComponent();
        Component2 decorated = new ConcreteDecorator(simple);
        decorated.operation();

        // Facade Pattern demo
        System.out.println("\n=== Facade Pattern Demo ===");
        Facade facade = new Facade();
        facade.operation();

        // Flyweight Pattern demo
        System.out.println("\n=== Flyweight Pattern Demo ===");
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fw1 = factory.getFlyweight("A");
        Flyweight fw2 = factory.getFlyweight("B");
        Flyweight fw3 = factory.getFlyweight("A"); // same as fw1
        fw1.operation("state1");
        fw2.operation("state2");
        fw3.operation("state3");

        // Proxy Pattern demo
        System.out.println("\n=== Proxy Pattern Demo ===");
        Subject proxy = new Proxy();
        proxy.request();
    }
}
