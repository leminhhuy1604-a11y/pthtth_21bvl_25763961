package part2.bai7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Giao diện đồ họa đơn giản cho Bài 7
 */
public class GiaoDien extends JFrame {

    private JComboBox<String> cbShape;
    private JPanel panelInputs;
    private JTextField txtParam1, txtParam2, txtParam3, txtParam4;
    private JLabel lblParam1, lblParam2, lblParam3, lblParam4;
    private JTextArea txtOutput;
    private JButton btnCalculate;

    public GiaoDien() {
        setTitle("Tính Toán Đa Giác - Bài 7 Lab 1");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel chọn hình
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        topPanel.add(new JLabel("Chọn loại đa giác: "), BorderLayout.WEST);

        String[] shapes = {
            "1. Tam giác thường (Triangle)",
            "2. Tam giác cân (Isosceles Triangle)",
            "3. Tam giác đều (Equilateral Triangle)",
            "4. Tứ giác (Quadrilateral)",
            "5. Hình chữ nhật (Rectangle)",
            "6. Hình vuông (Square)",
            "7. Ngũ giác đều (Regular Pentagon)",
            "8. Lục giác đều (Regular Hexagon)",
            "9. Bát giác đều (Regular Octagon)"
        };
        cbShape = new JComboBox<>(shapes);
        topPanel.add(cbShape, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Panel nhập thông số
        panelInputs = new JPanel(new GridLayout(4, 2, 8, 8));
        panelInputs.setBorder(new TitledBorder("Nhập thông số kích thước"));

        lblParam1 = new JLabel("Cạnh a:");
        txtParam1 = new JTextField("3");
        lblParam2 = new JLabel("Cạnh b:");
        txtParam2 = new JTextField("4");
        lblParam3 = new JLabel("Cạnh c:");
        txtParam3 = new JTextField("5");
        lblParam4 = new JLabel("Cạnh d:");
        txtParam4 = new JTextField("");

        panelInputs.add(lblParam1);
        panelInputs.add(txtParam1);
        panelInputs.add(lblParam2);
        panelInputs.add(txtParam2);
        panelInputs.add(lblParam3);
        panelInputs.add(txtParam3);
        panelInputs.add(lblParam4);
        panelInputs.add(txtParam4);

        btnCalculate = new JButton("Tính Chu Vi & Diện Tích");
        btnCalculate.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalculate.setBackground(new Color(41, 128, 185));
        btnCalculate.setForeground(Color.WHITE);

        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));
        centerPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        centerPanel.add(panelInputs, BorderLayout.CENTER);
        centerPanel.add(btnCalculate, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        // Panel kết quả hiển thị
        txtOutput = new JTextArea(8, 40);
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(new TitledBorder("Kết quả tính toán"));
        add(scrollPane, BorderLayout.SOUTH);

        // Events
        cbShape.addActionListener(e -> updateInputFields());
        btnCalculate.addActionListener(e -> calculatePolygon());

        updateInputFields();
    }

    private void updateInputFields() {
        int index = cbShape.getSelectedIndex();
        // Reset visibility
        lblParam1.setVisible(true); txtParam1.setVisible(true);
        lblParam2.setVisible(false); txtParam2.setVisible(false);
        lblParam3.setVisible(false); txtParam3.setVisible(false);
        lblParam4.setVisible(false); txtParam4.setVisible(false);

        switch (index) {
            case 0: // Triangle
                lblParam1.setText("Cạnh a:");
                lblParam2.setText("Cạnh b:");
                lblParam3.setText("Cạnh c:");
                lblParam2.setVisible(true); txtParam2.setVisible(true);
                lblParam3.setVisible(true); txtParam3.setVisible(true);
                break;
            case 1: // Isosceles
                lblParam1.setText("Cạnh bên (equalSide):");
                lblParam2.setText("Cạnh đáy (baseSide):");
                lblParam2.setVisible(true); txtParam2.setVisible(true);
                break;
            case 2: // Equilateral
                lblParam1.setText("Độ dài cạnh (side):");
                break;
            case 3: // Quadrilateral
                lblParam1.setText("Cạnh a:");
                lblParam2.setText("Cạnh b:");
                lblParam3.setText("Cạnh c:");
                lblParam4.setText("Cạnh d:");
                lblParam2.setVisible(true); txtParam2.setVisible(true);
                lblParam3.setVisible(true); txtParam3.setVisible(true);
                lblParam4.setVisible(true); txtParam4.setVisible(true);
                break;
            case 4: // Rectangle
                lblParam1.setText("Chiều dài (Length):");
                lblParam2.setText("Chiều rộng (Width):");
                lblParam2.setVisible(true); txtParam2.setVisible(true);
                break;
            case 5: // Square
                lblParam1.setText("Cạnh hình vuông (side):");
                break;
            case 6: // Pentagon
                lblParam1.setText("Cạnh ngũ giác đều (side):");
                break;
            case 7: // Hexagon
                lblParam1.setText("Cạnh lục giác đều (side):");
                break;
            case 8: // Octagon
                lblParam1.setText("Cạnh bát giác đều (side):");
                break;
        }
        panelInputs.revalidate();
        panelInputs.repaint();
    }

    private void calculatePolygon() {
        try {
            int index = cbShape.getSelectedIndex();
            Polygon poly = null;

            switch (index) {
                case 0: {
                    double a = Double.parseDouble(txtParam1.getText().trim());
                    double b = Double.parseDouble(txtParam2.getText().trim());
                    double c = Double.parseDouble(txtParam3.getText().trim());
                    poly = new Triangle(a, b, c);
                    break;
                }
                case 1: {
                    double eq = Double.parseDouble(txtParam1.getText().trim());
                    double bs = Double.parseDouble(txtParam2.getText().trim());
                    poly = new IsoscelesTriangle(eq, bs);
                    break;
                }
                case 2: {
                    double s = Double.parseDouble(txtParam1.getText().trim());
                    poly = new EquilateralTriangle(s);
                    break;
                }
                case 3: {
                    double a = Double.parseDouble(txtParam1.getText().trim());
                    double b = Double.parseDouble(txtParam2.getText().trim());
                    double c = Double.parseDouble(txtParam3.getText().trim());
                    double d = Double.parseDouble(txtParam4.getText().trim());
                    poly = new Quadrilateral(a, b, c, d);
                    break;
                }
                case 4: {
                    double l = Double.parseDouble(txtParam1.getText().trim());
                    double w = Double.parseDouble(txtParam2.getText().trim());
                    poly = new Rectangle(l, w);
                    break;
                }
                case 5: {
                    double s = Double.parseDouble(txtParam1.getText().trim());
                    poly = new Square(s);
                    break;
                }
                case 6: {
                    double s = Double.parseDouble(txtParam1.getText().trim());
                    poly = new Pentagon(s);
                    break;
                }
                case 7: {
                    double s = Double.parseDouble(txtParam1.getText().trim());
                    poly = new Hexagon(s);
                    break;
                }
                case 8: {
                    double s = Double.parseDouble(txtParam1.getText().trim());
                    poly = new Octagon(s);
                    break;
                }
            }

            if (poly != null) {
                String res = String.format("Đã tạo: %s%n - Chu vi:    %.4f%n - Diện tích: %.4f%n%s%n%n",
                        poly.getName(), poly.perimeter(), poly.area(), poly.toString());
                txtOutput.append(res);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị số thực hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi hình học", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GiaoDien().setVisible(true);
        });
    }
}
