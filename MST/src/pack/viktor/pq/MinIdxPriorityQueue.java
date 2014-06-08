/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pack.viktor.pq;

/**
 *
 * @author Viktor
 * @param <Key>
 */

public interface MinIdxPriorityQueue < Key > {
    public void insert(int idx, Key val);
    public void deleteMin();
    public int getMinIdx();
    public Key getMin();
    public void decreaseKey(int idx, Key newVal);
    public boolean isEmpty();
    public Key getVal(int idx);
    public boolean exists(int idx);
}
