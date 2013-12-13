package JSHOP2;

import java.util.Vector;

public class OutputPlanStepDetail extends OutputPlanStep {
	private Vector<String> deleteList;
	private Vector<String> addList;
	private Vector<String> missingPrecond;
	private int backtrackReason;
	
	public OutputPlanStepDetail(int index){
		super(index);
	}
	
	public OutputPlanStepDetail(String taskAtom, String action, int index){
		super(taskAtom, action,index);
	}
	
	public Vector<String> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(Vector<String> deleteList) {
		this.deleteList = deleteList;
	}

	public Vector<String> getAddList() {
		return addList;
	}

	public void setAddList(Vector<String> addList) {
		this.addList = addList;
	}

	public int getBacktrackReason() {
		return backtrackReason;
	}

	public void setBacktrackReason(int backtrackReason) {
		this.backtrackReason = backtrackReason;
	}

	public Vector<String> getMissingPrecond() {
		return missingPrecond;
	}

	public void setMissingPrecond(Vector<String> missingPrecond) {
		this.missingPrecond = missingPrecond;
	}
	
	public String toString(){
		String returnString = "Step: "+getIndex()+" Action: "+getAction()+" (TaskAtom: "+getTaskAtom()+")\n";
		returnString += "DeleteList: ";
		if(getDeleteList() != null) {
			for(Object x :getDeleteList()){
				returnString += x+ " ";
			}
		}
		returnString +="\n";
		returnString += "AddList: ";
		if(getAddList() != null) {
			for(Object x :getAddList()){
				returnString += x+ " ";
			}
		}
		returnString +="\n";
		returnString += "BacktrackReason: ";
		switch(getBacktrackReason()){
		case JSHOP2Output.NOBACKTRACKING:		
			returnString += "No backtrack happened";
			break;
		case JSHOP2Output.NOBINDINGFORPRECONDOP:
			returnString += "No binding found(Operator): No binding could satisfy the current precondition";
			break;
		case JSHOP2Output.NOBINDINGFORPRECONDME:
			returnString += "No binding found(Method): No binding could satisfy the current precondition";
			break;
		case JSHOP2Output.NOBRANCHAPPLICABLE:
			returnString += "There is no branch in the chosen method that satisfies the precondition";
			break;
		case JSHOP2Output.NOMETHOD:
			returnString += "No method found: No methods can decompose this task";
			break;
		case JSHOP2Output.NOOPERATOR:
			returnString += "No operator found: No operator can achieve this task";
			break;
			default:
				break;
		}
		returnString +="\n";
		returnString += "Missing Preconditions: ";
		if(getMissingPrecond() != null) {
			for(String x :getMissingPrecond()){
				returnString += x+ " ";
			}
		}
		returnString +="\n";
		return returnString;
		
	}
}
