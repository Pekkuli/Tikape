package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.AlueDao;
import tikape.runko.database.AiheDao;
import tikape.runko.database.ViestiDao;
import tikape.runko.domain.Aihe;

public class Main {

    public static void main(String[] args) throws Exception{
		
        if (System.getenv("PORT") != null) {
            port(Integer.valueOf(System.getenv("PORT")));
        }
		
        Database database = new Database("jdbc:sqlite:nahkaluola.db");
        database.init();
        
        AlueDao alueDao = new AlueDao(database);
        AiheDao aiheDao = new AiheDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("alueet", alueDao.findAll());

            return new ModelAndView(map, "alueet");
        }, new ThymeleafTemplateEngine());

        get("/:alueid", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aiheet", aiheDao.findAll(Integer.parseInt(req.params("alueid"))));

            return new ModelAndView(map, "aiheet");
        }, new ThymeleafTemplateEngine());
        
        post("/:alueid", (req, res) -> {
            Aihe aihe = aiheDao.uusiAihe(Integer.parseInt(req.params("alueid")), req.queryParams("aiheOtsikko"), req.queryParams("viestiTeksti"), req.queryParams("viestiNimimerkki"));
            
            HashMap map = new HashMap<>();
            res.redirect("/aihe/" + aihe.getId());
            map.put("viestit", viestiDao.findAll(aihe.getId()));

            return new ModelAndView(map, "viestit");
        }, new ThymeleafTemplateEngine());
        
        get("/aihe/:aiheid", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viestit", viestiDao.findAll(Integer.parseInt(req.params("aiheid"))));

            return new ModelAndView(map, "viestit");
        }, new ThymeleafTemplateEngine());
        
        post("/aihe/:aiheid", (req, res) -> {
            viestiDao.uusiViesti(Integer.parseInt(req.params("aiheid")), req.queryParams("viestiTeksti"), req.queryParams("viestiNimimerkki"));
            
            HashMap map = new HashMap<>();
            map.put("viestit", viestiDao.findAll(Integer.parseInt(req.params("aiheid"))));

            return new ModelAndView(map, "viestit");
        }, new ThymeleafTemplateEngine());
    }
}
