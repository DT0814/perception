package com.xawl.perception.controller;

import com.xawl.perception.pojo.Image;
import com.xawl.perception.pojo.result.SimpleResult;
import com.xawl.perception.service.ImageService;
import com.xawl.perception.utils.PageInfo;
import com.xawl.perception.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("img")
public class ImageController {
    @Autowired
    private ImageService service;

    @GetMapping("findAll")
    public SimpleResult findAll(Integer page, Image image) {
        if (null == page || page == 0) {
            List<Image> res = service.findAll();
            return SimpleResult.Success(res);
        } else {
            Page<Image> all = service.findAll(--page, image);
            PageInfo<Image> pageInfo = new PageUtils<Image>().getPageInfo(all);
            SimpleResult instance = SimpleResult.getInstance(0);
            instance.setData(pageInfo);
            return instance;
        }
    }
    @PostMapping("delete")
    public SimpleResult delete(Integer imid){
        service.delete(imid);
        return SimpleResult.getInstance(0);
    }
}
