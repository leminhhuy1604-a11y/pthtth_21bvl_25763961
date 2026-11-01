package part1.fileio;

import java.io.File;

public class DeleteFileDemo {
    public void deleteFile(String source) {
        File file = new File(source);
        if (file.exists()) {
            System.out.println("File tồn tại: " + file.getAbsolutePath());
            if (file.delete()) {
                System.out.println("Xóa file thành công!");
            } else {
                System.out.println("Không thể xóa file.");
            }
        } else {
            System.out.println("File không tồn tại: " + source);
        }
    }

    public static void main(String[] args) {
        DeleteFileDemo demo = new DeleteFileDemo();
        // Thử nghiệm với đường dẫn file mẫu
        demo.deleteFile("demo.txt");
    }
}
