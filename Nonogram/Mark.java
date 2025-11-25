package Nonogram;
/* Mark.java
 * PURPOSE: Represents different types of marks that can be placed on the grid
 * OOP CONCEPTS: Abstraction, Inheritance, Polymorphism
 */

// Abstract class - cannot be instantiated directly
// This demonstrates ABSTRACTION - hiding implementation details
abstract class Mark {
    protected String symbol; // What it looks like (X, O, ■, .)
    protected int value; // Internal numeric value

    // Constructor - called by subclasses
    public Mark(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    // Abstract method - MUST be implemented by subclasses
    // This demonstrates POLYMORPHISM - each mark displays differently
    public abstract String display();

    // Getter - demonstrates ENCAPSULATION
    public int getValue() {
        return value;
    }
}

// Concrete subclass - represents marking with X
class MarkX extends Mark {
    public MarkX() {
        super("X", 1); // Calls parent constructor
    }

    @Override
    public String display() {
        return "X ";
    }
}

// Concrete subclass - represents marking with O
class MarkO extends Mark {
    public MarkO() {
        super("O", 2);
    }

    @Override
    public String display() {
        return "O ";
    }
}

// Concrete subclass - represents filled square (solid block)
class MarkFilled extends Mark {
    public MarkFilled() {
        super("■", 3);
    }

    @Override
    public String display() {
        return "■ ";
    }
}

// Concrete subclass - represents empty square
class MarkEmpty extends Mark {
    public MarkEmpty() {
        super(".", 0);
    }

    @Override
    public String display() {
        return ". ";
    }
}