package personal.project.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.project.email.model.Request;
import personal.project.email.service.RequestService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/requests")
public class RequestsController {

    @Autowired
    RequestService service;

    @PostMapping(value = "/save-request")
    public Mono<Request> saveRequest(@RequestBody Request request) {
        return service.saveRequest(request);
    }

    @GetMapping(value = "/fetch/{_id}")
    public Mono<Request> fetchRequest(@PathVariable String _id) {
        return service.fetchRequest(_id);
    }

    @PutMapping(value = "/update-request")
    public Mono<Request> updateRequest(@RequestBody Request request) {
        return service.updateRequest(request);
    }

    @DeleteMapping(value = "/delete-request/_id")
    public Mono<Request> deleteRequest(@PathVariable String requestId) {
        return service.deleteRequest(requestId);
    }
}
