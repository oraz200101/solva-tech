package kz.solva.solvatechoraz.worker.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import kz.solva.solvatechoraz.worker.ElasticWorker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ElasticWorkerImpl implements ElasticWorker {
    private final ElasticsearchClient elasticsearchClient;

    @Override
    @SneakyThrows
    public void createIndex(@NonNull String indexName, @NonNull Map<@NonNull String, @NonNull Property> indexMapping) {
        boolean indexExists = elasticsearchClient.indices().exists(request -> request.index(indexName)).value();

        if (!indexExists) {
            TypeMapping mapping = new TypeMapping.Builder()
                    .properties(indexMapping).build();

            elasticsearchClient.indices().create(request -> request
                    .index(indexName)
                    .mappings(mapping)
                    .settings(new IndexSettings.Builder()
                            .numberOfShards("1")
                            .numberOfReplicas("1")
                            .build())
            );
        }
    }

    @Override
    @SneakyThrows
    public void createDocument(@NonNull String indexName, @NonNull Map<String, Object> document, @NonNull String id) {
        IndexRequest<Map<String, Object>> request = new IndexRequest.Builder<Map<String, Object>>()
                .index(indexName)
                .id(id)
                .document(document)
                .build();

        elasticsearchClient.index(request);
    }

}
