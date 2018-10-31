package controll;

import java.util.ArrayList;
import java.util.List;

import controle.Command;

public class Invoker {

	private List<Command> todos = new ArrayList<>();

	private List<Command> undo = new ArrayList<>();

	public void execute(Command comm) {
		comm.execute();
		todos.add(comm);
	}

	public void undo() {

		Command comm = todos.remove(todos.size() - 1);
		comm.undo();
		undo.add(comm);

	}

	public void redo() {
		if(!todos.isEmpty()) {
			Command comm = todos.remove(todos.size()-1);
			comm.undo();
			undo.add(comm);}
		
	}

	public void imprimir() {

		System.out.println("Log :");
		for (Command comm : todos) {
			System.out.println(" " + comm);
		}

	}

}
