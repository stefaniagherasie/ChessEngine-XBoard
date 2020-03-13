package auxiliary;

public class LineErrorException extends Exception{
	private static final long serialVersionUID = 1L;
	public LineErrorException() {
		super("No line or error at reading line");
	}
}