

package cindy.userregislogin.intfc;


import javax.validation.constraints.NotNull;
import java.util.List;

// import steamdom.master.model.Level;
// import steamdom.master.model.Subject;
// import steamdom.master.model.Semester;
import cindy.userregislogin.model.Regis;

public interface ParentRepositoryInf {
    List<Parent> findAll(int page, int limit);
    Long save(@NotNull Parent parent);
    Long size();
    Parent findById(@NotNull Long id);
   
} 
