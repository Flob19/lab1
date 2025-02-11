import java.util.*;

public interface Loadable<T> {
    T offLoad();
    void load(T t);
}
