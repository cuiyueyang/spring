package es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * <p>Description: 插入数据</p>
 * <p>Date: 2023/03/07 23:16</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class ESTest_Doc_Insert {

    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        //插入数据
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");
        User user = new User();
        user.setName("cuiyy");
        user.setAge(20);
        user.setSex("男");
        //向es插入数据，必须将数据转换为JSON格式
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());


        //关闭es客户端
        esClient.close();
    }

}
