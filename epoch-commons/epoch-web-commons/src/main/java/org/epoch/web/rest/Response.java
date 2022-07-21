package org.epoch.web.rest;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.epoch.core.exception.BaseException;
import org.epoch.core.util.BaseConverter;

/**
 * TODO: move file operations out.
 * response工具类
 *
 * @author Marshal
 * @date 2019/8/27
 */
@SuppressWarnings("rawtypes,unchecked")
@Slf4j
public class Response {
    private Response() {
    }

    public static ResponseEntity<Void> success() {
        return success(null);
    }

    public static ResponseEntity<Void> success(String message) {
        return new ResponseEntity(message);
    }

    public static <T> ResponseEntity<T> success(Object data) {
        return new ResponseEntity(data);
    }

    public static ResponseEntity<Void> error() {
        return error(null);
    }

    public static ResponseEntity<Void> error(String message) {
        return new ResponseEntity(false, message);
    }

    public static ResponseEntity<Void> error(String code, String message) {
        return new ResponseEntity(code, false, message);
    }

    public static void error(HttpServletResponse response, String message) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(BaseConverter.toJSONString(error(message)));
        } catch (IOException e) {
            log.error("io exception occur while resp...");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * excel导出
     *
     * @param workbook
     * @param fileName
     * @param response
     * @throws IOException
     */
    public static void excel(Workbook workbook, String fileName, HttpServletResponse response) throws IOException {
        setResponseHeaderWhileDownloading(response, fileName);

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new IOException("导出失败!");
        } finally {
            outputStream.close();
            workbook.close();
        }
    }

    /**
     * 文件下载
     *
     * @param bytes
     * @param fileName
     * @param response
     * @throws IOException
     */
    public static void file(byte[] bytes, String fileName, HttpServletResponse response) throws IOException {
        setResponseHeaderWhileDownloading(response, fileName);

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            throw new IOException("文件下载失败!");
        } finally {
            outputStream.close();
        }
    }

    /**
     * 压缩包下载
     *
     * @param files
     * @param fileName
     * @param response
     * @throws IOException
     */
    public static void zip(Map<String, byte[]> files, String fileName, HttpServletResponse response) throws IOException {
        setResponseHeaderWhileDownloading(response, fileName);

        ServletOutputStream servletOutputStream = response.getOutputStream();
        ZipOutputStream zos = new ZipOutputStream(servletOutputStream);
        BufferedOutputStream bos = new BufferedOutputStream(zos);

        for (Map.Entry<String, byte[]> entry : files.entrySet()) {
            String name = entry.getKey();
            byte[] content = entry.getValue();

            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(content));
            zos.putNextEntry(new ZipEntry(name));

            int len = 0;
            byte[] buf = new byte[content.length];
            while ((len = bis.read(buf, 0, buf.length)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
            zos.closeEntry();
            bis.close();
        }
        bos.flush();
        bos.close();
        servletOutputStream.close();
    }

    private static void setResponseHeaderWhileDownloading(HttpServletResponse response, String fileName) {
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new BaseException("unsupport encoding type...");
        }
    }
}
