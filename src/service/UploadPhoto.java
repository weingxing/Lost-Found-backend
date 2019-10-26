package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "UploadPhoto")
/**
 * 图片上传接口，用户在提交信息时添加图片
 * @author Oxygen
 */
public class UploadPhoto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = this.getServletContext().getRealPath("/img");
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }

        try {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            fileUpload.setHeaderEncoding("UTF-8");
            if (!fileUpload.isMultipartContent(request)) {
                return;
            }
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"), "UTF-8");
                    System.out.println(name + "  " + value);
                    System.out.println(name + "  " + value1);
                } else {
                    String fileName = item.getName();
                    System.out.println(fileName);
                    if (fileName == null || fileName.trim().equals("")) {
                        continue;
                    }
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
                    InputStream is = item.getInputStream();
                    FileOutputStream fos = new FileOutputStream(savePath + File.separator + fileName);
                    byte buffer[] = new byte[1024];
                    int length = 0;
                    while ((length = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }

                    is.close();
                    fos.close();
                    item.delete();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
