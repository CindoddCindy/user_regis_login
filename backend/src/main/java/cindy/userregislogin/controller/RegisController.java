package cindy.userregislogin.controller;

import steamdom.master.model.Project;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Delete;
import io.micronaut.validation.Validated;
import io.reactivex.annotations.Nullable;

import com.google.gson.Gson;
import cindy.userregislogin.repository.*;

import java.util.HashMap;
import java.util.List;

@Validated
@Controller("/regis")
public class RegisController {

    private RegisInterface regisInterface;

    public RegisController(RegisInterface regisInterface) {
        this.regisInterface = regisInterface;
    }

    @Get(processes = MediaType.APPLICATION_JSON)
    public String index(@Nullable @QueryValue final int page, @QueryValue final int limit) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            final List<Regis> regis = regisInterface.findAll(page, limit);
            data.put("status", "ok");
            data.put("message", "Data Project");
            data.put("page", Math.ceil(regisInterface.size() / limit));
            data.put("data", regis);
            return (new Gson().toJson(data));
        } catch (Exception e) {
            data.put("status", "error");
            data.put("message", e.getMessage());
            return (new Gson().toJson(data));
        }
    }

    @Get("{/id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String show(@PathVariable @Nullable final Long id) {
        return (new Gson().toJson(regisInterface.findById(id)));
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String save(@Body final Regis regis) {
        final HashMap<String, Object> data = new HashMap<>();
        try {
            // boolean exist = repository.existByName(projecttype.getName());
            if (regis.getName() == null || regis.getName() == "") {
                return "nama tidak boleh kosong";
            }
            if (regis.getCategory() == null || regis.getCategory() == "") {
                return "category tidak boleh kosong";
            }
            if (regis.getDescription() == null || regis.getDescription() == "") {
                return "nama description tidak boleh kosong";
            }
           
            // if(exist == true) {
            Regis regisresult = regisInterface.save(regisresult);
            if (regisresult != null) {
                data.put("status", "ok");
                data.put("message", "Data User");
                data.put("data", regisresult);
                return (new Gson().toJson(data));
            } else {
                data.put("status", "error");
                data.put("message", "failed data already exist");
                data.put("data", regisresult);
                return (new Gson().toJson(data));
            }
            // }
            // return (new Gson()).toJson(data);
        } catch (Exception e) {
            String message = e.getMessage();
            data.put("status", "errors");
            data.put("message", message);
            return (new Gson().toJson(data));
        }
    }

 
}