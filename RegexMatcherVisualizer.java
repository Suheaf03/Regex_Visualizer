import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RegexMatcherVisualizer extends JFrame {
    private JTextField inputField;
    private JTextField patternField;
    private JButton matchButton;
    private DrawingPanel panel;
    private Dp_Solution solution;

    public RegexMatcherVisualizer() {
        setTitle("Suheafs Project Regex Matcher Visualizer");
        setSize(600, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(20);
        patternField = new JTextField(20);
        matchButton = new JButton("Check Match");

        panel = new DrawingPanel(750, 430); 
        solution = new Dp_Solution();

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Input String:"));
        topPanel.add(inputField);
        topPanel.add(new JLabel("Regex Pattern:"));
        topPanel.add(patternField);
        topPanel.add(matchButton);

        add(topPanel);

        matchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String pattern = patternField.getText();
                
                solution.clearMemo();
                
                boolean isMatch = solution.isMatch(input, pattern);

                Graphics g = panel.getGraphics();
                panel.clear(); 

                g.setFont(new Font("SansSerif", Font.BOLD, 50));
                g.setColor(Color.BLACK);
                g.drawString("Input: " + input, 50, 50);

                g.setColor(Color.BLUE);
                g.drawString("Pattern: " + pattern, 50, 100);

                if (isMatch) {
                    g.setColor(Color.GREEN);
                    g.drawString("Match: YES", 50, 150);
                } else {
                    g.setColor(Color.RED);
                    g.drawString("Match: NO", 50, 150);
                }
            }
        });

        panel.onClick((x, y) -> {
            Graphics g = panel.getGraphics();
            g.setColor(Color.MAGENTA);
            g.fillOval(x, y, 10, 10);
        });

        panel.onKeyDown((key) -> {
            if (key == KeyEvent.VK_R) {
                panel.setBackground(Color.RED);
            } else if (key == KeyEvent.VK_G) {
                panel.setBackground(Color.GREEN);
            } else if (key == KeyEvent.VK_B) {
                panel.setBackground(Color.BLUE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegexMatcherVisualizer gui = new RegexMatcherVisualizer();
            gui.setVisible(true);
            gui.panel.setVisible(true);
        });
    }
}
