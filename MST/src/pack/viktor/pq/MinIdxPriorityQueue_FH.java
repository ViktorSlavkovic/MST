package pack.viktor.pq;

/**
 * Klasa koja predstavlja tip indeksiranog reda sa prioritetom implementiran
 * Fibonacijevim hipom. Klasa je genericka za tipove koji nasledjuju Comparable.
 * 
 * @author Viktor
 * @param <Key>
 */
public class MinIdxPriorityQueue_FH<Key extends Comparable<Key>> implements MinIdxPriorityQueue<Key> {
    
    //IMPLEMENT!!
        
    @Override
    public void insert(int idx, Key val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMinIdx() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Key getMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decreaseKey(int idx, Key newVal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Key getVal(int idx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(int idx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
