
/**
* This is the TeacherGUI class .
* author@ Ramesh Sapkota
* ID: np05cp4a230104 London Met ID: 23049378
*/
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

class RoundedCornerBorder extends AbstractBorder {
    private static final Color ALPHA_ZERO = new Color(0x0, true);

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape border = getBorderShape(x, y, width - 1, height - 1);
        g2.setPaint(ALPHA_ZERO);
        Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
        corner.subtract(new Area(border));
        g2.fill(corner);
        g2.setPaint(Color.GRAY);
        g2.draw(border);
        g2.dispose();
    }

    public Shape getBorderShape(int x, int y, int w, int h) {
        int r = h / 2; // height/2
        return new RoundRectangle2D.Double(x, y, w, h, r, r);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(4, 8, 4, 8);
        return insets;
    }
}

public class TeacherGUI {
    // ArrayList to store teacher objects
    private ArrayList<Teacher> teachersList = new ArrayList<>();
    private JFrame frame;

    // Buttons for various functionalities
    private JButton AddLecturerButton;
    private JButton displayLecturerButton;
    private JButton AddTutorButton;
    private JButton displayTutorButton;
    private JButton gradeAssignmentButton;
    private JButton removeTutorButton;
    private JButton SetSalaryButton;

    // Text fields for adding lecturer details
    private JTextField teacherIdLecturerField;
    private JTextField teacherNameLecturerField;
    private JTextField addressLecturerField;
    private JTextField workingTypeLecturerField;
    private JTextField employmentStatusLecturerField;
    private JTextField workingHoursLecturerField;
    private JTextField departmentLecturerField;
    private JTextField experienceLecturerField;

    // Text fields for adding tutor details
    private JTextField teacherIdTutorField;
    private JTextField teacherNameTutorField;
    private JTextField addressTutorField;
    private JTextField workingTypeTutorField;
    private JTextField employmentStatusTutorField;
    private JTextField workingHoursTutorField;
    private JTextField academicQualificationField;
    private JTextField performingIndexField;
    private JTextField salaryField;
    private JTextField specializationField;

    // Text fields for grading assignment
    private JTextField teacherIdGradeAssignmentfield;
    private JTextField gradedScoregradeAssignmnetField;
    private JTextField departmentGradeAssignmentField;
    private JTextField experienceGradeAssignmentField;

    // Text field for removing tutor
    private JTextField teacherIdRemoveTutorField;

    // Text fields for setting salary
    private JTextField teacherIdSetSalaryField;
    private JTextField salarySetSalaryfield;
    private JTextField performingIndexSetSalaryField;

    // Initial position of the GUI frame
    private JButton clearButton;
    private int posX;
    private int posY;

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            {
                setContentAreaFilled(false);
                setFocusPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(new Color(0, 150, 136));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(0, 150, 136, 200));
                } else {
                    g2.setColor(new Color(0, 150, 136, 180));
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();

                super.paintComponent(g);
            }
        };
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Poppins", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Helper method to create styled text fields
    private JTextField createStyledTextField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };
        field.setBackground(new Color(30, 30, 30));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("Poppins", Font.PLAIN, 14));
        return field;
    }

    // Constructor to initialize the GUI
    public TeacherGUI() {
        // Initialize the JFrame
        frame = new JFrame("TeacherGUI");
        frame.setSize(1260, 680);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setBackground(new Color(0, 0, 0, 0));

        // Enable antialiasing for smooth rendering
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        // Set custom JOptionPane UI
        UIManager.put("OptionPane.background", new Color(28, 28, 35));
        UIManager.put("Panel.background", new Color(28, 28, 35));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(0, 150, 136));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Poppins", Font.BOLD, 14));
        UIManager.put("OptionPane.messageFont", new Font("Poppins", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Poppins", Font.BOLD, 14));

        // Modern frame design with gradient background
        frame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create gradient background
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(28, 28, 35),
                        0, getHeight(), new Color(18, 18, 25));
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Add subtle border
                g2d.setStroke(new BasicStroke(2f));
                g2d.setColor(new Color(70, 70, 80, 100));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20);

                // Add highlight effect at the top
                g2d.setColor(new Color(255, 255, 255, 30));
                g2d.drawLine(25, 2, getWidth() - 25, 2);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        });
        frame.getContentPane().setLayout(null);

        // Initialize ArrayList to store teachers
        teachersList = new ArrayList<>();

        // Ultra-modern close button with glass effect and smooth animations
        JButton closeButton = new JButton() {
            private float alpha = 0.0f;
            private Timer fadeTimer;

            {
                fadeTimer = new Timer(20, e -> {
                    if (getModel().isRollover() && alpha < 1.0f) {
                        alpha = Math.min(1.0f, alpha + 0.1f);
                    } else if (!getModel().isRollover() && alpha > 0.0f) {
                        alpha = Math.max(0.0f, alpha - 0.1f);
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                    repaint();
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                // Create glass effect background
                int arc = getHeight();
                if (getModel().isPressed()) {
                    g2.setColor(new Color(255, 69, 58));
                } else {
                    Color baseColor = new Color(255, 69, 58);
                    Color hoverColor = new Color(255, 59, 48);
                    float[] baseHSB = Color.RGBtoHSB(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(),
                            null);
                    float[] hoverHSB = Color.RGBtoHSB(hoverColor.getRed(), hoverColor.getGreen(), hoverColor.getBlue(),
                            null);
                    float h = baseHSB[0] + (hoverHSB[0] - baseHSB[0]) * alpha;
                    float s = baseHSB[1] + (hoverHSB[1] - baseHSB[1]) * alpha;
                    float b = baseHSB[2] + (hoverHSB[2] - baseHSB[2]) * alpha;
                    g2.setColor(Color.getHSBColor(h, s, b));
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                // Add glass highlight
                GradientPaint glassEffect = new GradientPaint(
                        0, 0, new Color(255, 255, 255, 60),
                        0, getHeight() / 2, new Color(255, 255, 255, 0));
                g2.setPaint(glassEffect);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                // Draw X symbol
                g2.setColor(new Color(255, 255, 255));
                g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int padding = 12;
                g2.drawLine(padding, padding, getWidth() - padding, getHeight() - padding);
                g2.drawLine(padding, getHeight() - padding, getWidth() - padding, padding);

                g2.dispose();
            }

            @Override
            public void setModel(ButtonModel model) {
                super.setModel(model);
                model.addChangeListener(e -> {
                    if (getModel().isRollover()) {
                        fadeTimer.start();
                    } else {
                        fadeTimer.start();
                    }
                });
            }
        };
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBounds(1220, 10, 28, 28);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> System.exit(0));
        frame.getContentPane().add(closeButton);

        // Ultra-modern minimize button with glass effect and smooth animations
        JButton minimizeButton = new JButton() {
            private float alpha = 0.0f;
            private Timer fadeTimer;

            {
                fadeTimer = new Timer(20, e -> {
                    if (getModel().isRollover() && alpha < 1.0f) {
                        alpha = Math.min(1.0f, alpha + 0.1f);
                    } else if (!getModel().isRollover() && alpha > 0.0f) {
                        alpha = Math.max(0.0f, alpha - 0.1f);
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                    repaint();
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                // Create glass effect background
                int arc = getHeight();
                if (getModel().isPressed()) {
                    g2.setColor(new Color(128, 128, 128));
                } else {
                    Color baseColor = new Color(169, 169, 169);
                    Color hoverColor = new Color(192, 192, 192);
                    float[] baseHSB = Color.RGBtoHSB(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(),
                            null);
                    float[] hoverHSB = Color.RGBtoHSB(hoverColor.getRed(), hoverColor.getGreen(), hoverColor.getBlue(),
                            null);
                    float h = baseHSB[0] + (hoverHSB[0] - baseHSB[0]) * alpha;
                    float s = baseHSB[1] + (hoverHSB[1] - baseHSB[1]) * alpha;
                    float b = baseHSB[2] + (hoverHSB[2] - baseHSB[2]) * alpha;
                    g2.setColor(Color.getHSBColor(h, s, b));
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                // Add glass highlight
                GradientPaint glassEffect = new GradientPaint(
                        0, 0, new Color(255, 255, 255, 60),
                        0, getHeight() / 2, new Color(255, 255, 255, 0));
                g2.setPaint(glassEffect);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

                // Draw minimize line
                g2.setColor(new Color(255, 255, 255));
                g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int padding = 10;
                int y = getHeight() / 2;
                g2.drawLine(padding, y, getWidth() - padding, y);

                g2.dispose();
            }

            @Override
            public void setModel(ButtonModel model) {
                super.setModel(model);
                model.addChangeListener(e -> {
                    if (getModel().isRollover()) {
                        fadeTimer.start();
                    } else {
                        fadeTimer.start();
                    }
                });
            }
        };
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBounds(1188, 10, 28, 28);
        minimizeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeButton.addActionListener(e -> frame.setState(JFrame.ICONIFIED));
        frame.getContentPane().add(minimizeButton);

        // Modern heading with custom font and shadow effect
        JLabel heading = new JLabel("Teacher Management System") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                // Draw shadow
                g2.setColor(new Color(0, 0, 0, 50));
                g2.drawString(getText(), 3, 33);

                // Draw main text
                g2.setColor(Color.GRAY);
                g2.drawString(getText(), 2, 32);

                g2.dispose();
            }
        };
        heading.setBounds(450, 3, 600, 40);
        heading.setFont(new Font("Poppins", Font.BOLD, 32));
        frame.getContentPane().add(heading);

        // Modern academic panel style with gradient and shadow
        JPanel panelAddLecturer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create gradient background
                GradientPaint gp = new GradientPaint(0, 0, new Color(28, 28, 35, 250),
                        0, getHeight(), new Color(18, 18, 25, 250));
                g2.setPaint(gp);

                // Draw main panel background with rounded corners
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Add subtle border
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(new Color(70, 70, 80, 100));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);

                // Add highlight at top
                g2.setColor(new Color(255, 255, 255, 30));
                g2.drawLine(25, 2, getWidth() - 25, 2);

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panelAddLecturer.setBounds(20, 50, 400, 450);
        panelAddLecturer.setLayout(null);
        panelAddLecturer.setOpaque(false);
        frame.getContentPane().add(panelAddLecturer);

        // Heading label for lecturer panel
        JLabel headingTeacher = new JLabel("Lecturer");
        headingTeacher.setBounds(150, 10, 400, 40);
        headingTeacher.setFont(new Font("Poppins", Font.BOLD, 25));
        headingTeacher.setForeground(Color.gray);
        panelAddLecturer.add(headingTeacher);

        // Labels and text fields for adding lecturer details

        // Teacher ID
        JLabel teacherIdLabel = new JLabel("Teacher ID:");
        teacherIdLabel.setBounds(30, 60, 200, 35);
        teacherIdLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        teacherIdLabel.setForeground(Color.gray);
        panelAddLecturer.add(teacherIdLabel);
        // Create modern text field with rounded corners
        teacherIdLecturerField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };
        teacherIdLecturerField.setBounds(200, 60, 180, 27);
        teacherIdLecturerField.setBackground(new Color(30, 30, 30));
        teacherIdLecturerField.setForeground(Color.WHITE);
        teacherIdLecturerField = createStyledTextField();
        teacherIdLecturerField.setBounds(200, 60, 180, 27);
        panelAddLecturer.add(teacherIdLecturerField);

        // Teacher Name
        JLabel teacherNameLabel = new JLabel("Teacher Name:");
        teacherNameLabel.setBounds(30, 100, 200, 35);
        teacherNameLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        teacherNameLabel.setForeground(Color.gray);
        panelAddLecturer.add(teacherNameLabel);
        teacherNameLecturerField = createStyledTextField();
        teacherNameLecturerField.setBounds(200, 100, 180, 27);
        panelAddLecturer.add(teacherNameLecturerField);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 140, 200, 35);
        addressLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        addressLabel.setForeground(Color.gray);
        panelAddLecturer.add(addressLabel);
        addressLecturerField = createStyledTextField();
        addressLecturerField.setBounds(200, 140, 180, 27);
        panelAddLecturer.add(addressLecturerField);

        // Working Type
        JLabel workingTypeLabel = new JLabel("Working Type:");
        workingTypeLabel.setBounds(30, 180, 200, 35);
        workingTypeLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        workingTypeLabel.setForeground(Color.gray);
        panelAddLecturer.add(workingTypeLabel);
        workingTypeLecturerField = createStyledTextField();
        workingTypeLecturerField.setBounds(200, 180, 180, 27);
        panelAddLecturer.add(workingTypeLecturerField);

        // Employment Status
        JLabel employmentStatusLabel = new JLabel("Employment Status:");
        employmentStatusLabel.setBounds(30, 220, 200, 35);
        employmentStatusLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        employmentStatusLabel.setForeground(Color.gray);
        panelAddLecturer.add(employmentStatusLabel);
        employmentStatusLecturerField = createStyledTextField();
        employmentStatusLecturerField.setBounds(200, 220, 180, 27);
        panelAddLecturer.add(employmentStatusLecturerField);

        // workingHours
        JLabel workingHoursLabel = new JLabel("Working Hours:");
        workingHoursLabel.setBounds(30, 260, 200, 35);
        workingHoursLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        workingHoursLabel.setForeground(Color.gray);
        panelAddLecturer.add(workingHoursLabel);
        workingHoursLecturerField = createStyledTextField();
        workingHoursLecturerField.setBounds(200, 260, 180, 27);
        panelAddLecturer.add(workingHoursLecturerField);

        // Department
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(30, 300, 200, 35);
        departmentLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        departmentLabel.setForeground(Color.gray);
        panelAddLecturer.add(departmentLabel);
        departmentLecturerField = createStyledTextField();
        departmentLecturerField.setBounds(200, 300, 180, 27);
        panelAddLecturer.add(departmentLecturerField);

        // Years of Experience
        JLabel experienceLabel = new JLabel("Years of Experience:");
        experienceLabel.setBounds(30, 340, 200, 35);
        experienceLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        experienceLabel.setForeground(Color.gray);
        panelAddLecturer.add(experienceLabel);
        experienceLecturerField = createStyledTextField();
        experienceLecturerField.setBounds(200, 340, 180, 27);
        panelAddLecturer.add(experienceLecturerField);

        // Add Lecturer Button
        AddLecturerButton = createStyledButton("Add Lecturer");
        AddLecturerButton.setBounds(30, 390, 150, 40);
        panelAddLecturer.add(AddLecturerButton);

        // Action Listener for Add Lecturer Button
        AddLecturerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addLecturer();
            }
        });

        // Display Lecturer Button
        displayLecturerButton = createStyledButton("Display Lecturer");
        displayLecturerButton.setBounds(230, 390, 150, 40);
        panelAddLecturer.add(displayLecturerButton);

        // Action Listener for Display Lecturer Button
        displayLecturerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                displayLecturer();
            }
        });

        // Panel for adding Tutor with modern academic style
        JPanel panelTutor = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create gradient background
                GradientPaint gp = new GradientPaint(0, 0, new Color(28, 28, 35, 250),
                        0, getHeight(), new Color(18, 18, 25, 250));
                g2.setPaint(gp);

                // Draw main panel background with rounded corners
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Add subtle border
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(new Color(70, 70, 80, 100));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);

                // Add highlight at top
                g2.setColor(new Color(255, 255, 255, 30));
                g2.drawLine(25, 2, getWidth() - 25, 2);

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panelTutor.setBounds(430, 50, 400, 540);
        panelTutor.setLayout(null);
        panelTutor.setOpaque(false);
        frame.getContentPane().add(panelTutor);

        // Heading label for Tutor panel
        JLabel headingTutor = new JLabel("Tutor");
        headingTutor.setBounds(170, 10, 400, 40);
        headingTutor.setFont(new Font("Poppins", Font.BOLD, 25));
        headingTutor.setForeground(Color.gray);
        panelTutor.add(headingTutor);

        // Labels and text fields for adding Tutor details

        // Teacher ID
        JLabel teacherIdTutorLabel = new JLabel("Teacher ID:");
        teacherIdTutorLabel.setBounds(30, 60, 200, 35);
        teacherIdTutorLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        teacherIdTutorLabel.setForeground(Color.gray);
        panelTutor.add(teacherIdTutorLabel);
        teacherIdTutorField = createStyledTextField();
        teacherIdTutorField.setBounds(210, 60, 170, 27);
        panelTutor.add(teacherIdTutorField);

        // Teacher Name
        JLabel teacherNameTutorLabel = new JLabel("Teacher Name:");
        teacherNameTutorLabel.setBounds(30, 100, 200, 35);
        teacherNameTutorLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        teacherNameTutorLabel.setForeground(Color.gray);
        panelTutor.add(teacherNameTutorLabel);
        teacherNameTutorField = createStyledTextField();
        teacherNameTutorField.setBounds(210, 100, 170, 27);
        panelTutor.add(teacherNameTutorField);

        // Address
        JLabel addressTutorLabel = new JLabel("Address:");
        addressTutorLabel.setBounds(30, 140, 200, 35);
        addressTutorLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        addressTutorLabel.setForeground(Color.gray);
        panelTutor.add(addressTutorLabel);
        addressTutorField = createStyledTextField();
        addressTutorField.setBounds(210, 140, 170, 27);
        panelTutor.add(addressTutorField);

        // Working Type
        JLabel workingTypeTutorLabel = new JLabel("Working Type:");
        workingTypeTutorLabel.setBounds(30, 180, 200, 35);
        workingTypeTutorLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        workingTypeTutorLabel.setForeground(Color.gray);
        panelTutor.add(workingTypeTutorLabel);
        workingTypeTutorField = createStyledTextField();
        workingTypeTutorField.setBounds(210, 180, 170, 27);
        panelTutor.add(workingTypeTutorField);

        // Employment Status
        JLabel employmentStatusTutorLabel = new JLabel("Employment Status:");
        employmentStatusTutorLabel.setBounds(30, 220, 200, 35);
        employmentStatusTutorLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        employmentStatusTutorLabel.setForeground(Color.gray);
        panelTutor.add(employmentStatusTutorLabel);
        employmentStatusTutorField = createStyledTextField();
        employmentStatusTutorField.setBounds(210, 220, 170, 27);
        panelTutor.add(employmentStatusTutorField);

        JLabel workingHoursTutorLabel = new JLabel("Working Hours:");
        workingHoursTutorLabel.setBounds(30, 260, 200, 35);
        workingHoursTutorLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        workingHoursTutorLabel.setForeground(Color.gray);
        panelTutor.add(workingHoursTutorLabel);
        workingHoursTutorField = createStyledTextField();
        workingHoursTutorField.setBounds(210, 260, 170, 27);
        panelTutor.add(workingHoursTutorField);

        // Salary
        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(30, 300, 200, 35);
        salaryLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        salaryLabel.setForeground(Color.gray);
        panelTutor.add(salaryLabel);
        salaryField = createStyledTextField();
        salaryField.setBounds(210, 300, 170, 27);
        panelTutor.add(salaryField);

        // Specialization
        JLabel specializationLabel = new JLabel("Specialization:");
        specializationLabel.setBounds(30, 340, 200, 35);
        specializationLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        specializationLabel.setForeground(Color.gray);
        panelTutor.add(specializationLabel);
        specializationField = createStyledTextField();
        specializationField.setBounds(210, 340, 170, 27);
        panelTutor.add(specializationField);

        // Academic Qualifications
        JLabel academicQualificationLabel = new JLabel("Academic Qualifications:");
        academicQualificationLabel.setBounds(30, 380, 200, 35);
        academicQualificationLabel.setFont(new Font("Poppins", Font.BOLD, 15));
        academicQualificationLabel.setForeground(Color.gray);
        panelTutor.add(academicQualificationLabel);
        academicQualificationField = createStyledTextField();
        academicQualificationField.setBounds(210, 380, 170, 27);
        panelTutor.add(academicQualificationField);

        // Performance Index
        JLabel performingIndexLabel = new JLabel("Performance Index:");
        performingIndexLabel.setBounds(30, 420, 200, 35);
        performingIndexLabel.setFont(new Font("Poppins", Font.BOLD, 17));
        performingIndexLabel.setForeground(Color.gray);
        panelTutor.add(performingIndexLabel);
        performingIndexField = createStyledTextField();
        performingIndexField.setBounds(210, 420, 170, 27);
        panelTutor.add(performingIndexField);

        // Button to add Tutor
        AddTutorButton = createStyledButton("Add Tutor");
        AddTutorButton.setBounds(30, 480, 150, 40);
        panelTutor.add(AddTutorButton);

        // Action Listener for Add Tutor Button
        AddTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addTutor();
            }
        });

        // Button to display Tutor
        displayTutorButton = createStyledButton("Display Tutor");
        displayTutorButton.setBounds(230, 480, 150, 40);
        panelTutor.add(displayTutorButton);

        // Action Listener for Display Tutor Button
        displayTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                displayTutor();
            }
        });

        // Panel for grading assignment with modern academic style
        JPanel panelGradeAssignment = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create gradient background
                GradientPaint gp = new GradientPaint(0, 0, new Color(28, 28, 35, 250),
                        0, getHeight(), new Color(18, 18, 25, 250));
                g2.setPaint(gp);

                // Draw main panel background with rounded corners
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Add subtle border
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(new Color(70, 70, 80, 100));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);

                // Add highlight at top
                g2.setColor(new Color(255, 255, 255, 30));
                g2.drawLine(25, 2, getWidth() - 25, 2);

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panelGradeAssignment.setBounds(840, 350, 400, 310);
        panelGradeAssignment.setLayout(null);
        panelGradeAssignment.setOpaque(false);
        frame.getContentPane().add(panelGradeAssignment);

        // Heading label for Grade Assignment panel
        JLabel headingGradeAssignment = new JLabel("Grade Assignment");
        headingGradeAssignment.setBounds(100, 10, 400, 40);
        headingGradeAssignment.setFont(new Font("Poppins", Font.BOLD, 25));
        headingGradeAssignment.setForeground(Color.gray);
        panelGradeAssignment.add(headingGradeAssignment);

        // Labels and text fields for grading assignment details

        // Teacher ID
        JLabel teacherIdGradeAssignment = new JLabel("Teacher ID:");
        teacherIdGradeAssignment.setBounds(30, 70, 200, 35);
        teacherIdGradeAssignment.setFont(new Font("Poppins", Font.BOLD, 17));
        teacherIdGradeAssignment.setForeground(Color.gray);
        panelGradeAssignment.add(teacherIdGradeAssignment);
        teacherIdGradeAssignmentfield = createStyledTextField();
        teacherIdGradeAssignmentfield.setBounds(200, 70, 180, 27);
        panelGradeAssignment.add(teacherIdGradeAssignmentfield);

        // Department
        JLabel departmentGradeAssignment = new JLabel("Department:");
        departmentGradeAssignment.setBounds(30, 110, 200, 35);
        departmentGradeAssignment.setFont(new Font("Poppins", Font.BOLD, 17));
        departmentGradeAssignment.setForeground(Color.gray);
        panelGradeAssignment.add(departmentGradeAssignment);
        departmentGradeAssignmentField = createStyledTextField();
        departmentGradeAssignmentField.setBounds(200, 110, 180, 27);
        panelGradeAssignment.add(departmentGradeAssignmentField);

        // Years of Experience
        JLabel experienceGradeAssignment = new JLabel("Years of Experience:");
        experienceGradeAssignment.setBounds(30, 150, 200, 35);
        experienceGradeAssignment.setFont(new Font("Poppins", Font.BOLD, 17));
        experienceGradeAssignment.setForeground(Color.gray);
        panelGradeAssignment.add(experienceGradeAssignment);
        experienceGradeAssignmentField = createStyledTextField();
        experienceGradeAssignmentField.setBounds(200, 150, 180, 27);
        panelGradeAssignment.add(experienceGradeAssignmentField);

        // Graded Score
        JLabel gradedScoregradeAssignmnet = new JLabel("Graded Score:");
        gradedScoregradeAssignmnet.setBounds(30, 190, 200, 35);
        gradedScoregradeAssignmnet.setFont(new Font("Poppins", Font.BOLD, 17));
        gradedScoregradeAssignmnet.setForeground(Color.gray);
        panelGradeAssignment.add(gradedScoregradeAssignmnet);
        gradedScoregradeAssignmnetField = createStyledTextField();
        gradedScoregradeAssignmnetField.setBounds(200, 190, 180, 27);
        panelGradeAssignment.add(gradedScoregradeAssignmnetField);

        // Button to grade assignment
        gradeAssignmentButton = createStyledButton("Grade Assignment");
        gradeAssignmentButton.setBounds(130, 240, 150, 40);
        panelGradeAssignment.add(gradeAssignmentButton);

        // Action Listener for Grade Assignment Button
        gradeAssignmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                gradeAssignment();
            }
        });

        // Panel for removing Tutor with modern academic style
        JPanel panelRemoveTutor = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create gradient background
                GradientPaint gp = new GradientPaint(0, 0, new Color(28, 28, 35, 250),
                        0, getHeight(), new Color(18, 18, 25, 250));
                g2.setPaint(gp);

                // Draw main panel background with rounded corners
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Add subtle border
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(new Color(70, 70, 80, 100));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);

                // Add highlight at top
                g2.setColor(new Color(255, 255, 255, 30));
                g2.drawLine(25, 2, getWidth() - 25, 2);

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panelRemoveTutor.setBounds(20, 510, 400, 150);
        panelRemoveTutor.setLayout(null);
        panelRemoveTutor.setOpaque(false);
        frame.getContentPane().add(panelRemoveTutor);

        // Heading label for Remove Tutor panel
        JLabel headingRemoveTutor = new JLabel("Remove Tutor");
        headingRemoveTutor.setBounds(120, 10, 400, 40);
        headingRemoveTutor.setFont(new Font("Poppins", Font.BOLD, 25));
        headingRemoveTutor.setForeground(Color.gray);
        panelRemoveTutor.add(headingRemoveTutor);

        // Label and text field for entering Teacher ID to remove Tutor
        JLabel teacherIdRemoveTutor = new JLabel("Teacher ID:");
        teacherIdRemoveTutor.setBounds(30, 60, 200, 35);
        teacherIdRemoveTutor.setFont(new Font("Poppins", Font.BOLD, 17));
        teacherIdRemoveTutor.setForeground(Color.gray);
        panelRemoveTutor.add(teacherIdRemoveTutor);
        teacherIdRemoveTutorField = createStyledTextField();
        teacherIdRemoveTutorField.setBounds(200, 60, 180, 27);
        panelRemoveTutor.add(teacherIdRemoveTutorField);

        // Button to remove Tutor
        removeTutorButton = createStyledButton("Remove Tutor");
        removeTutorButton.setBounds(130, 100, 150, 40);
        panelRemoveTutor.add(removeTutorButton);

        // Action Listener for Remove Tutor Button
        removeTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                removeTutor();
            }
        });

        // Panel for setting salary with modern academic style
        JPanel panelSetSalary = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create gradient background
                GradientPaint gp = new GradientPaint(0, 0, new Color(28, 28, 35, 250),
                        0, getHeight(), new Color(18, 18, 25, 250));
                g2.setPaint(gp);

                // Draw main panel background with rounded corners
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Add subtle border
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(new Color(70, 70, 80, 100));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);

                // Add highlight at top
                g2.setColor(new Color(255, 255, 255, 30));
                g2.drawLine(25, 2, getWidth() - 25, 2);

                g2.dispose();
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        panelSetSalary.setBounds(840, 50, 400, 290);
        panelSetSalary.setLayout(null);
        panelSetSalary.setOpaque(false);
        frame.getContentPane().add(panelSetSalary);

        // Heading label for Set Salary panel
        JLabel headingSetSalary = new JLabel("Set Salary");
        headingSetSalary.setBounds(140, 10, 400, 40);
        headingSetSalary.setFont(new Font("Poppins", Font.BOLD, 25));
        headingSetSalary.setForeground(Color.gray);
        panelSetSalary.add(headingSetSalary);

        // Label and text field for entering Teacher ID to set salary
        JLabel teacherIdSetSalary = new JLabel("Teacher ID:");
        teacherIdSetSalary.setBounds(30, 70, 200, 35);
        teacherIdSetSalary.setFont(new Font("Poppins", Font.BOLD, 17));
        teacherIdSetSalary.setForeground(Color.gray);
        panelSetSalary.add(teacherIdSetSalary);
        teacherIdSetSalaryField = createStyledTextField();
        teacherIdSetSalaryField.setBounds(200, 70, 180, 27);
        panelSetSalary.add(teacherIdSetSalaryField);

        // Label and text field for entering salary amount
        JLabel salarySetSalary = new JLabel("Salary:");
        salarySetSalary.setBounds(30, 110, 200, 35);
        salarySetSalary.setFont(new Font("Poppins", Font.BOLD, 17));
        salarySetSalary.setForeground(Color.gray);
        panelSetSalary.add(salarySetSalary);
        salarySetSalaryfield = createStyledTextField();
        salarySetSalaryfield.setBounds(200, 110, 180, 27);
        panelSetSalary.add(salarySetSalaryfield);

        // Label and text field for entering performance index
        JLabel performingIndexSetSalary = new JLabel("Performance Index:");
        performingIndexSetSalary.setBounds(30, 150, 200, 35);
        performingIndexSetSalary.setFont(new Font("Poppins", Font.BOLD, 17));
        performingIndexSetSalary.setForeground(Color.gray);
        panelSetSalary.add(performingIndexSetSalary);
        performingIndexSetSalaryField = createStyledTextField();
        performingIndexSetSalaryField.setBounds(200, 150, 180, 27);
        panelSetSalary.add(performingIndexSetSalaryField);

        // Button to set salary
        SetSalaryButton = createStyledButton("Set Salary");
        SetSalaryButton.setBounds(130, 210, 150, 40);
        panelSetSalary.add(SetSalaryButton);

        // Action Listener for Set Salary Button
        SetSalaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setSalary();
            }
        });

        // Modern clear all fields button
        clearButton = createStyledButton("Clear All Fields");
        clearButton.setBounds(430, 598, 400, 60);
        clearButton.setFont(new Font("Poppins", Font.BOLD, 16));
        frame.getContentPane().add(clearButton);

        // Action Listener for Clear All Fields Button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                resetFields();
            }
        });

        // Mouse listener for dragging the frame
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                frame.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
            }
        });

        frame.setVisible(true);
    }

              /**
 * Method to add a new Lecturer to the system.
 * Retrieves input data from the text fields, validates them, and adds a new Lecturer to the teachersList.
 * Displays error messages if any input is invalid or if the Teacher ID already exists.
 */

private void addLecturer() {
    try {
        // Retrieving input data from text fields and parsing to respective data types
        int teacherId = Integer.parseInt(getText(teacherIdLecturerField));
        int workingHours = Integer.parseInt(getText(workingHoursLecturerField));
        int yearsOfExperience = Integer.parseInt(getText(experienceLecturerField));

        // Validating input data
        if (workingHours < 0 || yearsOfExperience < 0 || teacherId < 0) {
            JOptionPane.showMessageDialog(frame, "Teacher ID, Working Hours, Years of Experience cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieving other input data
        String teacherName = getText(teacherNameLecturerField);
        String address = getText(addressLecturerField);
        String workingType = getText(workingTypeLecturerField);
        String employmentStatus = getText(employmentStatusLecturerField);
        String department = getText(departmentLecturerField);

        // Checking for duplicate Teacher ID
        if (!isDuplicate(teacherId)) {
            JOptionPane.showMessageDialog(frame, "Teacher ID " + teacherId + " Already Exists.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creating a new Lecturer object and adding it to the teachersList
        Lecturer lecturer = new Lecturer(teacherId, teacherName, address, workingType, workingHours,
                employmentStatus, department, yearsOfExperience);
        teachersList.add(lecturer);

        // Displaying success message
        JOptionPane.showMessageDialog(frame, "Lecturer Added Successfully.");
    } catch (NumberFormatException e) {
        // Handling invalid input format
        JOptionPane.showMessageDialog(frame, "Invalid Input for the required fields.",
                "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
        // Handling missing required fields
        JOptionPane.showMessageDialog(frame, "Please fill in all the required fields.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}


   /**
 * Method to add a new Tutor to the system.
 * Retrieves input data from the text fields, validates them, and adds a new Tutor to the teachersList.
 * Displays error messages if any input is invalid or if the Teacher ID already exists.
 */
private void addTutor() {
    try {
        // Retrieving input data from text fields and parsing to respective data types
        int teacherId = Integer.parseInt(getText(teacherIdTutorField));
        String teacherName = getText(teacherNameTutorField);
        String address = getText(addressTutorField);
        String workingType = getText(workingTypeTutorField);
        String employmentStatus = getText(employmentStatusTutorField);
        int workingHours = Integer.parseInt(getText(workingHoursTutorField));
        double salary = Double.parseDouble(getText(salaryField));
        int performanceIndex = Integer.parseInt(getText(performingIndexField));
        
        // Validating input data
        if (teacherId < 0 || salary < 0 || performanceIndex < 0 || workingHours < 0) {
            JOptionPane.showMessageDialog(frame, "Teacher ID, Working Hours, Salary, Performance Index cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieving other input data
        String specialization = getText(specializationField);
        String academicQualifications = getText(academicQualificationField);

        // Checking for duplicate Teacher ID
        if (!isDuplicate(teacherId)) {
            JOptionPane.showMessageDialog(frame, "Teacher ID " + teacherId + " Already Exists.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Creating a new Tutor object and adding it to the teachersList
        Tutor tutor = new Tutor(teacherId, teacherName, address, workingType, employmentStatus, workingHours,
                salary, specialization, academicQualifications, performanceIndex);
        teachersList.add(tutor);

        // Displaying success message
        JOptionPane.showMessageDialog(frame, "Tutor Added Successfully.");
    } catch (NumberFormatException e) {
        // Handling invalid input format
        JOptionPane.showMessageDialog(frame, "Invalid Input for the required fields.",
                "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
        // Handling missing required fields
        JOptionPane.showMessageDialog(frame, "Please fill in all the required fields.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}


  /**
 * Method to grade an assignment for a lecturer.
 * Retrieves input data from the text fields, validates them, and grades the assignment for the lecturer.
 * Displays error messages if any input is invalid or if the lecturer is not found, or if the teacher is not a lecturer.
 */
private void gradeAssignment() {
    try {
        // Retrieving teacher ID and validating
        int teacherId = Integer.parseInt(teacherIdGradeAssignmentfield.getText());
        if (teacherId < 0) {
            JOptionPane.showMessageDialog(frame, "Teacher ID cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieving department and validating
        String department = departmentGradeAssignmentField.getText();

        // Retrieving years of experience and validating
        int experience = Integer.parseInt(experienceGradeAssignmentField.getText());
        if (experience < 0) {
            JOptionPane.showMessageDialog(frame, "Years of experience cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieving graded score and validating
        int gradedScore = Integer.parseInt(gradedScoregradeAssignmnetField.getText());
        if (gradedScore < 0) {
            JOptionPane.showMessageDialog(frame, "Graded score cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the entered teacher ID is available
        Teacher teacher = findTeacherId(teacherId);
        if (teacher == null) {
            JOptionPane.showMessageDialog(frame, "Teacher ID " + teacherId + " is Not Found", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the teacher is a Lecturer
        if (!(teacher instanceof Lecturer)) {
            JOptionPane.showMessageDialog(frame, "Only Lecturers can be graded for assignments.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Lecturer lecturer = (Lecturer) teacher;

        // Check if years of experience is at least 5 years and department is valid
        if (experience < 5 || !isValidDepartment(department)) {
            JOptionPane.showMessageDialog(frame,
                    "Years of experience must be at least 5 years or department is not valid.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm grading
        int option = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to grade the assignment?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Grade the assignment
            lecturer.gradeAssignment(gradedScore, department, experience);

            JOptionPane.showMessageDialog(frame,
                    "Assignment graded successfully.\nGradeScore is Displayed in Terminal.");
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Assignment grading canceled.");
        }
    } catch (NumberFormatException e) {
        // Handling invalid input format
        JOptionPane.showMessageDialog(frame, "Please enter a valid number in the required fields.", "ERROR MESSAGE",
                JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException a) {
        // Handling missing required fields
        JOptionPane.showMessageDialog(frame, "Please fill in all the required fields.", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}

   /**
 * Method to set salary for a tutor based on performance index.
 * Retrieves input data from the text fields, validates them, and sets the salary accordingly.
 * Displays error messages if any input is invalid, if the teacher is not found or not a tutor,
 * or if the performance index or working hours do not meet the specified criteria.
 */
private void setSalary() {
    try {
        // Retrieve teacher ID and validate
        int teacherId = Integer.parseInt(getText(teacherIdSetSalaryField));
        if (teacherId < 0) {
            JOptionPane.showMessageDialog(frame, "Teacher ID cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve salary and validate
        double salary = Double.parseDouble(getText(salarySetSalaryfield));
        if (salary < 0) {
            JOptionPane.showMessageDialog(frame, "Salary cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve performance index and validate
        int performanceIndex = Integer.parseInt(getText(performingIndexSetSalaryField));
        if (performanceIndex < 0) {
            JOptionPane.showMessageDialog(frame, "Performance index cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Find the teacher by ID
        Teacher teacher = findTeacherId(teacherId);
        if (teacher == null) {
            JOptionPane.showMessageDialog(frame, "Teacher ID " + teacherId + " is Not Found.");
            return;
        }

        // Check if the teacher is a Tutor
        if (!(teacher instanceof Tutor)) {
            JOptionPane.showMessageDialog(frame, "Teacher ID " + teacherId + " is not a tutor.");
            return;
        }

        // Convert the teacher object to a Tutor object
        Tutor tutor = (Tutor) teacher;

        // Validate performance index
        if (performanceIndex <= 5) {
            JOptionPane.showMessageDialog(frame, "Performance index Should be greater than 5.");
            return;
        }
        if (performanceIndex >= 10) {
            JOptionPane.showMessageDialog(frame, "Performance index Should not be greater than 10.");
            return;
        }

        // Validate working hours
        if (tutor.getWorkingHours() <= 20) {
            JOptionPane.showMessageDialog(frame, "Working hours should be greater than 20.");
            return;
        }

        // Set the salary for the tutor based on performance index
        tutor.setSalary(salary, performanceIndex);
        JOptionPane.showMessageDialog(frame,
                "Teacher Id: " + tutor.getTeacherId() + "\n" +
                "Salary: " + tutor.getSalary() + "\n");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Invalid Input for the required fields.",
                "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException a) {
        JOptionPane.showMessageDialog(frame, "Please fill in all the required fields.",
                "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    }
}


   /**
 * Removes a tutor from the system based on the provided teacher ID.
 * Validates the input and ensures that the teacher is not a certified tutor before removal.
 * Displays appropriate error messages if the input is invalid or if the tutor cannot be removed.
 */
private void removeTutor() {
    try {
        // Retrieve teacher ID and validate
        int teacherId = Integer.parseInt(getText(teacherIdRemoveTutorField));
        if (teacherId < 0) {
            JOptionPane.showMessageDialog(frame, "Teacher ID cannot be negative.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Find the teacher by ID
        Teacher teacher = findTeacherId(teacherId);
        if (teacher == null) {
            JOptionPane.showMessageDialog(frame, "Teacher ID " + teacherId + " was Not Found.");
            return;
        }

        // Check if the teacher is a Tutor
        if (!(teacher instanceof Tutor)) {
            JOptionPane.showMessageDialog(frame, "The teacher is not a tutor.");
            return;
        }

        Tutor tutor = (Tutor) teacher;

        // Check if the tutor is certified
        if (tutor.getIsCertified()) {
            JOptionPane.showMessageDialog(frame, "Tutor can't be Removed. Because they are Certified.");
        } else {
            // Remove the tutor from the list
            teachersList.remove(teacher);
            JOptionPane.showMessageDialog(frame, "Tutor removed successfully");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Invalid Input for Teacher ID.",
                "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException a) {
        JOptionPane.showMessageDialog(frame, "Teacher ID field is empty.",
                "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    }
}

/**
 * Displays details of all the lecturers in the system.
 * Iterates through the teachers list and displays the details of each lecturer found.
 * Displays appropriate messages if no lecturers are found or if details are displayed in the terminal.
 */
private void displayLecturer() {
    boolean foundLecturer = false;
    for (Teacher object : teachersList) {
        if (object instanceof Lecturer) {
            Lecturer lecturer = (Lecturer) object;
            lecturer.display();
            foundLecturer = true;
        }
    }
    if (!foundLecturer) {
        JOptionPane.showMessageDialog(frame, "No lecturers found.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(frame, "Details are displayed in terminal.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

/**
 * Displays details of all the tutors in the system.
 * Iterates through the teachers list and displays the details of each tutor found.
 * Displays appropriate messages if no tutors are found or if details are displayed in the terminal.
 */
private void displayTutor() {
    boolean foundTutor = false;
    for (Teacher object : teachersList) {
        if (object instanceof Tutor) {
            Tutor tutor = (Tutor) object;
            tutor.display();
            foundTutor = true;
        }
    }
    if (!foundTutor) {
        JOptionPane.showMessageDialog(frame, "No tutors found.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(frame, "Details are displayed in terminal.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

/**
 * Checks if the provided teacher ID already exists in the system.
 * Iterates through the teachers list and compares each teacher's ID with the provided ID.
 * Returns true if no duplicate ID is found, indicating it's not a duplicate.
 *
 * @teacherId The ID of the teacher to check for duplication.
 * @return True if the teacher ID is not a duplicate, false otherwise.
 */
private boolean isDuplicate(int teacherId) {
    for (Teacher teacher : teachersList) {
        if (teacher.getTeacherId() == teacherId) {
            return false;
        }
    }
    return true;
}

/**
 * Finds a teacher in the system based on the provided teacher ID.
 * Iterates through the teachers list and compares each teacher's ID with the provided ID.
 * Returns the teacher object if found, or null if not found.
 *
 * teacherId The ID of the teacher to find.
 * @return The Teacher object with the specified ID, or null if not found.
 */
private Teacher findTeacherId(int teacherId) {
    for (Teacher teacher : teachersList) {
        if (teacher.getTeacherId() == teacherId) {
            return teacher;
        }
    }
    return null;
}

/**
 * Checks if the provided teacher is certified.
 * If the teacher is an instance of Tutor, it retrieves the certification status.
 *
 * teacher The teacher to check for certification.
 * @return True if the teacher is certified, false otherwise.
 */
public boolean isCertified(Teacher teacher) {
    if (teacher instanceof Tutor) {
        Tutor tutor = (Tutor) teacher;
        return tutor.getIsCertified();
    }
    return false;
}

/**
 * Retrieves the text from a JTextField and trims any leading or trailing whitespace.
 * Throws an IllegalArgumentException if the text field is empty.
 *
 * textField The JTextField to retrieve text from.
 * @returnT he trimmed text from the JTextField.
 * @throws IllegalArgumentException If the text field is empty.
 */
private String getText(JTextField textField) {
    String text = textField.getText().trim();
    if (text.isEmpty()) {
        throw new IllegalArgumentException();
    }
    return text;
}

/**
 * Checks if the provided department is valid by comparing it with the departments of existing lecturers.
 */
private boolean isValidDepartment(String department) {
    for (Teacher teacher : teachersList) {
        if (teacher instanceof Lecturer) {
            Lecturer lecturer = (Lecturer) teacher;
            if (department.equals(lecturer.getDepartment())) {
                return true;
            }
        }
    }
    return false;
}

/**
 * Resets all input fields in the GUI to their initial state.
 * Clears the text from all text fields.
 */
private void resetFields() {
    teacherIdLecturerField.setText("");
    teacherNameLecturerField.setText("");
    addressLecturerField.setText("");
    workingTypeLecturerField.setText("");
    employmentStatusLecturerField.setText("");
    workingHoursLecturerField.setText("");
    departmentLecturerField.setText("");
    experienceLecturerField.setText("");
    teacherIdTutorField.setText("");
    teacherNameTutorField.setText("");
    addressTutorField.setText("");
    workingTypeTutorField.setText("");
    employmentStatusTutorField.setText("");
    workingHoursTutorField.setText("");
    salaryField.setText("");
    specializationField.setText("");
    academicQualificationField.setText("");
    performingIndexField.setText("");
    teacherIdGradeAssignmentfield.setText("");
    gradedScoregradeAssignmnetField.setText("");
    departmentGradeAssignmentField.setText("");
    experienceGradeAssignmentField.setText("");
    teacherIdRemoveTutorField.setText("");
    teacherIdSetSalaryField.setText("");
    salarySetSalaryfield.setText("");
    performingIndexSetSalaryField.setText("");
}

    public static void main(String[] args) {
        new TeacherGUI();
    }
}
