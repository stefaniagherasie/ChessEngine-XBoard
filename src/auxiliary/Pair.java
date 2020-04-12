package auxiliary;

public class Pair<E,F> {
	public E first;
	public F second;
	
	/**
	 * Constructs a pair of 2 elements
	 * @param fst = first element
	 * @param snd = second element
	 */
	public Pair(E fst, F snd) {
		first = fst;
		second = snd;
	}
}
