package JSHOP2;

import java.util.Vector;

import JSHOP2.JSHOP2Output.BacktrackReason;

public class OutputPlanStepDetail extends OutputPlanStep {
	private Vector<String> deleteList;
	private Vector<String> addList;
	private Vector<String> missingPrecond;
	private BacktrackReason backtrackReason;
	
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

	public BacktrackReason getBacktrackReason() {
		return backtrackReason;
	}

	public void setBacktrackReason(BacktrackReason backtrackReason2) {
		this.backtrackReason = backtrackReason2;
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
		case NOBACKTRACKING:		
			returnString += "No backtrack happened";
			break;
		case NOBINDINGFORPRECONDOP:
			returnString += "No binding found(Operator): No binding could satisfy the current precondition";
			break;
		case NOBINDINGFORPRECONDME:
			returnString += "No binding found(Method): No binding could satisfy the current precondition";
			break;
		case NOBRANCHAPPLICABLE:
			returnString += "There is no branch in the chosen method that satisfies the precondition";
			break;
		case NOMETHOD:
			returnString += "No method found: No methods can decompose this task";
			break;
		case NOOPERATOR:
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
