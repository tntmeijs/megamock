package dev.tahar.megamock.mocking.storage;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MocksRepository extends ReactiveMongoRepository<MockDocument, UUID> {
}
