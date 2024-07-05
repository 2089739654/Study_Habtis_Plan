package ymaker.javaweb.studyhabitsplan;

import cn.hutool.cron.pattern.CronPattern;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ymaker.javaweb.studyhabitsplan.dao.TestMapper;
import ymaker.javaweb.studyhabitsplan.pojo.StudyPlan;
import ymaker.javaweb.studyhabitsplan.pojo.User;
import ymaker.javaweb.studyhabitsplan.server.mapper.StudyPlanMapper;
import ymaker.javaweb.studyhabitsplan.server.service.StudyPlanService;
import ymaker.javaweb.studyhabitsplan.server.service.Userservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudyHabitsPlanApplicationTests {
	@Autowired
	StudyPlanMapper studyPlanMapper;
	@Autowired
	StudyPlanService studyPlanService;
	/*@Autowired
	Userservice userservic;*/




}
