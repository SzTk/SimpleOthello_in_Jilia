/**
 *
 */
package predictor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.ArrayList;

import predictor.Instance;

/**
 * データを管理するクラス。
 * @author artul
 * @param <E>
 *
 */
public class DataFrame extends LinkedHashMap<Integer, Instance> {

	/**
	 * 属性名。順序付き辞書で管理。
	 */
	public LinkedHashSet<String> attribute_names = new LinkedHashSet<String>();

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ。
	 */
	DataFrame(){}

	/**
	 * Instanceを追加する。
	 */
	public Instance put(Integer iid, Instance instance) {
		attribute_names.addAll(instance.attributeNames());
		this.checkSameAttributeHasSameDataType();
		return super.put(iid, instance);
	}

	/**
	 * 同じ属性名に同じ方が入っているかチェックする。
	 * インスタンスが追加されるたびに呼び出される。
	 * @return
	 */
	private boolean checkSameAttributeHasSameDataType() {
		return true;
	}

	/**
	 * 持っているInstanceの Id のリストを返す。
	 * @return
	 */
	public Set<Integer> instanceIds() {
		return this.keySet();
	}

	public DataFrame fromCSV(String filePath) {
		DataFrame df = new DataFrame();
		try {
        // ファイルを読み込む
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        String line;
        String[] token;

        // 一行目は属性名として読む。
        token = br.readLine().split(",");
        ArrayList<String> attribute_names = new ArrayList<String>();
        for (int i=0; i<token.length; i++) {
        	attribute_names.add(token[i]);
        }
        // ここでは仮で行番号＝instande idとする。
        int line_num = 1;

        // 1行ずつInstanceにする。
        while ((line = br.readLine()) != null) {
            token = line.split(",");
            Instance ins = new Instance();
            for (int x=0; x<token.length; x++) {
            	ins.put(attribute_names.get(x), token[x]);
            }
            df.put(line_num, ins);
            line_num++;
        }

        //終了処理
        br.close();
        return df;
		} catch (IOException ex) {
        //例外発生時処理
        ex.printStackTrace();
        }
		return null;
	}

	public void toCSV(String outputFilePath) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter(outputFilePath, false);
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

			// 一行目に属性名を出力
			for (Iterator<String> i = this.attribute_names.iterator(); i.hasNext();) {
				pw.print(i.next());
				if (i.hasNext()) {
					pw.print(",");
				}
			}
			pw.print("\n");

			// ２行目以降にInstanceを出力
			for (Iterator<Integer> i = this.instanceIds().iterator(); i.hasNext();) {
				Instance ins = this.get(i.next());
				for (Iterator<String> j = this.attribute_names.iterator(); j.hasNext();) {
					pw.print(ins.get(j.next()));
					if (j.hasNext()) {
						pw.print(",");
					}
				}
				pw.print("\n");
			}

		pw.close();
		} catch (IOException ex) {
            //例外時処理
            ex.printStackTrace();
        }

	}
}
