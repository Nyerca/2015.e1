package e2;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class GUI extends JFrame {

    public GUI() {
        // Inizializzazione base
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        final JPanel south = new JPanel(new FlowLayout());

        List<JButton> buttons = new ArrayList<>();

        Definer definer = new Definer();

        Operation op = new Operation("Sum", Integer::sum);
        Operation op2 = new Operation("Mult", (x, y) -> x * y);
        List<Operation> ops = Arrays.asList(op, op2);
        ops.forEach(el -> {
            JButton opBtn = new JButton(el.getName());
            opBtn.addActionListener(e -> {
                if (definer.getPressed().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "INVALID");
                } else {
                    Optional<Integer> result = definer.execute(el);
                    result.ifPresent(integer -> JOptionPane.showMessageDialog(this, el.getName() + " is " + integer));
                    buttons.forEach(btn -> btn.setEnabled(true));
                }
            });
            south.add(opBtn);
        });

        this.getContentPane().add(BorderLayout.SOUTH, south);

        // Pannello centrale
        JPanel center = new JPanel(new FlowLayout());
        IntStream.range(0, 100).forEach(el -> {
            JButton btn = new JButton(String.valueOf(el));

            btn.addActionListener(e -> {
                JButton o = (JButton) e.getSource();
                String name = o.getText();
                btn.setEnabled(false);
                definer.press(Integer.parseInt(name));
            });
            buttons.add(btn);
            center.add(btn);
        });

        this.getContentPane().add(BorderLayout.CENTER, center);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

}
