package eflang.ear.core;

public class Argument {
    public enum Type {
        CONSTANT, CELL
    }

    private Type type;
    private int value;

    private Argument(Type type, int value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public static Argument constant(int value) {
        return new Argument(Type.CONSTANT, value);
    }

    public static Argument cell(int value) {
        return new Argument(Type.CELL, value);
    }

    public static ArgumentValidator validator() {
        return new ArgumentValidator();
    }

    public String toString() {
        if (type == Type.CELL) {
            return String.format("@%d", value);
        } else {
            return String.format("%d", value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Argument)) {
            return false;
        }

        Argument that = (Argument) o;
        return (this.type == that.type) && (this.value == that.value);
    }

    @Override
    public int hashCode() {
        return type.hashCode() ^ Integer.hashCode(value);
    }
}
