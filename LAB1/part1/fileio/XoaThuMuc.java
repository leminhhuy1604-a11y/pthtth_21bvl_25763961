package part1.fileio;

import java.io.File;
import java.io.IOException;

public class XoaThuMuc {

    // TH1: Xóa thư mục rỗng
    public boolean deleteEmptyFolder(String source) {
        File folder = new File(source);
        if (folder.exists() && folder.isDirectory()) {
            if (folder.delete()) {
                System.out.println("Thư mục rỗng đã xóa thành công: " + source);
                return true;
            } else {
                System.out.println("Xóa thư mục thất bại (có thể thư mục không rỗng).");
                return false;
            }
        } else {
            System.out.println("Thư mục không tồn tại: " + source);
            return false;
        }
    }

    // TH2: Xóa thư mục chỉ chứa các files (không chứa thư mục con)
    public boolean deleteListFileInFolder(String source) {
        File folder = new File(source);
        if (folder.exists() && folder.isDirectory()) {
            File[] listFile = folder.listFiles();
            if (listFile != null) {
                for (File f : listFile) {
                    if (f.isFile()) {
                        f.delete();
                    }
                }
            }
            folder.delete();
            System.out.println("Delete folder (chứa file) thành công: " + source);
            return true;
        } else {
            System.out.println("Folder không tồn tại hoặc không phải thư mục: " + source);
            return false;
        }
    }

    // TH3: Xóa đệ quy toàn bộ thư mục (chứa cả files và các thư mục con lồng nhau)
    public boolean deleteFolderRecursive(String source) throws IOException {
        File folder = new File(source);
        if (folder.exists()) {
            File[] listFile = folder.listFiles();
            if (listFile != null) {
                for (File f : listFile) {
                    if (f.isFile()) {
                        f.delete();
                    } else if (f.isDirectory()) {
                        deleteFolderRecursive(f.getAbsolutePath());
                    }
                }
            }
            folder.delete();
            System.out.println("Delete folder đệ quy thành công: " + source);
            return true;
        } else {
            System.out.println("Folder không tồn tại: " + source);
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        XoaThuMuc xoaThuMuc = new XoaThuMuc();
        System.out.println("=== TEST XÓA THƯ MỤC ===");
        // Tạo thử thư mục để test
        File testDir = new File("test_delete_dir/sub");
        testDir.mkdirs();
        File testFile = new File("test_delete_dir/sub/sample.txt");
        testFile.createNewFile();

        // Xóa đệ quy
        xoaThuMuc.deleteFolderRecursive("test_delete_dir");
    }
}
