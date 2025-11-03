package Design;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ImageLabelExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageLabelExample().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("CanData Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);

        // CardLayout für Seitenverwaltung
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Seiten erstellen
        JPanel startPage = createStartPage(cardLayout, mainPanel);
        JPanel imagePage = createImagePage();

        // Seiten hinzufügen
        mainPanel.add(startPage, "start");
        mainPanel.add(imagePage, "images");

        // Hauptframe konfigurieren
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Startseite zeigen
        cardLayout.show(mainPanel, "start");
    }

    // Start Page
    private JPanel createStartPage(CardLayout layout, JPanel mainPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 245, 250));

        // ====== HEADER ======
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(panel.getBackground());
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("CanBeData", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 42));
        title.setForeground(new Color(50, 70, 130));
        title.setBorder(BorderFactory.createEmptyBorder(60, 0, 10, 0));

        JLabel subtitle = new JLabel("A product of CanBe Studios");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 34));
        subtitle.setForeground(new Color(90, 100, 130));
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        JTextPane description = new JTextPane();
        description.setEditable(false);
        description.setOpaque(false);
        description.setText(
                "This product helps visualize data and create basic analytics.\n" +
                        "It does not save any data." +
                        " Feel free to give feedback @Coding-bz."
        );
        description.setFont(new Font("SansSerif", Font.ITALIC, 20));
        description.setForeground(new Color(70, 70, 70));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 80));

// --- Text mittig ausrichten ---
        StyledDocument doc = description.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        headerPanel.add(title);
        headerPanel.add(subtitle);
        headerPanel.add(description);

        // ====== BUTTONS ======
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(panel.getBackground());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 120, 0));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));

        JButton startButton = new JButton("Start");
        styleButton(startButton, new Color(80, 140, 255));
        startButton.addActionListener(e -> layout.show(mainPanel, "images"));

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, new Color(220, 70, 70));
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // ====== ADD TO MAIN PANEL ======
        panel.add(headerPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void styleButton(JButton button, Color baseColor) {
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(baseColor);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(baseColor.darker(), 1, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
    }


    private JPanel createImagePage() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        int width = 300;
        int height = 300;

        try {
            BufferedImage imgPie = ImageIO.read(new File("C:\\Users\\elifb\\OneDrive\\Dokumente\\GitHub\\TBZ_m320_elif_luka\\02-projects\\Projects\\CanData\\src\\Design\\diagram_images\\PieChartDemo1.png"));
            BufferedImage imgPie3d = ImageIO.read(new File("C:\\Users\\elifb\\OneDrive\\Dokumente\\GitHub\\TBZ_m320_elif_luka\\02-projects\\Projects\\CanData\\src\\Design\\diagram_images\\PieChart3DDemo1.png"));
            BufferedImage imgBar = ImageIO.read(new File("C:\\Users\\elifb\\OneDrive\\Dokumente\\GitHub\\TBZ_m320_elif_luka\\02-projects\\Projects\\CanData\\src\\Design\\diagram_images\\XYBarChartDemo.png"));
            BufferedImage imgSpline = ImageIO.read(new File("C:\\Users\\elifb\\OneDrive\\Dokumente\\GitHub\\TBZ_m320_elif_luka\\02-projects\\Projects\\CanData\\src\\Design\\diagram_images\\XYSplineRendererDemo.png"));

            panel.add(createClickableImage(imgPie, width, height, "Pie Chart Seite"));
            panel.add(createClickableImage(imgPie3d, width, height, "3D Pie Chart Seite"));
            panel.add(createClickableImage(imgBar, width, height, "Balkendiagramm Seite"));
            panel.add(createClickableImage(imgSpline, width, height, "Spline Diagramm Seite"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return panel;
    }

    private static JLabel createClickableImage(BufferedImage img, int width, int height, String title) {
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openNewPage();
            }
        });

        return label;
    }

    private static void openNewPage() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        JFreeChart jfreechart = ChartFactory.createPieChart("Category 1", dataset, true, true, true);
        ChartFrame chartframe = new ChartFrame("Category 1", jfreechart);
        PiePlot3D piePlot3D = new PiePlot3D();
        piePlot3D.setDataset(dataset);
        dataset.setValue("Category 1", Double.valueOf("10"));
        dataset.setValue("Category 2", Double.valueOf("20"));
        dataset.setValue("Category 3", Double.valueOf("30"));
        dataset.setValue("Category 4", Double.valueOf("40"));

        JTextField jTextField = new JTextField();
        jTextField.setHorizontalAlignment(JTextField.CENTER);
        jTextField.setEditable(true);
        piePlot3D.setDataset(dataset);
        chartframe.add(jTextField);
        chartframe.setVisible(true);

    }
}
