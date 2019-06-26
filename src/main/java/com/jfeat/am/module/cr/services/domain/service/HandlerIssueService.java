package com.jfeat.am.module.cr.services.domain.service;


import com.jfeat.am.module.cr.services.bean.HandlerRequest;

public interface HandlerIssueService {


    /**
     * 指派某个 Issue
     * 1.自己指派给自己
     * 2.指派给其他同事
     * */
    Integer assign(HandlerRequest request);

    /**
     * 关闭某个 Issue
     * 1.自己创建-自己关闭原则
     * */
    Integer closed(HandlerRequest request);

    /**
     * 完成某个 Issue
     * 1.自己创建-自己执行完成原则
     * */
    Integer done(HandlerRequest request);

    /**
     * 重启某个 Issue
     * 1.自己创建-直接执行重启操作
     * 2.非自己创建，执行复制重新生成操作 此时该 issue 创建人变成了当前操作者
     * */
    Integer restart(HandlerRequest request);

    /**
     * 未复现
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    Integer nr(HandlerRequest request);

    /**
     * NFP 无修复计划
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    Integer nfp(HandlerRequest request);

    /**
     * 重复的ISSUE
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    Integer deplicate(HandlerRequest request);

    /**
     * 未发现该issue的问题，一般指两边业务理解的不同导致
     * 1.一般来说，指的是开发人员 对ISSUE 执行该操作
     * */
    Integer ntf(HandlerRequest request);

    /**
     * 修复 ISSUE
     * */
    Integer fixed(HandlerRequest request);

    /**
     * 不是自己处理的范畴 同时不知道谁处理，直接 route out
     * */
    Integer route(HandlerRequest request);


}
