package demo.jingdong.diary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@RequiredArgsConstructor()
public class TestGson {

	/**
	 * bussinessType : 100784
	 * clientIP : 11.24.112.232
	 * orgType : 0
	 * originId : 2
	 * serverName : pinServer
	 * userIP : 106.38.115.29
	 * webOriginId : 3
	 */
	@Setter @Getter private long bussinessType;
	@Setter @Getter private String clientIP;
	@Setter @Getter private int orgType;
	@Setter @Getter private int originId;
	@Setter @Getter private String serverName;
	@Setter @Getter private String userIP;
	@Setter @Getter private int webOriginId;

	public static void main(String[] args) {
		TestGson test = new TestGson();
		test.setBussinessType(107775);
		test.setClientIP("11.24.112.232");
		test.setOrgType(0);
		test.setOriginId(2);
		test.setServerName("pinServer");
		test.setUserIP("106.38.115.29");
		test.setWebOriginId(3);
		System.out.println(test.toString());
	}
}
