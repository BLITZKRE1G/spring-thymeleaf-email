package personal.project.email.database;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import personal.project.email.model.Request;

public interface RequestsRepository extends ReactiveMongoRepository<Request, String> {

}
