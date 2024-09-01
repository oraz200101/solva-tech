package kz.solva.solvatechoraz.configuration;

import feign.codec.ErrorDecoder;
import kz.solva.solvatechoraz.client.CurrencyClientErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CurrencyClientErrorDecoder();
    }
}
