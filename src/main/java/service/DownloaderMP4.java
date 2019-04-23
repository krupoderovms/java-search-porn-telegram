//package service;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
///**
// * Отвечает за скачивание роликов MP4 по ссылке на диск
// */
//public class DownloaderMP4 {
//
//    OutputStream outputStream = null;
//    InputStream inputStream = null;
//    HttpURLConnection connection;
//
//
//    // СОХРАНЯЕТ mp4 ролики по переданной ссылке в указанную директорию
//    public void saveMP4(String urlOfMP4, String headerVideo) {
//            URL url;
//            byte[] buf;
//            int byteRead, byteWritten = 0;
//            try {
//                url = new URL(getFinalLocation(urlOfMP4));
//                // Сохраняем в указанную директорию с заданным заголовком. Можно сделать перезаписываемость скачиваемых файлов, тогда имена файлов будут не уникальны.
//                outputStream = new BufferedOutputStream(new FileOutputStream(Properties.LOCATION + headerVideo + ".mp4"));
//
//                connection = (HttpURLConnection) url.openConnection();
//                inputStream = connection.getInputStream();
//                buf = new byte[1024];
//                while ((byteRead = inputStream.read(buf)) != -1) {
//                    outputStream.write(buf, 0, byteRead);
//                    byteWritten += byteRead;
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//    }
//
//
//
//
//    public static String getFinalLocation(String address) throws IOException {
//        URL url = new URL(address);
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        int status = conn.getResponseCode();
//        if (status != HttpURLConnection.HTTP_OK)
//        {
//            if (status == HttpURLConnection.HTTP_MOVED_TEMP
//                    || status == HttpURLConnection.HTTP_MOVED_PERM
//                    || status == HttpURLConnection.HTTP_SEE_OTHER)
//            {
//                String newLocation = conn.getHeaderField("Location");
//                return getFinalLocation(newLocation);
//            }
//        }
//        return address;
//    }
//
//
//
//}
