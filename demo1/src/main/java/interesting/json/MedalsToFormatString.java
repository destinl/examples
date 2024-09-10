package interesting.json;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/10 22:59
 */
public class MedalsToFormatString {
    private static final String TotalMedalsFormatString = "Rank: %d, Organisation: %s, Gold: %d, Silver: %d, Bronze: %d, Total: %d";

    // item为medalsTable的某叶节点
    private String medalsToFormatString(JSONObject item) {
        int rank = item.getIntValue("rank");
        String organisation = item.getString("longDescription");
        AtomicInteger gold = new AtomicInteger();
        AtomicInteger silver = new AtomicInteger();
        AtomicInteger bronze = new AtomicInteger();
        AtomicInteger total = new AtomicInteger();
        List<JSONObject> medalsNumber;
        medalsNumber = item.getJSONArray("medalsNumber").toJavaList(JSONObject.class);
        medalsNumber.forEach(obj -> {
            if (obj.getString("type").equalsIgnoreCase("total")) {
                gold.set(obj.getIntValue("gold"));
                silver.set(obj.getIntValue("silver"));
                bronze.set(obj.getIntValue("bronze"));
                total.set(obj.getIntValue("total"));
            }
        });

        return String.format(TotalMedalsFormatString,
                rank,
                organisation,
                gold.get(),
                silver.get(),
                bronze.get(),
                total.get());
    }

    public static void main(String[] args) {
        MedalsToFormatString medalFormatting = new MedalsToFormatString();
        String filePath = "D:\\IdeaProjects\\examples\\demo1\\src\\main\\java\\interesting\\json\\medals.json";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine())!= null) {
                stringBuilder.append(line);
            }
            JSONObject jsonObject = JSONObject.parseObject(stringBuilder.toString());
//            System.out.println(jsonObject.toString());
//            JSONObject jsonObject = JSONObject.parseObject(jsonObject);
            JSONObject props = jsonObject.getJSONObject("props");
            JSONObject paperProps = props.getJSONObject("pageProps");
            JSONObject initialMedals = paperProps.getJSONObject("initialMedals");
            JSONObject medalStandings = initialMedals.getJSONObject("medalStandings");
            JSONArray medalsTable = medalStandings.getJSONArray("medalsTable");

            for (Object obj : medalsTable) {
                if (obj instanceof JSONObject) {
                    JSONObject item = (JSONObject) obj;
                    String result = medalFormatting.medalsToFormatString(item);
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
