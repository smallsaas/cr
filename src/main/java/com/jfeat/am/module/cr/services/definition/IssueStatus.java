package com.jfeat.am.module.cr.services.definition;

public enum IssueStatus {
    //OPEN,   // 新建为 open 状态
    //WIP,    //指派
    //REPETITIONERROR, // 未复现
    //TRANSFER,   // 转移，基本业务为指派修复对象出错(如前端的问题，提交到了后端)
    //FIXED,
    //CLOSED, // 关闭，ISSUE提出本身存在错误或其他情况下的状态值
    //DONE    // 表示为 已修复状态，只能通过 发起者/或者管理员进行改变



                OPEN,//## 开放
                FIXED,//## 修复
                NFP,//##无修复计划
                NTF,//## 未发现问题
                DEPLICATE,// ## 重复
                NR,// ## 无法重现
                CLOSED,// ## 关闭
                DONE //## 完成
}
