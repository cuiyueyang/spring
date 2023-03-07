package es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * <p>Description: 创建索引</p>
 * <p>Date: 2023/03/07 23:09</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class ESTest_index_Create {

    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        //响应状态
        boolean acknowledged = response.isAcknowledged();
        System.out.println("索引操作："+ acknowledged);
        //关闭es客户端
        esClient.close();
    }

}
