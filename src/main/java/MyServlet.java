import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Item;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(itemService.findById(Long.parseLong(req.getParameter("itemID"))).toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Item item = null;
        try (InputStream inputStream = req.getInputStream()) {
            item = objectMapper.readValue(inputStream, Item.class);
        } catch (Exception e) {
            System.err.println("DoPost failed.");
            System.err.println(e.getMessage());
            return;
        }
        itemService.save(item);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Item item = null;
        try (InputStream inputStream = req.getInputStream()) {
            item = objectMapper.readValue(inputStream, Item.class);
        } catch (Exception e) {
            System.err.println("DoPut failed.");
            System.err.println(e.getMessage());
            return;
        }
        itemService.update(item);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        itemService.delete(Long.parseLong(req.getParameter("itemId")));
    }
}
