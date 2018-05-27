import com.xmlcrawler.service.CrawlerController;
import io.javalin.Javalin;

import static io.javalin.ApiBuilder.post;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create()
                .port(7000)
                .start();

        app.routes(() -> {
            post("/findfirst", CrawlerController.FindFirst);
            post("/findlast", CrawlerController.FindLast);
            post("/findall", CrawlerController.FindAll);
        });
    }
}