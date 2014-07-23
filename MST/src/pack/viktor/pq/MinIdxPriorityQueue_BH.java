package pack.viktor.pq;

import java.util.NoSuchElementException;

/**
 * Klasa koja predstavlja tip indeksiranog reda sa prioritetom implementiran
 * binarnim hipom. Klasa je genericka za tipove koji nasledjuju Comparable.
 *
 * @author Viktor Slavkovic
 * @param <Key>
 */
public class MinIdxPriorityQueue_BH<Key extends Comparable<Key>> implements MinIdxPriorityQueue<Key> {

    private int idx_LeftChild(int idx) {
        return (idx < _vn / 2) ? (2 * idx + 1) : (-1);
    }

    private int idx_RightChild(int idx) {
        return (idx < (_vn - 1) / 2) ? (2 * idx + 2) : (-1);
    }

    private int idx_Parent(int idx) {
        return (idx > 0) ? ((idx - 1) / 2) : (-1);
    }

    private int idx_PChild(int idx) {
        int lc = idx_LeftChild(idx);
        int rc = idx_RightChild(idx);

        if ((lc < 0) && (rc < 0)) {
            return -1;
        } else if (lc < 0) {
            return rc;
        } else if (rc < 0) {
            return lc;
        } else {
            return (_vals[lc].compareTo(_vals[rc]) < 0) ? lc : rc;
        }
    }

    private void swap(int a_idx, int b_idx) {
        Key p = _vals[a_idx];
        _vals[a_idx] = _vals[b_idx];
        _vals[b_idx] = p;

        int p1 = _id[_ii[a_idx]];
        _id[_ii[a_idx]] = _id[_ii[b_idx]];
        _id[_ii[b_idx]] = p1;

        p1 = _ii[a_idx];
        _ii[a_idx] = _ii[b_idx];
        _ii[b_idx] = p1;
    }

    private void perculateDown(int idx) {
        int c = idx_PChild(idx);
        while ((c > 0) && (_vals[c].compareTo(_vals[idx]) < 0)) {
            swap(c, idx);
            idx = c;
            c = idx_PChild(idx);
        }
    }

    private void perculateUp(int idx) {
        int p = idx_Parent(idx);
        while ((p >= 0) && (_vals[idx].compareTo(_vals[p]) < 0)) {
            swap(p, idx);
            idx = p;
            p = idx_Parent(idx);
        }
    }

    private Key _vals[];
    private int _vn;
    private int _id[];
    private int _ii[];

    /**
     * Konstruktor koji vrsi standardne inicijalizacije;
     *
     * @param	maxSize	Maksimalni broj elemenata u redu;
     * @param	maxIdxSize	Maksimalna velicnia indeksa;
     * @throws	IllegalArgumentException	Ako su argumenti negativni;
     */
    public MinIdxPriorityQueue_BH(int maxSize, int maxIdxSize) {

        if (maxSize < 0) {
            throw new IllegalArgumentException();
        }

        if (maxIdxSize < 0) {
            throw new IllegalArgumentException();
        }

        _vals = (Key[]) new Comparable[maxSize];
        _ii = new int[maxSize];
        _id = new int[2 * maxIdxSize];
        _vn = 0;

        for (int i = 0; i < _id.length; i++) {
            _id[i] = -1;
        }
        for (int i = 0; i < _ii.length; i++) {
            _ii[i] = -1;
        }
    }

    /**
     * Metod za ubacivanje vrednosti u red;
     *
     * @param	idx	Indeks;
     * @param	val	Vrednost koja se indeksira;
     * @throws	IllegalArgumentException	Ako vec postoji vrednost pod tim
     * indeksom;
     */
    @Override
    public void insert(int idx, Key val) {

        if (_id[idx] != -1) {
            throw new IllegalArgumentException();
        }
        if (_vn < _vals.length) {
            _id[idx] = _vn;
            _ii[_vn] = idx;
            _vals[_vn] = val;
            _vn++;
            perculateUp(_vn - 1);
        } else {
            throw new RuntimeException("PQ is full.");
        }
    }

    /**
     * Metod za izbacivanje najprioritetnijeg clana;
     *
     * @throws	RuntimeException	Ako je red prazan;
     */
    @Override
    public void deleteMin() {
        if (isEmpty()) {
            throw new RuntimeException("PQ is empty.");
        }
        _vn--;
        _id[_ii[0]] = -1;
        _ii[0] = _ii[_vn];
        _id[_ii[_vn]] = 0;
        _ii[_vn] = -1;
        _vals[0] = _vals[_vn];
        if (!isEmpty()) {
            perculateDown(0);
        }
    }

    /**
     * Metod za dobijanje indeksa najprioritetnijeg clana;
     *
     * @throws	RuntimeException	Ako je red prazan;
     * @return	Vraca indeks.
     */
    @Override
    public int getMinIdx() {
        if (isEmpty()) {
            throw new RuntimeException("PQ is empty.");
        }
        return _ii[0];
    }

    /**
     * Metod za dobijanje vrednosti najprioritetnijeg clana;
     *
     * @throws	RuntimeException	Ako je red prazan;
     * @return	Vraca vrednost.
     */
    @Override
    public Key getMin() {
        if (isEmpty()) {
            throw new RuntimeException("PQ is empty.");
        }
        return _vals[0];
    }

    /**
     * Metod za smanjivanje vrednosti pod odredjenim indeksom;
     *
     * @param	idx	Indeks;
     * @param	newVal	Nova vrednost za taj indeks;
     * @throws	RuntimeException	Ako se vrednost povecava ili ako nema vrednosti
     * pod tim indeksom;
     */
    @Override
    public void decreaseKey(int idx, Key newVal) {
        if (_id[idx] == -1) {
            throw new RuntimeException("Faild to decrease non-indexed key.");
        }
        if (newVal.compareTo(_vals[_id[idx]]) > 0) {
            throw new RuntimeException("You are trying to increase key.");
        }
        _vals[_id[idx]] = newVal;
        perculateUp(_id[idx]);
    }

    /**
     * Metod koji proverava da li je red prazan;
     *
     * @return	Vraca odgovarajuci boolean.
     */
    @Override
    public boolean isEmpty() {
        return _vn == 0;
    }

    /**
     * Metod za pristup vrednosti pod odredjenim indeksom;
     *
     * @param	idx	Indeks;
     * @throws	NoSuchElementException	Ako nema vrednosti pod tim indeksom;
     * @return	Vraca vrednost
     */
    @Override
    public Key getVal(int idx) {
        if (!exists(idx)) {
            throw new NoSuchElementException();
        }
        return _vals[_id[idx]];
    }

    /**
     * Metod koji proverava da li postoji vrednost pod odredjenim indeksom;
     *
     * @param	idx	Indeks;
     * @return	Vraca odgovarajuci boolean.
     */
    @Override
    public boolean exists(int idx) {
        return _id[idx] != -1;
    }

}
