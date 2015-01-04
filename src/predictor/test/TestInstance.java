package predictor.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import predictor.Instance;

public class TestInstance {

	public Instance ins;

	@Before
	public void setup() {
		Instance instance = new Instance();
		instance.put("hogehoge", 10);
		instance.put("fugafuga", "fugafugavalue");
		instance.put("funge", 0.123);
		this.ins = instance;
	}

	@Test
	public void testKeepAttributeOeder() {
		Object desired_array[] = new Object[3];
		desired_array[0] = "hogehoge";
		desired_array[1] = "fugafuga";
		desired_array[2] = "funge";

		assertTrue((this.ins.attributeNames().toArray()[0] == desired_array[0])
				&& (this.ins.attributeNames().toArray()[1] == desired_array[1])
				&& (this.ins.attributeNames().toArray()[2] == desired_array[2]));
	}

	@Test
	public void testContainProperValue() {
		assertTrue(((int)ins.get("hogehoge") == 10)
				&& (ins.get("fugafuga") == "fugafugavalue")
				&& ((double)ins.get("funge") == 0.123));


	}
}
