package pack.viktor.pq;

/**
 * Interfejs klasa koje implementiraju strukturu indeksiranog
 * reda sa prioritetom
 *
 * @author Viktor Slavkovic
 * @param <Key>
 */
public interface MinIdxPriorityQueue< Key> {

    public void insert(int idx, Key val);

    public void deleteMin();

    public int getMinIdx();

    public Key getMin();

    public void decreaseKey(int idx, Key newVal);

    public boolean isEmpty();

    public Key getVal(int idx);

    public boolean exists(int idx);
}
