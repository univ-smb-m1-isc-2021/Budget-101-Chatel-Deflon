package application;

import persistence.Test;
import persistence.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    private final TestRepository repository;

    public TestService(TestRepository repository) {
        this.repository = repository;
    }

    public List<Test> tests() {
        return this.repository.findAll();
    }

    public void delete(Long factId) {
        Optional<Test> fact = repository.findById(factId);
        fact.ifPresent(repository::delete);
    }

    public void create(String nom, String description) {
        repository.save(new Test(nom, description));
    }
}
