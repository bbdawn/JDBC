package model;

public class EmployeeVO {
	private int empno;
	private String jame;
	private String job;
	private int salary;
	public EmployeeVO(int empno, String jame, String job, int salary) {
		super();
		this.empno = empno;
		this.jame = jame;
		this.job = job;
		this.salary = salary;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getJame() {
		return jame;
	}
	public void setJame(String jame) {
		this.jame = jame;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "EmployeeVO [empno=" + empno + ", jame=" + jame + ", job=" + job + ", salary=" + salary + "]";
	}
	
	
	
	
	
	
}
