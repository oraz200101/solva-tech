package kz.solva.solvatechoraz.configuration.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElasticIndexCreation {

    private final ElasticsearchClient elasticsearchClient;


}
