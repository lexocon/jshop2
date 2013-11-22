package JSHOP2;

public class OutputPlanStep {
	private String Name;
	private int Index;
	private String Action; 
	private String TaskAtom;
	
	public OutputPlanStep(int index){
		setIndex(index);
	}
	
	public OutputPlanStep(String taskAtom, String action, int index){
		setTaskAtom(taskAtom);
		setAction(action);
		setIndex(index);
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getIndex() {
		return Index;
	}

	public void setIndex(int index) {
		Index = index;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getTaskAtom() {
		return TaskAtom;
	}

	public void setTaskAtom(String taskAtom) {
		TaskAtom = taskAtom;
	}
	
	public String toString(){
		return "Step: "+getIndex()+" Action: "+getAction()+" (TaskAtom: "+getTaskAtom()+")";
	}
}
