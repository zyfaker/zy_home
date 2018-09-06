package demo.jingdong.diary;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XStreamTest {
	@XStreamAlias("住址")
	private static class Room{
		@XStreamAlias("編號")
		int number;
		@XStreamAlias("街道")
		String address;
		Room(int number,String address){
			this.number = number;
			this.address = address;
		}
	}
	@XStreamAlias("學生信息")
	private static class Person{
		@XStreamAlias("年齡")
		int age;
		@XStreamAsAttribute
		@XStreamAlias("姓名")
		String name;
		@XStreamAlias("住址")
		Room room;
		Person(int age,String name,Room room){
			this.age = age;
			this.name = name;
			this.room = room;
		}
	}
	public static void main(String[] args){

		Room room = new Room(1212614,"衛津路94號");

		Person person = new Person(20,"張元",room);

		XStream xStream = new XStream();
		//綁定安全框架
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[]{XStreamTest.class,Room.class, Person.class});

		//綁定注解偵測
		xStream.autodetectAnnotations(true);
/*
		xStream.alias("學生信息",Person.class);
		xStream.aliasField("地址信息",Person.class,"room");*/
		String xml = xStream.toXML(person);

		System.out.println(xml);

		person = (Person) xStream.fromXML(xml);

		xStream = new XStream(new JettisonMappedXmlDriver());
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.alias("學生信息",Person.class);
		String gson = xStream.toXML(person);

		System.out.println(gson);

	}
}
