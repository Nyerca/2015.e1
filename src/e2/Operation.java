package e2;

import java.util.function.BinaryOperator;

public class Operation {
    private String name;
    private BinaryOperator<Integer> op;

    public Operation (String name, BinaryOperator<Integer> op) {
        this.name = name;
        this.op = op;
    }

    public String getName() {
        return name;
    }

    public BinaryOperator<Integer> getOp() {
        return op;
    }
}
