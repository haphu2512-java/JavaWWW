package fit.se.haxuanphu_tuan2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/uploadmulti")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 5 MB
        maxRequestSize = 1024 * 1024 * 50
)
public class UpFileServlet extends HttpServlet {
    private  static final String UPLOAD_DIR = "uploads";

    public UpFileServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;

        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        for(Part part : req.getParts()){
            String fileName = getFileName(part);
            if(fileName != null && !fileName.isEmpty()){
                part.write(uploadPath + File.separator + fileName);
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h3>Upload thành công!"+uploadPath +"</h3>");
    }


    private String getFileName(Part part){
        String contentDisp = part.getHeader("content-dispo  sition");
        String[] items = contentDisp.split(";");
        for(String s : items){
            if(s.trim().startsWith("filename")){
                return s.substring(s.indexOf("=")+2, s.length()-1);
            }
        }
        return null;
    }

}
