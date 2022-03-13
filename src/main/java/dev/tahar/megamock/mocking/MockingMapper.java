package dev.tahar.megamock.mocking;

import dev.tahar.megamock.mocking.data.MockInfo;
import dev.tahar.megamock.mocking.storage.MockDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MockingMapper {

    MockingMapper INSTANCE = Mappers.getMapper(MockingMapper.class);

    MockInfo mockDocumentToMockInfo(final MockDocument mockDocument);
    MockDocument mockInfoToMockDocument(final MockInfo mockInfo);

}
