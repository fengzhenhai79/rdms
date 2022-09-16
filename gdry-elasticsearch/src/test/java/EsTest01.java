import com.gdry.EsApplication;
import com.gdry.config.RestHighLevelClientBuilder;
import com.gdry.service.impl.RiverInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

@SpringBootTest(classes = EsApplication.class)
@RunWith(SpringRunner.class)
public class EsTest01 {

    @Autowired
    RestHighLevelClientBuilder restHighLevelClient;

    @Autowired
    private RiverInfoServiceImpl searchService;


//
//
//    // 测试索引的创建
//    @Test
//    void testCreateIndex() throws IOException {
//
//        // 1、创建索引请求
//        CreateIndexRequest request = new CreateIndexRequest("gdry_test02");
//        // 2、客户端执行请求 IndicesClient,请求后获得响应
//        CreateIndexResponse createIndexResponse =
//                gd.restHighLevelClient().indices().create(request, RequestOptions.DEFAULT);
//        System.out.println(createIndexResponse);
//    }
//
//    // 批量新增
//    @Test
//    public void insertList() {
//        List<ReservoirInfo> list = new ArrayList<>();
//        list.add(new ReservoirInfo(4,"广州河道信息","广州主要河道水位正常","河道","normal","200mm","2022-09-06 17:49:56"));
//        list.add(new ReservoirInfo(5,"茂名河道信息","茂名主要河道水位过底","河道","low","0mm","2022-09-05 17:49:56"));
//        // 接收对象集合，实现批量新增
//        reservoirInfoMapper.saveAll(list);
//    }
//
//    // 新增
//    @Test
//    public void insert() {
//        ReservoirInfo item = new ReservoirInfo(4,"广州河道信息","广州主要河道水位正常","河道","normal","200mm","2022-09-06 17:49:56");
//        reservoirInfoMapper.save(item);
//    }

    @Test
    public void segment() throws Exception{

        String text = "08月30日钱塘江的水势凶猛，注意防护";
        String text1 = "赵鹏超是个超级大反派的角色";

        Set set = new HashSet<>();

        StringReader re = new StringReader(text.trim());

        IKSegmenter ik= new IKSegmenter(re,true);

        Lexeme lex;

        while((lex = ik.next())!=null){

            set.add(lex.getLexemeText());
            System.out.print(lex.getLexemeText());

        }
        System.out.println(set);
    }

    @Test
    public void test01() throws Exception {
        String str = "2022年08月30日嘉陵江的水势凶猛，注意防护";
        String str1 = "2022年08月30日飞来峡的水位情况如何";
        Set<String> splitWords = getSplitWords(str1);
        System.out.println(splitWords);
        String[] s = splitWords.toArray(new String[splitWords.size()]);
        for (String s1 : s) {
            System.out.println(s1);
        }

    }

    public Set getSplitWords(String str) throws IOException {

        Set set = new HashSet<>();
        StringReader stringReader = new StringReader(str.trim());
        IKSegmenter ik = new IKSegmenter(stringReader,true);

        Lexeme lex;
        while((lex = ik.next())!=null){
            set.add(lex.getLexemeText());
            }
            return set;
    }

    @Test
    public void test03() throws IOException {
        String str = "2022年9月10日23时柳家营水位情况如何";
        String str1 = "赵鹏超是个超级大反派的角色";
        Map<Integer,String> map = new HashMap<>();
        StringReader stringReader = new StringReader(str1.trim());
        IKSegmenter ik = new IKSegmenter(stringReader,true);

        Integer i = 0;
        Lexeme lex;
        while((lex = ik.next())!=null){
            System.out.print(lex.getLexemeText() + " ");
            i++;
            map.put(i,lex.getLexemeText());
        }
//        System.out.println(map);

        String date = map.get(1);
        String date1 = date.substring(0, date.length() - 1);
        String hour = map.get(4);
        String hour1 = hour.substring(0, hour.length() - 1);
//        String name = map.get(5);
//        System.out.println(date1);
//        System.out.println(hour1);
//        System.out.println(name);
    }

}
