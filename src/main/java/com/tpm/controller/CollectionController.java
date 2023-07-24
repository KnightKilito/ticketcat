package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tpm.entity.Collection;
import com.tpm.entity.JsonResult;
import com.tpm.entity.Movie;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.CollectionService;
import com.tpm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    MovieService movieService;

//    @GetMapping("tt")
//    public JsonResult test(){
//        List<Object> list = new ArrayList<>();
//        Map<String,Integer> map = new HashMap<>();
//        map.put("seat",1);
//        map.put("status",0);
//        list.add(map);
//        Map<String,Integer> map2 = new HashMap<>();
//        map2.put("seat",1);
//        map2.put("status",0);
//    }

    @PostMapping("/add/{uid}/{mid}")
    public JsonResult addCollection(@PathVariable int uid, @PathVariable int mid){
        Collection c = new Collection();
        c.setMid(mid);
        c.setUid(uid);
        c.setCollectionid((int)(Math.random()*99999+1000));
        QueryWrapper queryWrapper= new QueryWrapper();
        queryWrapper.eq("mid",mid);
        queryWrapper.eq("uid",uid);
       List<Collection> coL = collectionService.list(queryWrapper);
       if(coL.size()!=0){
           throw new MyException(StateEnums.ADD_ERROR,"改收藏已经存在");
       }
       else {
           boolean is = collectionService.save(c);
           if (is) {
               return new JsonResult("成功");
           } else {
               throw new MyException(StateEnums.ADD_ERROR, "添加异常");
           }
       }
    }

    //判断是否收藏
    @GetMapping("{uid}/{mid}")
    public boolean ifC(@PathVariable int uid,@PathVariable int mid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("mid",mid);
        List<Collection> collectionList = collectionService.list(queryWrapper);
        if(collectionList.size()==0){
            return false;
        }else{
            return true;
        }

    }

    @DeleteMapping("{collection}")
    public  JsonResult DelCollection(@PathVariable int collection){
        boolean isDel = collectionService.removeById(collection);
        if(isDel){
            return new JsonResult("成功");
        }
        else{
            return new JsonResult(-1,"失败");

        }
    }
    @DeleteMapping("remove/{uid}/{mid}")
    public  JsonResult DelC(@PathVariable int uid,@PathVariable int mid){
        QueryWrapper queryWrapper  = new QueryWrapper();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("mid",mid);
        if(collectionService.remove(queryWrapper)){
            return new JsonResult("成功");
        }else{
            throw new MyException(StateEnums.REMOVE_ERROR,"删除收藏异常");
        }

    }

    @GetMapping("{uid}")
    public JsonResult<Movie> fingMyCollection(@PathVariable int uid){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);

        List<Collection> collectionList = collectionService.list(queryWrapper);
        List<Movie> movieList  = new ArrayList<>();
        for(Collection c:collectionList){
            movieList.add(movieService.getById(c.getMid()));
        }
        return new JsonResult<>(movieList);
    }
}
