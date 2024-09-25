package kz.solva.solvatechoraz.configuration.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConnectionFactory {

    private static final String ELASTIC_HOST = "localhost";
    private static final int ELASTIC_PORT = 9200;
    private static final String ELASTIC_SCHEME = "http";

    @Bean
    public RestClient restClient() {
        return RestClient.builder(
                        new HttpHost(ELASTIC_HOST, ELASTIC_PORT, ELASTIC_SCHEME))
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
