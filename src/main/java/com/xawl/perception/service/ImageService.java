package com.xawl.perception.service;

import com.xawl.perception.dao.ImageDao;
import com.xawl.perception.pojo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageDao dao;

    public Image add(Image image) {
        image.setMids("");
        image = dao.saveAndFlush(image);
        return image;
    }

    public List<Image> findAll() {
        return dao.findAll();
    }

    public Page<Image> findAll(Integer pageNumber, Image image) {
        Sort sort = new Sort(Sort.Direction.DESC, "imid");
        Pageable pageable = PageRequest.of(pageNumber, 8, sort);
        Example<Image> example = Example.of(image, ExampleMatcher.matching());
        Page<Image> all = dao.findAll(example, pageable);
        return all;
    }

    public void delete(Integer imid) {
        dao.deleteById(imid);
    }
}
