package dev.tahar.megamock.mocking.storage;

import dev.tahar.megamock.mocking.MockingMapper;
import dev.tahar.megamock.mocking.data.MockInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public final class MockStorageService {

    private final MocksRepository mocksRepository;

    /**
     * Store a mock in the database
     *
     * @param mockInfo Mock information
     * @return {@link Optional} {@link MockInfo} on success, {@link Optional#empty()} if the mock could not be stored
     */
    public Optional<MockInfo> storeMock(@NonNull final MockInfo mockInfo) {
        if (mocksRepository.findById(mockInfo.getId()).blockOptional().isPresent()) {
            // Object already exists, cannot store duplicate items
            return Optional.empty();
        }

        return mocksRepository
                .save(MockingMapper.INSTANCE.mockInfoToMockDocument(mockInfo))
                .blockOptional()
                .map(MockingMapper.INSTANCE::mockDocumentToMockInfo);
    }

    /**
     * Update an existing mock in the database
     *
     * @param mockInfo Mock information
     * @return {@link Optional} {@link MockInfo} on success, {@link Optional#empty()} if the mock could not be stored
     */
    public Optional<MockInfo> updateMock(@NonNull final MockInfo mockInfo) {
        if (mocksRepository.findById(mockInfo.getId()).blockOptional().isEmpty()) {
            // Object does not exist yet, cannot update nonexistent items
            return Optional.empty();
        }

        return mocksRepository
                .save(MockingMapper.INSTANCE.mockInfoToMockDocument(mockInfo))
                .blockOptional()
                .map(MockingMapper.INSTANCE::mockDocumentToMockInfo);
    }

    /**
     * Delete an existing mock from the database
     *
     * @param id Unique ID of the mock that should be removed from the database
     */
    public void removeMockWithId(@NonNull final UUID id) {
        mocksRepository
                .deleteById(id)
                .block();
    }

    /**
     * Retrieve all mocks from the database
     *
     * @return {@link List} of {@link MockInfo} objects
     */
    public List<MockInfo> getAllMocks() {
        return mocksRepository
                .findAll()
                .collectList()
                .blockOptional()
                .orElse(Collections.emptyList())
                .stream()
                .map(MockingMapper.INSTANCE::mockDocumentToMockInfo)
                .toList();
    }

    /**
     * Attempt to find a mock with the specified ID
     *
     * @param id {@link UUID} of the mock to retrieve
     * @return {@link Optional} {@link MockInfo} when the mock was found, {@link Optional#empty()} if the mock could not be found
     */
    public Optional<MockInfo> getMockWithId(@NonNull final UUID id) {
        return mocksRepository
                .findById(id)
                .blockOptional()
                .map(MockingMapper.INSTANCE::mockDocumentToMockInfo);
    }

}
