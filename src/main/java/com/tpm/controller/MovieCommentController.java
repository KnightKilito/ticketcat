package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tpm.entity.JsonResult;
import com.tpm.entity.Movie;
import com.tpm.entity.MovieComment;
import com.tpm.entity.Ticket;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.MovieCommentService;
import com.tpm.service.MovieService;
import com.tpm.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("moviecomment")
public class MovieCommentController {
    @Autowired
    MovieCommentService movieCommentService;
    @Autowired
    TicketService ticketService;
    @Autowired
    MovieService movieService;

        @PostMapping("{tid}/{mid}/{uscore}")
        public JsonResult add(@PathVariable int tid,@PathVariable int mid,@PathVariable double uscore){
            MovieComment movieComment = new MovieComment();
            movieComment.setUscore(uscore);
            movieComment.setMid(mid);
            movieComment.setTid(tid);
            if(movieCommentService.save(movieComment)){

                //评论后分数改变
                Map<String,Object> map = new HashMap<>();
                map.put("mid",mid);
                List<MovieComment> movieCommentList = movieCommentService.listByMap(map);
                double count = 0;
                for (MovieComment comment : movieCommentList) {
                    count+=comment.getUscore();
                }
                count = count/movieCommentList.size();
                Movie m = movieService.getById(mid);
                m.setScore(String.format("%.1f", count));
                movieService.updateById(m);

                //修改订单状态
                Ticket t = ticketService.getById(tid);
                t.setStatus(3);
                if(ticketService.updateById(t))
                {
                    return  new JsonResult("成功");
                }else{
                    throw new MyException(StateEnums.EDIT_ERROR,"修改评论完成状态异常-->3");
                }

            }else {
                throw new MyException(StateEnums.ADD_ERROR,"评论失败");
            }

        }

        @GetMapping("{mid}")
         public JsonResult<MovieComment> gett(@PathVariable int mid){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("mid",mid);
            return new JsonResult<>(movieCommentService.list(queryWrapper));
        }

        
}
