package XStream;

/**
 * @anthor zhangyuan33@jd.com
 * @date 2018/9/7 14:56
 */
public class ShallowCopy {

	public static void main(String[] args) throws CloneNotSupportedException {
		Home home = new Home("同砚路38号",21);

		Student s1 = new Student(1212614,"zhangyuan",home);
		//拷贝
		Student s2 = (Student) s1.clone();
		//是同一个
		Student s3 = s2;

		System.out.println("before");
		System.out.println("s1:"+s1.toString());
		System.out.println("s2:"+s1.toString());
		System.out.println("s3:"+s3.toString());

		s2.setName("liucx");
		s2.setStudentId(1212628);
		s2.getHome().setAddress("卫津路94号");
		s2.getHome().setCityId(10);

		System.out.println("after");
		System.out.println("s1:"+s1.toString());
		System.out.println("s2:"+s2.toString());
	}
}
