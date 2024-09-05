package kz.solva.solvatechoraz.worker;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import lombok.NonNull;

import java.util.Map;

public interface ElasticWorker {

   void createIndex(@NonNull String indexName, @NonNull Map<@NonNull String, @NonNull Property> indexMapping);

   void createDocument(@NonNull String indexName, @NonNull Map<String, Object> document, @NonNull String id);
}
