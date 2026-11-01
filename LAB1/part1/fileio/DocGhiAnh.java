package part1.fileio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DocGhiAnh {

    /**
     * Tạo và ghi một file ảnh mẫu định dạng PNG
     */
    public static void writeSampleImage(String outputPath) throws IOException {
        int width = 400;
        int height = 200;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = image.createGraphics();
        // Tô nền
        g2d.setColor(new Color(24, 76, 120));
        g2d.fillRect(0, 0, width, height);

        // Vẽ chữ lên ảnh
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 22));
        g2d.drawString("Lab 1: Java Image IO", 80, 100);
        g2d.dispose();

        File outputFile = new File(outputPath);
        if (outputFile.getParentFile() != null) {
            outputFile.getParentFile().mkdirs();
        }
        ImageIO.write(image, "png", outputFile);
        System.out.println("Ghi file ảnh thành công tại: " + outputPath);
    }

    /**
     * Đọc thông tin kích thước của file ảnh
     */
    public static void readImageInfo(String inputPath) throws IOException {
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            System.out.println("File ảnh không tồn tại: " + inputPath);
            return;
        }

        BufferedImage img = ImageIO.read(inputFile);
        if (img != null) {
            System.out.println("Đọc file ảnh thành công: " + inputPath);
            System.out.println(" - Chiều rộng (Width): " + img.getWidth() + " px");
            System.out.println(" - Chiều cao (Height): " + img.getHeight() + " px");
            System.out.println(" - Kiểu ảnh (Type): " + img.getType());
        } else {
            System.out.println("Không thể giải mã định dạng file ảnh: " + inputPath);
        }
    }

    public static void main(String[] args) {
        String testImagePath = "sample_image.png";
        try {
            System.out.println("=== TEST ĐỌC VÀ GHI FILE ẢNH ===");
            writeSampleImage(testImagePath);
            readImageInfo(testImagePath);

            // Dọn dẹp file mẫu sau khi test
            new File(testImagePath).delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
