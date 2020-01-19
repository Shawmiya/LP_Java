class Employee{
    private int empId;
    private String empName;
    private int empAge;

    public int getEmpId(){
        return empId;
    }

    public String getEmpName(){
        return empName;
    }

    public int getEmpAge(){
        return empAge;
    }

    public void setEmpAge(int newAge){
        empAge = newAge;
    }

    public void setEmpName(String newName){
        empName = newName;
    }

    public void setEmpId(int newId){
        empId = newId;
    }
}
public class EmployeeTest{
    public static void main(String args[]){
         Employee obj = new Employee();
         obj.setEmpName("Ali");
         obj.setEmpAge(25);
         obj.setEmpId(24566);
         System.out.println("Employee Name: " + obj.getEmpName());
         System.out.println("Employee Id: " + obj.getEmpId());
         System.out.println("Employee Age: " + obj.getEmpAge());
    } 
}