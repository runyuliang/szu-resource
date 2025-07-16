import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class Downloader {
    public static void downloadFile(String fileUrl, String savePath) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(savePath);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();
                System.out.println("文件下载完成：" + savePath);
            } else {
                System.out.println("文件下载失败：" + fileUrl + " 响应码：" + responseCode);
            }
        } catch (IOException e) {
            System.out.println("文件下载失败：" + fileUrl + " 错误信息：" + e.getMessage());
        }
    }

    public static void downloadImage(String imageUrl, String saveDirectory, AtomicInteger counter) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String fileExtension = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
                String fileName = String.format("%02d.%s", counter.incrementAndGet(), fileExtension);
                String savePath = saveDirectory + fileName;
                FileOutputStream outputStream = new FileOutputStream(savePath);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();
                System.out.println("图片下载完成：" + savePath);
            } else {
                System.out.println("图片下载失败：" + imageUrl + " 响应码：" + responseCode);
            }
        } catch (IOException e) {
            System.out.println("图片下载失败：" + imageUrl + " 错误信息：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 从用户输入获取URL
        String url = "https://szu.edu.cn";
        String saveDirectory = "D:/save/";

        try {
            // 下载HTML文件
            String htmlFileName = "index.html";
            String htmlSavePath = saveDirectory + htmlFileName;
            downloadFile(url, htmlSavePath);

            // 解析HTML中的图片并下载
            Document doc = Jsoup.parse(new URL(url), 3000);
            Elements images = doc.select("img");
            AtomicInteger counter = new AtomicInteger();
            for (Element image : images) {
                String imageUrl = image.absUrl("src");
                downloadImage(imageUrl, saveDirectory, counter);
            }

            // 解析CSS中的背景图片并下载
            Elements cssLinks = doc.select("link[rel=stylesheet]");
            for (Element cssLink : cssLinks) {
                String cssUrl = cssLink.absUrl("href");
                Document cssDoc = Jsoup.connect(cssUrl).get();
                Elements cssImages = cssDoc.select("[style*=background-image]");
                for (Element cssImage : cssImages) {
                    String style = cssImage.attr("style");
                    String imageUrl = style.substring(style.indexOf("url(") + 4, style.indexOf(")")).replaceAll("[\"']", "");
                    downloadImage(imageUrl, saveDirectory, counter);
                }
            }
        } catch (IOException e) {
            System.out.println("URL解析失败：" + e.getMessage());
        }
    }
}
