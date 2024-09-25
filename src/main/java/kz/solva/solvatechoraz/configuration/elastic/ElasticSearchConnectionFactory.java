package kz.solva.solvatechoraz.configuration.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConnectionFactory {

    @Value("${spring.elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${spring.elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${spring.elasticsearch.schema}")
    private String elasticSearchSchema;

    @Bean
    public RestClient restClient() {
        return RestClient.builder(
                        new HttpHost(elasticsearchHost, elasticsearchPort, elasticSearchSchema))
                .build();
    }

    @Bean
    public ElasticsearchTransport elasticsearchTransport(RestClient restClient) {
        return new RestClientTransport(
                restClient, new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticsearchTransport transport) {
        return new ElasticsearchClient(transport);
    }
}
