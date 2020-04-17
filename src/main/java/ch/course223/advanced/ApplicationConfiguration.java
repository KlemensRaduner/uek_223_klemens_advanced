package ch.course223.advanced;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories("ch.course223.advanced")
class ApplicationConfiguration {


}