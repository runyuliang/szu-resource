import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ResumableFileDownloader {
    public static void downloadFile(String fileUrl, String saveDirectory) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            File file = new File(saveDirectory);
            long existingFileSize = 0;

            if (file.exists()) {
                existingFileSize = file.length();
                connection.setRequestProperty("Range", "bytes=" + existingFileSize + "-");
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_PARTIAL) {
                String contentDisposition = connection.getHeaderField("Content-Disposition");
                String fileName = "";

                if (contentDisposition != null && contentDisposition.indexOf("filename=") != -1) {
                    fileName = contentDisposition.substring(contentDisposition.indexOf("filename=") + 9)
                            .replace("\"", "");
                } else {
                    fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                }

                String savePath = saveDirectory + File.separator + fileName;
                file = new File(savePath);

                RandomAccessFile outputFile = new RandomAccessFile(file, "rw");
                outputFile.seek(existingFileSize);

                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputFile.write(buffer, 0, bytesRead);
                }

                outputFile.close();
                inputStream.close();

                System.out.println("文件下载完成：" + savePath);
            } else {
                System.out.println("文件下载失败：" + fileUrl + " 响应码：" + responseCode);
            }
        } catch (IOException e) {
            System.out.println("文件下载失败：" + fileUrl + " 错误信息：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要下载的文件 URL：");
        String fileUrl = scanner.nextLine();

        String saveDirectory = "D:/save/";//需要先创建目录

        downloadFile(fileUrl, saveDirectory);
    }
}
