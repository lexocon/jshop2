package JSHOP2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class JSHOP2Output {
  public enum BacktrackReason {
    NOBACKTRACKING, NOOPERATOR, NOMETHOD, NOBINDINGFORPRECONDOP, NOBINDINGFORPRECONDME, NOBRANCHAPPLICABLE
    }
	
  protected static Domain plandomain;
  private static Vector<OutputPlanStep> simplePlan;
  private static Vector<OutputPlanStepDetail> extendedPlan;
  private static Vector<OutputPlanStepDetail> backtrackList;
  private static Vector<OutputPlanStepDetail> debugPlan;
	
  public static void setPlans(ArrayList<PlanStepInfo> plans){
    int stepIndex = 1;
    simplePlan = new Vector<OutputPlanStep>();
    extendedPlan = new Vector<OutputPlanStepDetail>();
    backtrackList = new Vector<OutputPlanStepDetail>();
    debugPlan = new Vector<OutputPlanStepDetail>();
    boolean lasterror = false;
    Stack<OutputPlanStepDetail> stack =  new Stack<OutputPlanStepDetail>();
    for(PlanStepInfo x: plans){
      String taskName = "";
      if(x.taskAtom != null)
        taskName = x.taskAtom.toString();
      OutputPlanStepDetail step = new OutputPlanStepDetail(taskName , x.action, stepIndex);
      if(x.action=="STATECHANGED"){
        step.setName(x.operatorInstance);
        step.setDeleteList(x.delAdd[0]);
        step.setAddList(x.delAdd[1]);
        step.setAction(x.action);
        stack.push(step);		
        }
				
      if(x.action =="REDUCED"){
        step.setName(x.method);
        stack.push(step);
        }
				
      if(x.action=="BACKTRACKING"){
        step.setBacktrackReason(x.backtrackReason);
        if(!stack.empty())
          stack.pop();					
					
        if(!lasterror){
          if(x.backtrackPrecondition != null){
            Vector<String> missingPreconditions = new Vector<String>();
							
            try {
              for(Precondition precond : x.backtrackPrecondition.p){
                if(precond instanceof PreconditionAtomic){
                  String currentBoundP = ((PreconditionAtomic) precond).getBoundP();
                  if(!x.state.contains(currentBoundP))
                    missingPreconditions.add(currentBoundP);
                  }
                if(precond instanceof PreconditionCall){
                  String currentBoundT = ((PreconditionCall) precond).getBoundT();
                  missingPreconditions.add(currentBoundT);
                  }
                }
              step.setMissingPrecond(missingPreconditions);
              }catch (Exception e){}							
            }
          lasterror = true;
          backtrackList.add(step);
          } 					
					
        } else {
          lasterror = false;
          }
      debugPlan.add(step);
      stepIndex++;
			}
    extendedPlan = new Vector<OutputPlanStepDetail>(stack);
    for(OutputPlanStepDetail step : extendedPlan){
      simplePlan.add(new OutputPlanStep(step.getTaskAtom(), step.getAction(), step.getIndex()));
      }
	}
	
  public static void setDomain(Domain domain){
    plandomain = domain;
	}
	
  public static Vector<OutputPlanStep> getSimplePlan(){
    return simplePlan;
	}

  public static Vector<OutputPlanStepDetail> getExtendedPlan(){
    return extendedPlan;
	}
	
  public static Vector<OutputPlanStepDetail> getBacktrackInformation(){
    return backtrackList;
	}
	
  public static Vector<OutputPlanStepDetail> getDebugInformation(){
    return debugPlan;
	}
	
  public static void writeAll() throws FileNotFoundException, UnsupportedEncodingException{
    PrintWriter writerSimple = new PrintWriter("SimplePlan.txt", "UTF-8");
    PrintWriter writerDetail = new PrintWriter("ExtendedPlan.txt", "UTF-8");
    PrintWriter writerBacktrack = new PrintWriter("BacktrackInformation.txt", "UTF-8");
    PrintWriter writerDebug = new PrintWriter("Debug.txt", "UTF-8");
    for(OutputPlanStep x : simplePlan){
      writerSimple.println(x);
		}
		
    for(OutputPlanStepDetail x : extendedPlan){
      writerDetail.println(x);
		}
		
    for(OutputPlanStepDetail x : backtrackList){
      writerBacktrack.println(x);
		}
		
    for(OutputPlanStepDetail x : debugPlan){
      writerDebug.println(x);
		}
    writerSimple.close();
    writerDetail.close();
    writerBacktrack.close();
    writerDebug.close();
	}
	
	
	public JSHOP2Output(){
	}
}
