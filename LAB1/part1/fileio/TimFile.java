package part1.fileio;

import java.io.File;

public class TimFile {

    public void findFile(String source, String key) {
        File file = new File(source);
        if (file.exists()) {
            if (file.isFile()) {
                if (file.getName().endsWith(key) || file.getName().contains(key)) {
                    System.out.println("Tìm thấy: " + file.getAbsolutePath());
                }
            } else if (file.isDirectory()) {
                File[] listFile = file.listFiles();
                if (listFile != null) {
                    for (File f : listFile) {
                        findFile(f.getAbsolutePath(), key);
                    }
                }
            }
        } else {
            System.out.println("Đường dẫn '" + source + "' không tồn tại");
        }
    }

    public static void main(String[] args) {
        TimFile timFile = new TimFile();
        System.out.println("=== TÌM KIẾM FILE ===");
        // Tìm kiếm các file .java trong thư mục hiện tại
        timFile.findFile(".", ".java");
    }
}
