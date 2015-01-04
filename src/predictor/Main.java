package predictor;
import predictor.Instance;
import predictor.DataFrame;

public class Main {

	public static void main(String[] args) {
		Instance instance = new Instance();
		instance.put("hogehoge", 10);
		instance.put("fugafuga", "fugafugavalue");
		instance.put("funge", 0.123);
		//System.out.println(instance.get("funge"));
		//System.out.println(instance.toString());
		//System.out.println(instance.attributeNames());

		Instance instance2 = new Instance();

		instance2.put("hogehoge", 12);
		instance2.put("hage", 0.345);
		//System.out.println(instance2.attributeNames());

		DataFrame df = new DataFrame();
		df.put(1, instance);
		//System.out.println(df.toString());

		df.put(2, instance2);
		//System.out.println(df.toString());

		//System.out.println(df.attribute_names);
		//System.out.println(df.instanceIds());

		df = df.fromCSV("/Users/artul/Documents/workspace/predictor/src/testdata/test.csv");
		//System.out.println(df.toString());
		//System.out.println(df.attribute_names);

		df.toCSV("/Users/artul/Documents/workspace/predictor/src/testdata/output_test.csv");
	}

}
