package dev.tahar.megamock.mocking;

import dev.tahar.megamock.mocking.data.MockInfo;
import dev.tahar.megamock.mocking.storage.MockDocument;
import dev.tahar.megamock.mocking.storage.MockStorageService;
import dev.tahar.megamock.mocking.storage.MocksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.UUID;

@SpringBootTest(classes = {MockStorageService.class})
final class MockStorageServiceTests {

    private final static UUID ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final static String TITLE = "UnitTestTitle";
    private final static String CATEGORY = "UnitTestCategory";
    private final static String ENDPOINT = "/unit/test";
    private final static HttpMethod HTTP_METHOD = HttpMethod.GET;

    @Autowired
    private MockStorageService mockStorageService;

    @MockBean
    private MocksRepository mocksRepository;

    @BeforeEach
    void init() {
        Mockito
                .when(mocksRepository.save(Mockito.any()))
                .thenReturn(Mono.just(new MockDocument(ID, TITLE, CATEGORY, ENDPOINT, HTTP_METHOD)));
    }

    @Test
    void storeNewMock_saveSuccessful() {
        // Mock a database response where no existing mock was not found
        Mockito
                .when(mocksRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Mono.empty());

        Assertions.assertTrue(
                mockStorageService
                        .storeMock(new MockInfo(ID, TITLE, CATEGORY, ENDPOINT, HTTP_METHOD))
                        .isPresent(),
                "Save should succeed when no existing database document was found");
    }

    @Test
    void storeDuplicateMock_saveFailed() {
        // Mock a database response where an existing mock was found
        Mockito
                .when(mocksRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Mono.just(new MockDocument(ID, TITLE, CATEGORY, ENDPOINT, HTTP_METHOD)));

        Assertions.assertFalse(
                mockStorageService
                        .storeMock(new MockInfo(ID, TITLE, CATEGORY, ENDPOINT, HTTP_METHOD))
                        .isPresent(),
                "Save should fail when an existing database document was found");
    }

    @Test
    void updateExistingMock_updateSuccessful() {
        // Mock a database response where an existing mock was found
        Mockito
                .when(mocksRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Mono.just(new MockDocument(ID, TITLE, CATEGORY, ENDPOINT, HTTP_METHOD)));

        Assertions.assertTrue(mockStorageService
                        .updateMock(new MockInfo(ID, TITLE, CATEGORY, ENDPOINT, HTTP_METHOD))
                        .isPresent(),
                "Update should succeed when an existing database document was found");
    }

    @Test
    void updateExistingMock_updateFailed() {
        // Mock a database response where no existing mock was not found
        Mockito
                .when(mocksRepository.findById(Mockito.any(UUID.class)))
                .thenReturn(Mono.empty());

        Assertions.assertFalse(mockStorageService
                        .updateMock(new MockInfo(ID, TITLE, CATEGORY, ENDPOINT, HTTP_METHOD))
                        .isPresent(),
                "Update should fail when no existing database document was found");
    }

    @Test
    void removeExistingMock_deleteCalled() {
        Mockito
                .when(mocksRepository.deleteById(Mockito.any(UUID.class)))
                .thenReturn(Mono.empty());

        mockStorageService.removeMockWithId(ID);
        Mockito.verify(mocksRepository, Mockito.times(1)).deleteById(Mockito.any(UUID.class));
    }

}
