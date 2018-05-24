package com.dfire.common.util;

import com.dfire.common.entity.HeraDebugHistory;
import com.dfire.common.entity.HeraJob;
import com.dfire.common.entity.vo.HeraDebugHistoryVo;
import com.dfire.common.entity.vo.HeraJobVo;
import com.dfire.common.enums.Status;
import com.dfire.common.enums.TriggerType;
import com.dfire.common.entity.HeraJobHistory;
import com.dfire.common.entity.HeraProfile;
import com.dfire.common.entity.vo.HeraJobHistoryVo;
import com.dfire.common.entity.vo.HeraProfileVo;
import com.dfire.common.processor.Processor;
import com.dfire.common.vo.LogContent;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: <a href="mailto:lingxiao@2dfire.com">凌霄</a>
 * @time: Created in 上午10:52 2018/5/2
 * @desc
 */
public class BeanConvertUtils {

    public static HeraProfileVo convert(HeraProfile heraProfile) {
        HeraProfileVo heraProfileVo = HeraProfileVo.builder().build();
        if (heraProfile.getHadoopConf() != null) {
            heraProfileVo.setHadoopConf(StringUtil.convertStringToMap(heraProfile.getHadoopConf()));
        }
        BeanUtils.copyProperties(heraProfile, heraProfileVo);
        return heraProfileVo;
    }

    public static HeraProfile convert(HeraProfileVo heraProfileVo) {
        HeraProfile heraProfile = HeraProfile.builder().build();
        if (heraProfileVo.getHadoopConf() != null) {
            heraProfile.setHadoopConf(StringUtil.convertMapToString(heraProfileVo.getHadoopConf()));
        }
        BeanUtils.copyProperties(heraProfile, heraProfileVo);
        return heraProfile;
    }

    public static HeraJobHistoryVo convert(HeraJobHistory heraJobHistory) {
        HeraJobHistoryVo heraJobHistoryVo = HeraJobHistoryVo.builder().build();
        BeanUtils.copyProperties(heraJobHistory, heraJobHistoryVo);
        heraJobHistoryVo.setLog(LogContent.builder().content(new StringBuffer(heraJobHistory.getLog())).build());
        heraJobHistoryVo.setProperties(StringUtil.convertStringToMap(heraJobHistory.getProperties()));
        heraJobHistoryVo.setStatus(Status.parse(heraJobHistory.getStatus()));
        heraJobHistoryVo.setTriggerType(TriggerType.parser(heraJobHistory.getTriggerType()));
        return heraJobHistoryVo;

    }

    public static HeraJobHistory convert(HeraJobHistoryVo jobHistoryVo) {
        HeraJobHistory jobHistory = HeraJobHistory.builder().build();
        BeanUtils.copyProperties(jobHistoryVo, jobHistory);
        jobHistory.setLog(jobHistoryVo.getLog().getContent());
        jobHistory.setStatus(jobHistoryVo.getStatus().toString());
        jobHistory.setProperties(StringUtil.convertMapToString(jobHistoryVo.getProperties()));
        jobHistory.setTriggerType(jobHistoryVo.getTriggerType().getId());
        return jobHistory;
    }

    public static HeraDebugHistoryVo convert(HeraDebugHistory heraJobHistory) {
        HeraDebugHistoryVo heraJobHistoryVo = HeraDebugHistoryVo.builder().build();
        BeanUtils.copyProperties(heraJobHistory, heraJobHistoryVo);
        heraJobHistoryVo.setStatus(Status.parse(heraJobHistory.getStatus()));
        heraJobHistoryVo.setLog(LogContent.builder().content(new StringBuffer(heraJobHistory.getLog())).build());
        return heraJobHistoryVo;

    }

    public static HeraDebugHistory convert(HeraDebugHistoryVo jobHistoryVo) {
        HeraDebugHistory jobHistory = HeraDebugHistory.builder().build();
        BeanUtils.copyProperties(jobHistoryVo, jobHistory);
        jobHistory.setStatus(jobHistoryVo.getStatus().toString());
        jobHistory.setLog(jobHistoryVo.getLog().getContent());
        return jobHistory;
    }

    public static HeraJobVo convert(HeraJob heraJob) {
        HeraJobVo heraJobVo = HeraJobVo.builder().build();
        BeanUtils.copyProperties(heraJob, heraJobVo);
        heraJobVo.setConfigs(StringUtil.convertStringToMap(heraJob.getConfigs()));
        Processor postProcessor = StringUtil.convertProcessorToList(heraJob.getPostProcessors());
        Processor preProcessor = StringUtil.convertProcessorToList(heraJob.getPreProcessors());
        List<Processor> list = new ArrayList<>();
        list.add(postProcessor);
        heraJobVo.setPostProcessors(list);
        list.clear();
        list.add(preProcessor);
        heraJobVo.setPreProcessors(list);
        heraJobVo.setResources(StringUtil.convertResources(heraJob.getResources()));
        heraJobVo.setId(String.valueOf(heraJob.getId()));
        return heraJobVo;
    }

    public static HeraJob convert(HeraJobVo heraJobVo) {
        HeraJob heraJob = HeraJob.builder().build();
        BeanUtils.copyProperties(heraJobVo, heraJob);
        heraJob.setPostProcessors(StringUtil.convertProcessorToList(heraJobVo.getPostProcessors()));
        heraJob.setPreProcessors(StringUtil.convertProcessorToList(heraJobVo.getPreProcessors()));
        heraJob.setResources(StringUtil.convertResoureToString(heraJobVo.getResources()));
        heraJob.setConfigs(StringUtil.convertMapToString(heraJobVo.getConfigs()));
        heraJob.setId(Integer.parseInt(heraJobVo.getId()));
        return heraJob;
    }



}