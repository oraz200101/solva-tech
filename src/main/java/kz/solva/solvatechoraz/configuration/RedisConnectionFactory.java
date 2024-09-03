package kz.solva.solvatechoraz.configuration;

import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConnectionFactory {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedisTemplate<String, CurrencyEntity> currencyRedisTemplate() {
        RedisTemplate<String, CurrencyEntity> currencyRedisTemplate = new RedisTemplate<>();
        currencyRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        currencyRedisTemplate.setKeySerializer(new StringRedisSerializer());
        currencyRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return currencyRedisTemplate;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfigurationBuilder =
                JedisClientConfiguration.builder();

        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfigurationBuilder.build());
    }
}
