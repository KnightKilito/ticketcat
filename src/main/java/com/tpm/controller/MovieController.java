package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tpm.Utils.QiniuUtils;
import com.tpm.entity.Cinema_Movie;
import com.tpm.entity.JsonResult;
import com.tpm.entity.Movie;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.CinemaMovieService;
import com.tpm.service.MovieService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    QiniuUtils qiniuUtils ;
    @Autowired
    MovieService movieService;

    @Autowired
    CinemaMovieService cinemaMovieService;


    //根据mid查找电影
    @GetMapping("mid/{mid}")
    public JsonResult<Movie> findByMid(@PathVariable int mid){
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("mid",mid);
        return new JsonResult<Movie>(movieService.getOne(queryWrapper));
    }
    //增加一个电影
    @PostMapping("")
    public JsonResult saveMovie(@Valid  Movie movie, BindingResult br){
        if(br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR, br.getFieldError().getDefaultMessage());

        }else{
                movieService.save(movie);
                return new JsonResult("添加电影成功");
        }

    }

    //管理员删除电影
    @DeleteMapping("/{mid}")
    public JsonResult delMovie(@PathVariable int mid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mid",mid);
        boolean isDel = movieService.remove(queryWrapper);
        List<Cinema_Movie> cinema_movieList = cinemaMovieService.list(queryWrapper);
        if(cinema_movieList.size()!=0){
            cinemaMovieService.removeBatchByIds(cinema_movieList);
        }
        if(isDel){
            return new JsonResult("删除成功");
        }else{
            return new JsonResult(-1,"删除失败");
        }
    }
    //管理员删除某个电影院的电影
    @DeleteMapping("/remove/{cid}/{mid}")
    public  JsonResult r(@PathVariable int cid,@PathVariable int mid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cid",cid);
        queryWrapper.eq("mid",mid);
        if(cinemaMovieService.remove(queryWrapper)){
            return new JsonResult("chengg");
        }
        else{
            throw new MyException(StateEnums.REMOVE_ERROR,"删除异常");
        }
    }

    //查询全部电影
    @GetMapping("")
    public JsonResult<Movie> findAllMovie(){

        return  new JsonResult<Movie>(movieService.list());
    }
    //查询全部电影
    @GetMapping("/gmovie")
    public JsonResult<Movie> findssAllMovie(){

        return  new JsonResult<Movie>(movieService.list());
    }

    //修改电影信息
    @PutMapping("")
    public JsonResult editMovie(@Valid Movie movie,BindingResult br){

        if(br.hasErrors()){
            throw  new MyException(StateEnums.EDIT_ERROR,br.getFieldError().getDefaultMessage());
        }
        movieService.updateById(movie);
        return new JsonResult("成功");
    }

    //根据label赛选电影
    @GetMapping("label/{label}")
    public JsonResult<Movie> findByLabel(@PathVariable String label){
        Map<String,Object> map=new HashMap<>();

        map.put("label",label);
        return new JsonResult<Movie> (movieService.listByMap(map));
    }

    //分页查询
    @GetMapping("{current}/{limit}")
    public JsonResult<Movie> page(@PathVariable long  current,@PathVariable long limit){
        Page<Movie> objectPage = new Page<>(current,limit);
        movieService.page(objectPage,null);
        long total = objectPage.getTotal();
        List<Movie> records = objectPage.getRecords();

        return new JsonResult<Movie>(total,records);

    }

    //条件分页
    @GetMapping("{current}/{limit}/{label}")
    public JsonResult<Movie> PgaeByLabel(@PathVariable long current,@PathVariable long limit,@PathVariable String label){
        Page<Movie> objectPage = new Page<>(current,limit);
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("label",label);
        movieService.page(objectPage, queryWrapper);
        long total = objectPage.getTotal();  //总记录数
        List<Movie> records = objectPage.getRecords(); //集合
        return new JsonResult<Movie>(total,records);
    }



    //通过影院查看上线的影片
    @GetMapping("/cid/{cid}")
    public JsonResult<Movie> findByCid(@PathVariable int cid){
        Map<String,Object> map = new HashMap<>();
        map.put("cid",cid);
        List<Movie> movieList = new ArrayList<>();
        List<Cinema_Movie> cinema_movieList = cinemaMovieService.listByMap(map);
        for(Cinema_Movie cm:cinema_movieList){
            movieList.add(movieService.getById(cm.getMid()));
        }
        return new JsonResult<>(movieList);
    }


    //查询是否上映的影片
    @GetMapping("/status/{status}")
    public JsonResult<Movie> findByStatus(@PathVariable int status){
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();

        if(status==1){
            queryWrapper.ge("status",0);
            return new JsonResult<Movie>(movieService.list(queryWrapper));

        }else if(status==0){
            queryWrapper.lt("status",0);
            return new JsonResult<Movie>(movieService.list(queryWrapper));
        }else {
            return new JsonResult<Movie>(movieService.list());
        }

    }


    //判断同时上映和类型
    @GetMapping("status_label/{status}/{label}")
    public JsonResult<Movie> findByStatusAndLabel(@PathVariable int status,@PathVariable String label){
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();

        if(status==1){
            queryWrapper.ge("status",0);
            queryWrapper.eq("label",label);
            return new JsonResult<Movie>(movieService.list(queryWrapper));

        }else if(status==0){
            queryWrapper.lt("status",0);
            queryWrapper.eq("label",label);
            return new JsonResult<Movie>(movieService.list(queryWrapper));
        }else {
            queryWrapper.eq("label",label);
            return new JsonResult<Movie>(movieService.list(queryWrapper));
        }
    }

    @GetMapping("/search/{mname}")
    public JsonResult<Movie> search(@PathVariable String mname){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("mname",mname);
        return new JsonResult<Movie>(movieService.list(queryWrapper));
    }

    @PostMapping("uploadMovieImgToQiNiu/{mid}")
    public JsonResult uploadMovieImgToQiNiu(@RequestParam MultipartFile file, @PathVariable String mid){
        //原始文件名称比如 aa.png
        String originalFilename = file.getOriginalFilename() ;
        //将原始名称修改为：唯一文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        //上传文件，上传到哪呢？图片服务器七牛云
        //把图片发放到距离图片最近的服务器上，降低我们自身服务器的带宽消耗
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            //上传成功
            String movieImgUrl = QiniuUtils.url+fileName;
            logger.info("====movieImgUrl:"+movieImgUrl+"====") ;
            //上传并不非要更新
            //TODO 如果不更新要把服务器上图片删掉，可是现在没空写了
//            Movie movie = movieService.getById(mid);
//            movie.setImg(movieImgUrl);
//            movieService.updateById(movie);
            return new JsonResult(String.valueOf(StateEnums.SUCCESS),movieImgUrl);
        }

        return new JsonResult(String.valueOf(StateEnums.UNKNOWN_ERROR),"上传失败");
    }
}
