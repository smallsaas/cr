package com.jfeat.am.module.cr.services.definition;

public enum CRStatus {
    OPEN,   // 新建为 open 状态
    WIP,    //指派
    CLOSED, // 关闭，未复现或者ISSUE提出本身存在错误时的状态值
    DONE    // 表示为 已修复状态，只能通过 发起者/或者管理员进行改变
}
