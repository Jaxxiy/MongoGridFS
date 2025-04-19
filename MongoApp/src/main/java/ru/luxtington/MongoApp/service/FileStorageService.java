package ru.luxtington.MongoApp.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileStorageService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String uploadFile(InputStream content, String filename, String contentType, Document metadata) throws IOException {
        GridFSUploadOptions options = new GridFSUploadOptions()
                .metadata(metadata);

        ObjectId objectId = gridFsTemplate.store(content, filename, contentType, options);
        return objectId.toString();
    }

    public GridFsResource downloadFile(String id) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (gridFSFile == null) {
            return null;
        }
        return gridFsTemplate.getResource(gridFSFile);
    }

    public void deleteFile(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }
}
