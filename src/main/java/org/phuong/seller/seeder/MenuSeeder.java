package org.phuong.seller.seeder;

import java.util.List;
import java.util.Optional;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class MenuSeeder implements ApplicationRunner {

  private final VectorStore vectorStore;

  @Value("classpath:documents/menu.json")
  private final Resource menuResource;

  public MenuSeeder(VectorStore vectorStore, @Value("classpath:documents/menu.json") Resource menuResource) {
    this.vectorStore = vectorStore;
    this.menuResource = menuResource;
  }

  @Override
  public void run(ApplicationArguments args) {
    Optional<JdbcTemplate> nativeClient = vectorStore.getNativeClient();

    if (nativeClient.isEmpty()) {
      return;
    }

    JdbcTemplate jdbc = nativeClient.get();
    Integer count = jdbc.queryForObject("SELECT count(*) FROM vector_store", Integer.class);
    Assert.isTrue(count != null, "Count is null");

    if (count > 0) {
      return;
    }

    JsonReader jsonReader = new JsonReader(menuResource, "category", "name", "size", "price");
    List<Document> addingDocuments = jsonReader.get();
    vectorStore.add(addingDocuments);
  }
}
