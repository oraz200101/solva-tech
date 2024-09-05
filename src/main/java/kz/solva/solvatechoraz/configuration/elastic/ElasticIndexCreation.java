package kz.solva.solvatechoraz.configuration.elastic;

import jakarta.annotation.PostConstruct;
import kz.solva.solvatechoraz.model.dto.TransactionResponseDto;
import kz.solva.solvatechoraz.worker.ElasticWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElasticIndexCreation {

    private final ElasticWorker elasticWorker;

    @PostConstruct
    private void createIndexes() {
        elasticWorker.createIndex(TransactionResponseDto.TRANSACTION_ELASTIC_INDEX_NAME, TransactionResponseDto.elasticMapping());
    }
}
