import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RecursiveLister extends JFrame {
    private JTextArea fileDisplayArea;
    private JButton startButton;
    private JButton quitButton;

    public RecursiveLister() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Recursive File Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);


        fileDisplayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(fileDisplayArea);
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");


        addComponents(scrollPane);


        startButton.addActionListener(e -> selectDirectory());
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void addComponents(JScrollPane scrollPane) {
        setLayout(new BorderLayout());
        add(createTitleLabel(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Recursive File Lister", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        return titleLabel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        return buttonPanel;
    }

    private void selectDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            listFiles(selectedDirectory);
        }
    }

    private void listFiles(File directory) {
        fileDisplayArea.setText("");
        listFilesRecursive(directory);
    }

    private void listFilesRecursive(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursive(file);
                } else {
                    fileDisplayArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecursiveLister().setVisible(true));
    }
}
