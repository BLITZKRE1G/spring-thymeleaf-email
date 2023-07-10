package personal.project.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import personal.project.email.database.RequestsRepository;
import personal.project.email.model.Request;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RequestService {

    @Autowired
    RequestsRepository repository;

    public Mono<Request> saveRequest(Request request) {
        return repository.save(request);
    }

    public Mono<Request> fetchRequest(String _id) {
        return repository.findById(_id)
                .switchIfEmpty(Mono.defer(() -> {
                    throw new RuntimeException("Requested ID is not present in the Database!");
                })).flatMap(request -> {
                    log.info("Found: {}", request);
                    return Mono.just(request);
                });
    }

    public Mono<Request> deleteRequest(String _id) {
        return repository.findById(_id)
                .switchIfEmpty(Mono.defer(() -> {
                    throw new RuntimeException("The Requested ID is not present in the Database!");
                })).flatMap(request -> {
                    repository.delete(request);
                    log.info("Deleted: ", request);
                    return Mono.just(request);
                });
    }

    public Mono<Request> updateRequest(Request request) {
        return repository.findById(request.getId())
                .switchIfEmpty(Mono.defer(() -> {
                    throw new RuntimeException("Data doesn't exist for the RequestID");
                })).flatMap(requestData -> {
                    log.info("Saving the new Data => {}", requestData);
                    return repository.save(requestData);
                });
    }
}
