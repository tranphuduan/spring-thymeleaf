package com.base.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    public String uploadPhoto(String title, MultipartFile file) throws IOException {
        ObjectId id = gridFsTemplate.store(file.getInputStream(), title, file.getContentType());
        return id.toString();
    }

    public GridFSFile getPhoto(String id) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    public GridFsResource getPhotoResource(GridFSFile file) {
        return operations.getResource(file);
    }

    // Phương thức xóa ảnh
    public void deletePhoto(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }
}
