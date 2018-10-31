package controll;

public interface Command {

	void execute();
	void undo();
	void redo();
	
}
