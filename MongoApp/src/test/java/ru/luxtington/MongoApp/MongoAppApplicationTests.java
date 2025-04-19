package ru.luxtington.MongoApp;

import org.bson.Document;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import ru.luxtington.MongoApp.service.FileStorageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MongoAppApplicationTests {

	@Autowired
	private FileStorageService fileStorageService;

	@Test
	public void testUploadDownloadDelete() throws IOException {
		// 1. Создаем тестовый файл
		String fileContent = "Hello, GridFS!";
		InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
		String filename = "test.txt";
		String contentType = "text/plain";

		// 2. Загружаем файл в GridFS
		Document metadata = new Document();
		metadata.append("author", "Test User");
		String fileId = fileStorageService.uploadFile(inputStream, filename, contentType, metadata);
		assertNotNull(fileId);

		// 3. Скачиваем файл из GridFS
		Resource resource = fileStorageService.downloadFile(fileId);
		assertNotNull(resource);
		assertEquals(filename, resource.getFilename());

		// Читаем содержимое файла
		String downloadedContent = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		assertEquals(fileContent, downloadedContent);

		// 4. Удаляем файл из GridFS
		fileStorageService.deleteFile(fileId);

		// Проверяем, что файл удален
		Resource deletedResource = fileStorageService.downloadFile(fileId);
		assertNull(deletedResource);
	}

}
