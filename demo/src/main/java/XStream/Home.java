package XStream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/7 14:48
 */
@ToString
public class Home implements Cloneable {

	@Setter
	@Getter
	private String address;
	@Setter
	@Getter
	private int cityId;

	Home(String address, int cityId) {
		this.address = address;
		this.cityId = cityId;
	}

	@Override
	public Object clone() {
		Home home = null;

		try {
			home = (Home) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return home;
	}
}
