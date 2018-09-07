package XStream;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @anthor zhangyuan33@jd.com
 * @date 2018/9/7 14:52
 */
@ToString
public class Student implements Cloneable {
	@Setter
	@Getter
	private int studentId;
	@Setter
	@Getter
	private String name;
	@Setter
	@Getter
	private Home home;

	Student(int id, String name, Home home) {
		this.studentId = id;
		this.name = name;
		this.home = home;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Student student = (Student) super.clone();
		student.home = (Home) home.clone();
		return student;
	}
}
