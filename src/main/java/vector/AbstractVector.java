package vector;

public class AbstractVector<T extends Number> implements Vector<T> {

    private final T[] components;

    public AbstractVector(int size) {
        components = (T[]) new Number[size];
    }

    @Override
    public T get(int index) {
        return components[index];
    }

    @Override
    public void set(int index, T value) {
        components[index] = value;
    }

    @Override
    public int getSize() {
        return components.length;
    }
}
