package auxiliary;

public class Pair<E,F> {
	public E first = null;
	public F second = null;
	
	/**
	 * Constructs a pair of 2 elements
	 * @param fst = first element
	 * @param snd = second element
	 */
	public Pair(E fst, F snd) {
		first = fst;
		second = snd;
	}
	
	public Pair() {}
	
	public Pair(Pair<E, F> p) {
		first = p.first;
		second = p.second;
	}
	
	public boolean isEmpty() {
		return (first == null) || (second == null);
	}

	@Override
	public String toString() {
		return "<" + first + ", " + second + ">";
	}
}
