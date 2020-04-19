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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Pair<E,F> other = ((Pair<E,F>) obj);
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
}
