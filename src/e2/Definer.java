package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Definer {
    private List<Integer> pressed;

    public Definer() {
        this.pressed = new ArrayList<>();
    }

    public void press(Integer number) {
        this.pressed.add(number);
    }

    public Optional<Integer> execute(Operation op) {
        Optional<Integer> returnValue = pressed.stream().reduce(op.getOp());
        pressed.clear();
        return returnValue;
    }

    public List<Integer> getPressed() {
        return pressed;
    }
}
