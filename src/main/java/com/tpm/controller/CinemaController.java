package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tpm.entity.Cinema_Movie;
import com.tpm.entity.Movie;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.CinemaMovieService;
import com.tpm.service.CinemaService;
import com.tpm.entity.Cinema;
import com.tpm.entity.JsonResult;
import com.tpm.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    CinemaService cinemaService;
    @Autowired
    CinemaMovieService cinemaMovieService;
    @Autowired
    MovieService movieService;

//    public List<Cinema> findLessPrice(){
//
//    }
    //根据cid返回一条影院信息
    @GetMapping("/cid/{cid}")
    public JsonResult<Cinema> getOneCinema(@PathVariable int cid){
        //Cinema c=cs.getById(cid);

        return new JsonResult<Cinema> (cinemaService.getById(cid));
    }

    //添加一条影院
    @PostMapping("")
    public JsonResult saveCinema(@Valid  Cinema cinema, BindingResult br){

        if(br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());

        }
        else{
            cinemaService.save(cinema);
            return new JsonResult("添加成功");
        }

    }

    //更改影院状态
    @PutMapping("/status/{cid}")
    public JsonResult updateCinema(@PathVariable int cid){
        Cinema c= cinemaService.getById(cid);
        if(c.getStatus()==1){
            c.setStatus(0);
        }
        else{
            c.setStatus(1);
        }
        boolean isUpdate = cinemaService.updateById(c);
        if(isUpdate==true){
            return new JsonResult("修改状态成功");
        }
        else{
            return new JsonResult(-1,"修改状态失败");
        }
    }

    //删除影院
    @DeleteMapping("{cid}")
    public JsonResult delCinema(@PathVariable int cid ){
        boolean isDel = cinemaService.removeById(cid);
        if(isDel==true){
            return new JsonResult("删除成功");
        }
        else{
            return new JsonResult(-1,"删除失败");
        }
    }

    //查询全部影院
    @GetMapping("")
    public JsonResult<Cinema> findAllCinema(){
       // List<Cinema> cinemaList = cs.list();
        return  new JsonResult<Cinema> (cinemaService.list());
    }

    //根据传入的对象-->修改影院的部分信息
    @PutMapping ("/update")
    public JsonResult updateCiema(@Valid  Cinema cinema,BindingResult br) {
        System.out.println(cinema.toString());

        if(br.hasErrors()){
            return new JsonResult(-1,"修改失败");

        }
        else{
            boolean isUpdate = cinemaService.updateById(cinema);
            if(isUpdate==true){
                return new JsonResult("修改成功");
            }
            else{
                throw new MyException(StateEnums.EDIT_ERROR,"异常");
            }
        }


    }

    //根据brand-->查询影院
    @GetMapping("brand/{brand}")
    public JsonResult<Cinema> findByBrand(@PathVariable String brand){
        Map<String,Object> map = new HashMap<>();
        map.put("brand",brand);
       return new JsonResult<Cinema> (cinemaService.listByMap(map));
    }

    //根据address-->查询影院
    @GetMapping("address/{address}")
    public JsonResult<Cinema> findByAddress(@PathVariable String address){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("address",address);
        return new JsonResult<Cinema> (cinemaService.list(queryWrapper));
    }

    //根据address与brand查询地址
    @GetMapping("/address_brand/{address}/{brand}")
    public JsonResult<Cinema> findByAddressAndBrand(@PathVariable String address,@PathVariable String brand){
        if(brand.equals("全部品牌")){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.like("address",address);
            return new JsonResult<Cinema>(cinemaService.list(queryWrapper));
        }else{

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("address",address);
        queryWrapper.eq("brand",brand);

        return new JsonResult<Cinema> (cinemaService.list(queryWrapper));
        }
    }


    //分页查询
    @GetMapping("{current}/{limit}")
    public JsonResult<Cinema> page(@PathVariable long  current, @PathVariable long limit){
        Page<Cinema> objectPage = new Page<>(current,limit);
        cinemaService.page(objectPage,null);
        long total = objectPage.getTotal();
        List<Cinema> records = objectPage.getRecords();
        return new JsonResult<Cinema>(total,records);

    }

    //通过影片查找影院
    @GetMapping("/cinema_movie/{mid}")
    public JsonResult<Cinema> findByMid(@PathVariable int mid){
        Map<String,Object> map = new HashMap<>();
        map.put("mid",mid);
        List<Cinema_Movie> list = cinemaMovieService.listByMap(map);
        List<Cinema> cinemaList = new ArrayList<>();
        for(Cinema_Movie cm:list){
            cinemaList.add(cinemaService.getById(cm.getCid()));
        }
        return new JsonResult<Cinema>(cinemaList);
    }

    //返回全部的地址信息
    @GetMapping("address")
    public JsonResult getAddress(){
        List<Cinema> cinemaList = cinemaService.list();
        String[] s = new String[cinemaList.size()];
        int count  = 0;
        try {
            for (Cinema c : cinemaList) {
                String address = c.getAddress().substring(3, 6);
                if (!Arrays.asList(s).contains(address)) {
                    s[count] = address;
                    count += 1;
                }

            }
        }catch (Exception e){
            throw new MyException(StateEnums.FIND_ERROR,"存在为空的字段的电影院，");
        }
        return new JsonResult(s);
    }

    //根据品牌返回全部的地址信息
    @GetMapping("address_brand/{brand}")
    public JsonResult getsAddress(@PathVariable String brand){
        List<Cinema> cinemaList = new ArrayList<>();

        if(brand.equals("全部品牌")){
            cinemaList = cinemaService.list();
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("brand",brand);
             cinemaList = cinemaService.listByMap(map);
        }
        String[] s = new String[cinemaList.size()];
        int count  = 0;
        for(Cinema c:cinemaList){
            String address = c.getAddress().substring(3,6);
            if(!Arrays.asList(s).contains(address)){
                s[count] = address;
                count+=1;
            }
        }
        return new JsonResult(s);
    }

    //查询影院
    @GetMapping("/search/{cname}")
    public JsonResult<Cinema> findByCname(@PathVariable String cname){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("cname",cname);
        return new JsonResult<Cinema> (cinemaService.list(queryWrapper));
    }



    //给影院添加影片
    @PostMapping("addMovie/{cid}/{mid}")
    public  JsonResult addcm(@PathVariable int cid,@PathVariable int mid){
        Cinema_Movie cinema_movie = new Cinema_Movie();
        cinema_movie.setCid(cid);
        cinema_movie.setMid(mid);
        if(cinemaMovieService.save(cinema_movie)){
            return new JsonResult("success");
        }
        throw  new MyException(StateEnums.ADD_ERROR,"添加影片失败");
    }

    //删除电影院的电影
    @DeleteMapping("removeMovie/{cid}/{mid}")
    public  JsonResult re(@PathVariable int cid,@PathVariable int mid){
        QueryWrapper queryWrapper =  new QueryWrapper();
        queryWrapper.eq("cid",cid);
        queryWrapper.eq("mid",mid);
        if(cinemaMovieService.remove(queryWrapper)){
            return new JsonResult("success");
        }
        throw  new MyException(StateEnums.REMOVE_ERROR,"删除电影失败");
    }

    //查找影院没有选过的影片
    @GetMapping("getMovie/{cid}")
    public JsonResult<Movie> getNotS(@PathVariable int cid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cid",cid);
        //先找到全部 该影院选中的影片的字段
        List<Cinema_Movie> cinema_movieList = cinemaMovieService.list(queryWrapper);
        //这个是存没选过的电影的集合
        List<Movie> movieList1 = new ArrayList<>();
        //这个是该电影选过的电影的集合
        List<Movie> movieList2 = new ArrayList<>();
        //循环添加到list2中
        for(Cinema_Movie cm:cinema_movieList){
            movieList2.add(movieService.getById(cm.getMid()));
        }

        //循环查找--如果不含有这个片，加到list1中
        for(Movie movie:movieService.list()){
            if(!movieList2.contains(movie)){
                movieList1.add(movie);
            }
        }

        return new JsonResult<Movie>(movieList1);
    }

}
