import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton submitButton;
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft;
    private int currentQuestionIndex;
    private String[][] questions;
    private String[] correctAnswers;

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Question panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(5, 1));

        questionLabel = new JLabel();
        questionPanel.add(questionLabel);

        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
            questionPanel.add(optionButtons[i]);
        }

        add(questionPanel, BorderLayout.CENTER);


        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);


        timerLabel = new JLabel();
        add(timerLabel, BorderLayout.NORTH);


        questions = new String[][]{
                {"Who is the ceo of Google?", "Satya Nadella", "Larry Ellison", "Sundar Pichai", "Steve Ballmer"},
                {"Who is the father of Artificial Intelligence?","James Gosling","Dennis Ritchie","Arthur Samuel",
                        "John McCarthy"},
                {"Which city is called Silicon city of India?", "Bengaluru", "Mumbai", "Delhi", "Chennai"}
        };

        correctAnswers = new String[]{"Sundar Pichai", "John McCarthy", "Bengaluru"};

        // Set initial question index
        currentQuestionIndex = 0;
        showQuestion();


        timeLeft = 120;
        timerLabel.setText("Time left: " + timeLeft + " seconds");


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + " seconds");


                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time's up!");

                }
            }
        });
        timer.start();
    }

    private void showQuestion() {
        questionLabel.setText(questions[currentQuestionIndex][0]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(questions[currentQuestionIndex][i + 1]);
            optionButtons[i].setSelected(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Check answer
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    String selectedAnswer = optionButtons[i].getText();
                    if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
                        JOptionPane.showMessageDialog(null, "Correct!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is: " + correctAnswers[currentQuestionIndex]);
                    }
                    break;
                }
            }


            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                showQuestion();
            } else {
                JOptionPane.showMessageDialog(null, "Quiz completed!");

            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}

