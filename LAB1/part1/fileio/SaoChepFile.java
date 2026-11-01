package part1.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaoChepFile {

    public boolean copyFile(String source, String dest) throws IOException {
        File sourceFile = new File(source);
        File destFile = new File(dest);

        if (sourceFile.exists()) {
            // Đảm bảo thư mục cha của file đích tồn tại
            if (destFile.getParentFile() != null) {
                destFile.getParentFile().mkdirs();
            }

            try (FileInputStream fis = new FileInputStream(sourceFile);
                 FileOutputStream fos = new FileOutputStream(destFile)) {
                byte[] arr = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(arr)) != -1) {
                    fos.write(arr, 0, bytesRead);
                }
                fos.flush();
            }
            System.out.println("Copy file thành công từ [" + source + "] sang [" + dest + "]");
            return true;
        } else {
            System.out.println("File nguồn không tồn tại: " + source);
            return false;
        }
    }

    public static void main(String[] args) {
        SaoChepFile saoChep = new SaoChepFile();
        try {
            // Tạo file nguồn tạm để test copy
            File src = new File("copy_source_test.txt");
            if (!src.exists()) {
                src.createNewFile();
            }
            saoChep.copyFile("copy_source_test.txt", "copy_dest_test.txt");

            // Dọn dẹp file test
            src.delete();
            new File("copy_dest_test.txt").delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
