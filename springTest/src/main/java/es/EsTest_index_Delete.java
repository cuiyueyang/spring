package es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * <p>Description: 删除索引</p>
 * <p>Date: 2023/03/07 23:16</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class EsTest_index_Delete {

    public static void main(String[] args) throws Exception {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        //删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
        //响应状态
        System.out.println(response.isAcknowledged());
        //关闭es客户端
        esClient.close();
    }

}
