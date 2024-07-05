package ymaker.javaweb.studyhabitsplan.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import ymaker.javaweb.studyhabitsplan.common.Context.BaseContext;
import ymaker.javaweb.studyhabitsplan.common.Result.Result;
import ymaker.javaweb.studyhabitsplan.common.utils.RecommendAlgorithm;
import ymaker.javaweb.studyhabitsplan.pojo.Dto;
import ymaker.javaweb.studyhabitsplan.pojo.StudyPlan;
import ymaker.javaweb.studyhabitsplan.server.service.StudyPlanService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/studyPlan")
public class StudyPlanController {
    @Autowired
    StudyPlanService studyPlanService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    RecommendAlgorithm recommendAlgorithm;

    @PostMapping("/add")
    public Result addStudyPlan(@RequestBody StudyPlan studyPlan){
        int i = studyPlanService.addStudyPlan(studyPlan);
        //redisTemplate.opsForValue().set("reminder_time",null);
        Result<Integer> success = Result.success(i);
        success.setMsg("add");
        return success;
    }

    @RequestMapping("/getAll")
    public Result getAllStudyPlans(String username){
        if(null!=username){
            List<StudyPlan> allStudyPlan = studyPlanService.getAllStudyPlan(username);
            StudyPlan first = allStudyPlan.getFirst();
            System.out.println(first.getDeadline());
            Result<List<StudyPlan>> success = Result.success(allStudyPlan);
            success.setMsg("getAll");
            return success;
        }else {
            String currentUsername = BaseContext.getCurrentUsername();
            if(null==currentUsername)return Result.error("未登录");
            List<StudyPlan> allStudyPlan = studyPlanService.getAllStudyPlan(currentUsername);
            Result<List<StudyPlan>> success = Result.success(allStudyPlan);
            success.setMsg("getAll");
            return success;
        }
    }

    @RequestMapping("/delete")
    public Result deleteStudyPlan(int id){
        studyPlanService.deleteStudyPlan(id);
        //redisTemplate.opsForValue().set("reminder_time",null);
        Result<StudyPlan> success = Result.success();
        success.setMsg("delete");
        return success;
    }

    @PostMapping("/update")
    public Result updateStudyPlan(@RequestBody StudyPlan studyPlan){
        studyPlanService.updateStudyPlan(studyPlan);
        //redisTemplate.opsForValue().set("reminder_time",null);
        return Result.success();
    }

    @PostMapping("/get")
    public Result getStudyPlan(String content,String topic,Integer priority,String username){
        if(null!=username){
            List<StudyPlan> studyPlans = studyPlanService.getStudyPlans(content, topic, priority, username);
            return Result.success(studyPlans);
        }
        else {
            String currentUsername = BaseContext.getCurrentUsername();
            if(null==currentUsername)return Result.error("未登录");
            List<StudyPlan> studyPlans = studyPlanService.getStudyPlans(content, topic, priority, currentUsername);
            return Result.success(studyPlans);
        }
    }

    @PostMapping("/getByTime")
    public Result getStudyPlanByTime(Date startTime,Date endTime,String username){
        if(null!=username){
            List<StudyPlan> studyPlans = studyPlanService.getStudyPlanByTime(startTime,endTime,username);
            return Result.success(studyPlans);
        }
        else {
            String currentUsername = BaseContext.getCurrentUsername();
            if(null==currentUsername)return Result.error("未登录");
            List<StudyPlan> studyPlans = studyPlanService.getStudyPlanByTime(startTime,endTime,currentUsername);
            return Result.success(studyPlans);
        }
    }

    @PostMapping("/getByStatus")
    public Result getStudyPlanByStatus(int status){
        List<StudyPlan> studyPlanByStatus = studyPlanService.getStudyPlanByStatus(status);
        return Result.success(studyPlanByStatus);
    }

    @PostMapping("/getById")
    public Result getStudyPlanById(int id){
        StudyPlan studyPlanById = studyPlanService.getStudyPlanById(id);
        return Result.success(studyPlanById);
    }
    @PostMapping("/getByFinishTime")
    public Result getStudyPlanByFinishTime(@RequestBody Dto dto) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(dto.getTime());
        String[] split = format.split(" ");
        format=split[0];
        String date01=format+" 00:00:00";
        String date02=format+" 23:59:59";
        Date start=simpleDateFormat.parse(date01);
        Date end=simpleDateFormat.parse(date02);
        String currentUsername = BaseContext.getCurrentUsername();
        List<StudyPlan> studyPlanByFinishTime = studyPlanService.getStudyPlanByFinishTime(start, end, currentUsername);
        return Result.success(studyPlanByFinishTime);
    }

    @RequestMapping("/getRecommend")
    public Result getStudyPlanRecommend(){
        List<StudyPlan> recommend = recommendAlgorithm.recommend();
        List<StudyPlan> result=new ArrayList<>();
        if (recommend.size()>=1){
            result.add(recommend.get(0));
        }
        if(recommend.size()>=2){
            result.add(recommend.get(1));
        }
        Result<List<StudyPlan>> success = Result.success(result);
        success.setMsg("getRecommend");
        return success;
    }

    @PostMapping("/finish")
    public Result finishStudyPlan(@RequestBody StudyPlan studyPlan) {
        studyPlanService.updateStudyPlan(studyPlan);
        Result<Object> success = Result.success();
        success.setMsg("finish");
        return success;
    }


}
