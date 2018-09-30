package qian.ling.yi.ext.tbSchedule;

import com.alibaba.fastjson.JSON;
//import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
//import com.taobao.pamirs.schedule.TaskItemDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * 要求实现Schedule的接口IScheduleTaskDealMulti或者IScheduleTaskDealSingle。
 接口主要包括两个方法。一个是根据调度器分配到的队列查询数据的接口，一个是进行数据处理的接口。
 * Created by liuguobin on 10/20/16.
 */
@Component
public class MyTbScheduleJob
//        implements IScheduleTaskDealSingle<String>
{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 选择任务. 从DB中读取数据, 将取出的数据返回
     * @param taskParameter
     * @param ownSign
     * @param taskItemNum
     * @param taskItemList
     * @param eachFetchDataNum
     * @return
     * @throws Exception
     */
    /**
     * taskTypeInfo.getTaskParameter(),
     scheduleManager.getScheduleServer().getOwnSign(),
     this.scheduleManager.getTaskItemCount(), tmpTaskList,
     taskTypeInfo.getFetchDataNumber()
     */
//    public List<String> selectTasks(String taskParameter, String ownSign,
//                            int taskItemNum, List<TaskItemDefine> taskItemList,
//                            int eachFetchDataNum) throws Exception {
//        logger.info("taskParameter {}", taskParameter);
//        logger.info("ownSign {}", ownSign);//不同的开发人员需要进行数据隔离也可以用OwnSign来实现，避免不同人员的数据冲突。缺省配置的环境区域OwnSign='BASE'。
//        logger.info("taskItemNum {}", taskItemNum);
//        logger.info("taskItemList {}", JSON.toJSONString(taskItemList));
//        logger.info("taskItem {}", taskItemList.get(0).toString());
//        logger.info("eachFetchDataNum {}", eachFetchDataNum);
//
//        List list = new ArrayList(1);
//
//        list.add("");
//        return list;
//    }
//
//    @Override
//    public Comparator getComparator() {
//        return null;
//    }
//
//    /**
//     * 向目标表中插入数据
//     * @param model
//     * @param ownSign
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean execute(String model, String ownSign)
//            throws Exception {
//        try {
//            //insertData(model);
//            System.out.println("执行任务");
//            Thread.sleep(10000);
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


}
