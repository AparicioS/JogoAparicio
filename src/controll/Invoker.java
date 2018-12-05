package controll;

import java.util.ArrayList;
import java.util.List;


public class Invoker {

	private List<Command> todos = new ArrayList<>();

	private List<Command> undo = new ArrayList<>();

	public void execute(Command comm) {
		comm.execute();
		todos.add(comm);
	}

	public void undo() {
		if(!todos.isEmpty()) {
			Command comm = todos.remove(todos.size()-1);
			comm.undo();
			undo.add(comm);}

	}

	public void redo() {
		if(!undo.isEmpty()) {
			Command comm = undo.remove(undo.size()-1);
			comm.redo();
			todos.add(comm);}
		
	}

	public void imprimir() {

		System.out.println("Log :");
		for (Command comm : todos) {
			System.out.println(" " + comm);
		}
	}		
		public boolean existe() {
			return !this.todos.isEmpty();
		}



}
