package lobecompiler;

public class ValueTree implements Evaluable {
	public Operator mOperator;	
	public Evaluable mLeft;
	public Evaluable mRight;	
	
	public ValueTree(Operator operator, 
			         Evaluable left, 
			         Evaluable right) {
		mOperator = operator;
		mLeft = left;
		mRight = right;		
	}
	
	public int getDepth() {
		return Math.max(mLeft.getDepth(), mRight.getDepth()) + 1;
	}
	
	public Value evaluate(LOBECompiler compiler) {	
		Value leftVal = mLeft.evaluate(compiler);
		Value rightVal = mRight.evaluate(compiler);
		return compiler.evaluate(mOperator, leftVal, rightVal);
	}
}
