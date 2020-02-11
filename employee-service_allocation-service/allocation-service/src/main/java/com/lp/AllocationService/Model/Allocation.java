lp com.lp.AllocationService.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Allocation {

    @Id
    @GeneratedValue
    private int id;
    private String projectName;
    private String empName;

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
