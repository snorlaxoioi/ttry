import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchGUI {

    private JFrame frame;
    private JLabel timerLabel;
    private JButton startStopButton, resetButton;
    private Timer timer;
    private int elapsedTime = 0; // Time in milliseconds
    private boolean running = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchGUI::new);
    }

    public StopwatchGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        // Timer label
        timerLabel = new JLabel(formatTime(elapsedTime), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(timerLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        startStopButton = new JButton("Start");
        startStopButton.addActionListener(new StartStopAction());
        buttonPanel.add(startStopButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetAction());
        buttonPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Timer
        timer = new Timer(100, e -> updateTimer());

        frame.setVisible(true);
    }

    private void updateTimer() {
        elapsedTime += 100;
        timerLabel.setText(formatTime(elapsedTime));
    }

    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / 60000) % 60;
        int hours = milliseconds / 3600000;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private class StartStopAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (running) {
                    timer.stop();
                    startStopButton.setText("Start");
                } else {
                    timer.start();
                    startStopButton.setText("Stop");
                }
                running = !running;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ResetAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                timer.stop();
                elapsedTime = 0;
                timerLabel.setText(formatTime(elapsedTime));
                startStopButton.setText("Start");
                running = false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
