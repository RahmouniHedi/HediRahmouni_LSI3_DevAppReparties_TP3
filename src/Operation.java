// Fichier: Operation.java
import java.io.Serializable;

public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;
    private int op1, op2;
    private char operator;
    private int result;

    public Operation(int op1, int op2, char operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    // Getters et Setters
    public int getOp1() { return op1; }
    public int getOp2() { return op2; }
    public char getOperator() { return operator; }
    public int getResult() { return result; }
    public void setResult(int result) { this.result = result; }
}